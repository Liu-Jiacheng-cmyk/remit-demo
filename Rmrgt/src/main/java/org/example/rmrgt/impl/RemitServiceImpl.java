package org.example.rmrgt.impl;

import org.example.common.DTO.RemitDTO;
import org.example.common.domain.em.CommonEnum.BaseEnum;
import org.example.rmrgt.api.IInboundService;
import org.example.rmrgt.api.IOutboundService;
import org.example.rmrgt.api.IRemitService;
import org.example.rmrgt.dao.mdo.RmrgtLifecycleMdo;
import org.example.rmrgt.datasource.DynamicDataSourceContextHolder;
import org.example.rmrgt.mapper.RmrgtLifecycleMdoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Service
public class RemitServiceImpl implements IRemitService {

    @Autowired
    private RmrgtLifecycleMdoMapper rmrgtLifecycleMdoMapper;

    @Autowired
    private IOutboundService iOutboundService;

    @Autowired
    private IInboundService iInboundService;

    @Override
    @Transactional
    public void remitService(RemitDTO remitDTO){
        // 1. 确定汇款客户和收款客户所在的数据库
        String payerSource = determineSource(remitDTO.getPayerNo());
        String payeeSource = determineSource(remitDTO.getPayeeNo());


        // 2. 设置汇出账户的数据源，并进行汇出操作
        try {
            DynamicDataSourceContextHolder.setDataSourceKey(payerSource);
            iOutboundService.processOutbound(remitDTO);
        } finally {
            // 确保数据源上下文被清理
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }

        // 3. 设置汇入账户的数据源，并进行汇入操作
        try {
            DynamicDataSourceContextHolder.setDataSourceKey(payeeSource);
            iInboundService.processInbound(remitDTO);
        } finally {
            // 确保数据源上下文被清理
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }

        // 4. 记录汇款生命周期信息
        try {
            DynamicDataSourceContextHolder.setDataSourceKey(payerSource); // 默认将生命周期记录到汇款客户的数据库中
            RmrgtLifecycleMdo lifecycle = new RmrgtLifecycleMdo();
            lifecycle.setRemittanceId(remitDTO.getRemitNo());
            lifecycle.setTransactionDate(new Date().toString());
            lifecycle.setDirectionCode(BaseEnum.RemDireCode.OUTWARD.getCode());
            lifecycle.setStatusChangeReason("Transfer Completed");
            lifecycle.setCurrencyCode(BaseEnum.CurrCode.CNY.getValue());
            lifecycle.setCreatedAt(new Date());
            lifecycle.setUpdatedAt(new Date());

            rmrgtLifecycleMdoMapper.insert(lifecycle);
        } finally {
            // 确保数据源上下文被清理
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }

    private String determineSource(String accountNo) {
        char lastChar = accountNo.charAt(accountNo.length() - 1);
        if (Character.isDigit(lastChar)) {
            int lastDigit = Character.getNumericValue(lastChar);
            if (lastDigit % 2 == 0) {
                return "bdus2";
            } else {
                return "bdus1";
            }
        } else {
            throw new IllegalArgumentException("Invalid account number format");
        }
    }

}
