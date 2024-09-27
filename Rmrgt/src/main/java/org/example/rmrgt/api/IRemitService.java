package org.example.rmrgt.api;

import org.example.common.DTO.RemitDTO;

public interface IRemitService {
    public String remit(RemitDTO remitDTO) throws Exception;

    public void registerRemit(RemitDTO remitDTO);
}
