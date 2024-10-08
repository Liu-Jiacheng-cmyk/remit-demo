package org.example.rmrgt.api;

import org.example.common.DTO.RemitDTO;

public interface IInboundService {
    void processInbound(RemitDTO remitDTO);
}
