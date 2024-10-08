package org.example.rmrgt.api;

import org.example.common.DTO.RemitDTO;

public interface IAccountService {

    public String determineBdus(String accountNo);

    public void valAccExists(RemitDTO remitDTO);

    public void valAccPwd(RemitDTO remitDTO);

    public void valAccBala(RemitDTO remitDTO);

    public void debitAccBala(RemitDTO remitDTO);

    public void creditAccBala(RemitDTO remitDTO);
}
