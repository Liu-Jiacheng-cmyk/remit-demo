package org.example.bdus1.domain.impl;

import org.example.bdus1.domain.service.RemitService;
import org.example.common.DTO.RemitDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RemitServiceImpl implements RemitService {
    @Transactional
    public void remit(RemitDTO remitDTO) {
        //密码校验

    }
}
