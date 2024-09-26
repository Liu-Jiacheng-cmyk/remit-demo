package org.example.common.domain.Service;

import java.math.BigDecimal;

public interface ICommonCheck {

    public void checkAccPwd(String accNo, String pwd);

    public void checkAccBalance(String accNo);

    public void deductAccBalance(String accNo, BigDecimal amount);

    public void increaseAccBalance(String accNo, BigDecimal amount);

}
