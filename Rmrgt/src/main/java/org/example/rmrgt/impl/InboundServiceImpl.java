package org.example.rmrgt.impl;

import org.example.common.DTO.RemitDTO;
import org.example.common.domain.em.CommonEnum.BaseEnum;
import org.example.common.exceptions.TransferFailedException;
import org.example.rmrgt.api.IInboundService;
import org.example.rmrgt.dao.mdo.RmrgtRegInMdo;
import org.example.rmrgt.datasource.DynamicDataSourceContextHolder;
import org.example.rmrgt.mapper.RmrgtRegInMdoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.example.common.utils.ObjectUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class InboundServiceImpl implements IInboundService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private RmrgtRegInMdoMapper rmrgtRegInMdoMapper;

    @Override
    public void processInbound(RemitDTO remitDTO) {
        if (ObjectUtils.isNull(remitDTO)) {
            throw new TransferFailedException("请输入正确的信息");
        }

        // 使用 Redis 检查是否已处理过
        String remitNo = remitDTO.getRemitNo();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(remitNo + ":inbound"))) {
            throw new TransferFailedException("Duplicate inbound transfer request");
        }
        try {
            // 设置收款人数据库源
            String payeeSource = accountService.determineBdus(remitDTO.getPayeeNo());
            DynamicDataSourceContextHolder.setDataSourceKey(payeeSource);

            // 验证收款人账户是否存在
            accountService.valAccExists(remitDTO);

            // 数据库校验入账记录是否已存在，确保幂等性
            RmrgtRegInMdo existingRegIn = rmrgtRegInMdoMapper.selectByRemitNo(remitNo);
            if (existingRegIn != null) {
                throw new TransferFailedException("Duplicate inbound record detected");
            }

            // 增加收款人余额
            accountService.creditAccBala(remitDTO);

            // 在 Redis 中保存该入账信息，避免重复处理
            redisTemplate.opsForValue().set(remitNo + ":inbound", "completed", 1, TimeUnit.HOURS);

            // 记录入账信息
            regInBound(remitDTO);

        } catch (Exception e) {
            e.printStackTrace();
            throw new TransferFailedException("Error during inbound transfer process: " + e.getMessage());
        } finally {
            // 清理数据源上下文
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }

    private void regInBound(RemitDTO remitDTO) {
        RmrgtRegInMdo rmrgtRegInMdo = new RmrgtRegInMdo();

        rmrgtRegInMdo.setRemittanceId(remitDTO.getRemitNo());
        rmrgtRegInMdo.setTransactionDate(remitDTO.getRemitDate());
        rmrgtRegInMdo.setSenderAccount(remitDTO.getPayerNo());
        rmrgtRegInMdo.setSenderName(remitDTO.getPayerName());
        rmrgtRegInMdo.setRecipientAccount(remitDTO.getPayeeNo());
        rmrgtRegInMdo.setRecipientName(remitDTO.getPayeeName());
        rmrgtRegInMdo.setRemittanceAmount(remitDTO.getRemitAmount());
        rmrgtRegInMdo.setCurrencyCode(BaseEnum.CurrCode.CNY.getValue());
        rmrgtRegInMdo.setCreatedAt(new Date());
        rmrgtRegInMdo.setUpdatedAt(new Date());

        rmrgtRegInMdoMapper.insert(rmrgtRegInMdo);
    }
}
