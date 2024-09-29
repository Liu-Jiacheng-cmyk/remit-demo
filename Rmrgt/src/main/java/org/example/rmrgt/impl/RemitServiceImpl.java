package org.example.rmrgt.impl;

import org.example.common.DTO.RemitDTO;
import org.example.common.domain.em.CommonEnum.BaseEnum;
import org.example.common.exceptions.TransferFailedException;
import org.example.common.utils.ObjectUtils;
import org.example.rmrgt.api.IRemitService;
import org.example.rmrgt.dao.mdo.RmrgtLifecycleMdo;
import org.example.rmrgt.dao.mdo.RmrgtRegInMdo;
import org.example.rmrgt.dao.mdo.RmrgtRegOutMdo;
import org.example.rmrgt.datasource.DynamicDataSourceContextHolder;
import org.example.rmrgt.mapper.RmrgtLifecycleMdoMapper;
import org.example.rmrgt.mapper.RmrgtRegInMdoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Service
public class RemitServiceImpl implements IRemitService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private RmrgtLifecycleMdoMapper rmrgtLifecycleMdoMapper;

    @Override
    public String remitOut(RemitDTO remitDTO) throws Exception {
        if(ObjectUtils.isNull(remitDTO)){
            throw new TransferFailedException("请输入正确的信息");
        }
        // 幂等性校验
        String remitNo = remitDTO.getRemitNo();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(remitNo))) {
            throw new TransferFailedException("Duplicate transfer request");
        }
        // 将汇款编号存入 Redis，设置有效期为1小时
        redisTemplate.opsForValue().set(remitNo, "processing", 1, TimeUnit.HOURS);
        try{
            // 设置源数据库 (付款人数据库)
            DynamicDataSourceContextHolder.setDataSourceKey(accountService.determineBdus(remitDTO.getPayerNo()));
            // 验证付款人密码和余额
            accountService.valAccPwd(remitDTO);
            accountService.valAccBala(remitDTO);

            // 扣减付款人余额
            accountService.debitAccBala(remitDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    @Transactional
    public void registerRemitOut(RemitDTO remitDTO) {
        // 记录转账生命周期和转出、转入流水
        RmrgtLifecycleMdo lifecycle = new RmrgtLifecycleMdo();
        lifecycle.setRemittanceId(remitDTO.getRemitNo());
        lifecycle.setCurrencyCode(BaseEnum.CurrCode.CNY.getValue());
        lifecycle.setDirectionCode(BaseEnum.RemDireCode.OUTWARD.getCode());
        lifecycle.setStatusChangeDate(String.valueOf(new Date()));
        lifecycle.setStatusChangeReason("发起转账");
        lifecycle.setGlobalTrackingNumber("");
        lifecycle.setCreatedAt(new Date());
        lifecycle.setUpdatedAt(new Date());
        rmrgtLifecycleMdoMapper.insert(lifecycle);

        // 转出记录
        RmrgtRegOutMdo regOut = new RmrgtRegOutMdo();
        regOut.setRemittanceId(remitDTO.getRemitNo());
        regOut.setCustomerNumber(remitDTO.getPayerNo());
        regOut.set(remitDTO.getRemitAmount());
        regOut.setRemarks(remitDTO.getRemitRemarks());
        regOut.setCreatedAt(new Date());
        regOut.setUpdatedAt(new Date());
        regOutMdoMapper.insert(regOut);
    }
    }
}
