package org.example.rmrgt.api;

import org.example.common.DTO.RemitDTO;

public interface IRemitService {
    public String remitOut(RemitDTO remitDTO) throws Exception;

    public void registerRemitOut(RemitDTO remitDTO);
}
