package org.example.rmrgt.impl;

import org.example.common.DTO.RemitDTO;
import org.example.common.exceptions.InsufficientBalanceException;
import org.example.common.exceptions.InvalidPasswordException;
import org.example.common.exceptions.TransferFailedException;
import org.example.common.utils.ObjectUtils;
import org.example.rmrgt.api.IAccountService;
import org.example.rmrgt.dao.mdo.CustomerAccountInfoMdo;
import org.example.rmrgt.mapper.CustomerAccountInfoMdoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private CustomerAccountInfoMdoMapper customerAccountInfoMdoMapper;

    @Override
    public String determineBdus(String accountNo) {
        // 检查账号是否为空
        if (accountNo == null || accountNo.isEmpty()) {
            throw new TransferFailedException("Account number cannot be null or empty");
        }
        // 获取账号的最后一位数字
        int lastDigit = Character.getNumericValue(accountNo.charAt(accountNo.length() - 1));

        // 判断是单数还是双数
        if (lastDigit % 2 == 0) {
            return "bdus2"; // 双数 -> bdus2
        } else {
            return "bdus1"; // 单数 -> bdus1
        }
    }

    @Override
    public void valAccPwd(RemitDTO remitDTO) {
        CustomerAccountInfoMdo customerAccountInfoMdo =
                customerAccountInfoMdoMapper.selectByPrimaryKey(remitDTO.getPayerNo());
        if(ObjectUtils.isNull(customerAccountInfoMdo.getAccountPassword())
                || !customerAccountInfoMdo.getAccountPassword().equals(remitDTO.getPayerPwd())){
            throw new InvalidPasswordException("密码错误");
        }
    }

    @Override
    public void valAccBala(RemitDTO remitDTO) {
        CustomerAccountInfoMdo customerAccountInfoMdo =
                customerAccountInfoMdoMapper.selectByPrimaryKey(remitDTO.getPayerNo());
        if(ObjectUtils.isNull(customerAccountInfoMdo.getAccountBalance())
                || (customerAccountInfoMdo.getAccountBalance().compareTo(remitDTO.getRemitAmount())<0)){
            throw new InsufficientBalanceException("余额不足");
        }
    }

    @Override
    public void debitAccBala(RemitDTO remitDTO) {
        CustomerAccountInfoMdo customerAccountInfoMdo =
                customerAccountInfoMdoMapper.selectByPrimaryKey(remitDTO.getPayerNo());
        customerAccountInfoMdo.setAccountBalance(customerAccountInfoMdo.getAccountBalance().subtract(remitDTO.getRemitAmount()));
        customerAccountInfoMdoMapper.updateByPrimaryKeySelective(customerAccountInfoMdo);
    }

    @Override
    public void creditAccBala(RemitDTO remitDTO) {
        CustomerAccountInfoMdo customerAccountInfoMdo =
                customerAccountInfoMdoMapper.selectByPrimaryKey(remitDTO.getPayerNo());
        customerAccountInfoMdo.setAccountBalance(customerAccountInfoMdo.getAccountBalance().add(remitDTO.getRemitAmount()));
        customerAccountInfoMdoMapper.updateByPrimaryKeySelective(customerAccountInfoMdo);
    }
}
