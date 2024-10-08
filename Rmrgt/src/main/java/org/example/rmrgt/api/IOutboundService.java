package org.example.rmrgt.api;

import org.example.common.DTO.RemitDTO;

public interface IOutboundService {
    void processOutbound(RemitDTO remitDTO);
}
