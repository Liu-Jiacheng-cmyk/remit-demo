package org.example.rmrgt.impl;

import org.example.common.DTO.RemitDTO;
import org.example.common.domain.em.CommonEnum.BaseEnum;
import org.example.common.exceptions.TransferFailedException;
import org.example.common.utils.ObjectUtils;
import org.example.rmrgt.api.IOutboundService;
import org.example.rmrgt.dao.mdo.RmrgtRegOutMdo;
import org.example.rmrgt.datasource.DynamicDataSourceContextHolder;
import org.example.rmrgt.mapper.RmrgtRegOutMdoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class OutboundServiceImpl implements IOutboundService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private RmrgtRegOutMdoMapper rmrgtRegOutMdoMapper;

    @Override
    public void processOutbound(RemitDTO remitDTO) {
        if(ObjectUtils.isNull(remitDTO)){
            throw new TransferFailedException("请输入正确的信息");
        }

        // 幂等性校验，确保汇出请求只被执行一次
        String remitNo = remitDTO.getRemitNo();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(remitNo + ":outbound"))) {
            throw new TransferFailedException("Duplicate outbound transfer request");
        }

        // 设置汇款编号为“处理中”，有效期为1小时
        redisTemplate.opsForValue().set(remitNo + ":outbound", "processing", 1, TimeUnit.HOURS);

        try{
            // 设置源数据库 (付款人数据库)
            DynamicDataSourceContextHolder.setDataSourceKey(accountService.determineBdus(remitDTO.getPayerNo()));
            // 验证付款人密码和余额
            accountService.valAccPwd(remitDTO);
            accountService.valAccBala(remitDTO);

            // 扣减付款人余额
            accountService.debitAccBala(remitDTO);

            // 登记汇出记录
            regOutBound(remitDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new TransferFailedException("Error during outbound transfer process: " + e.getMessage());
        } finally {
            // 清理数据源上下文
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }

    private void regOutBound(RemitDTO remitDTO) {
        // 记录汇出记录
        RmrgtRegOutMdo regOut = new RmrgtRegOutMdo();
        regOut.setRemittanceId(remitDTO.getRemitNo());
        regOut.setTransactionDate(remitDTO.getRemitDate());
        regOut.setSenderAccount(remitDTO.getPayerNo());
        regOut.setSenderName(remitDTO.getPayerName());
        regOut.setRecipientAccount(remitDTO.getPayeeNo());
        regOut.setRecipientName(remitDTO.getPayeeName());
        regOut.setRemittanceAmount(remitDTO.getRemitAmount());
        regOut.setCurrencyCode(BaseEnum.CurrCode.CNY.getValue());
        regOut.setCreatedAt(new Date());
        regOut.setUpdatedAt(new Date());

        rmrgtRegOutMdoMapper.insert(regOut);
    }
}
