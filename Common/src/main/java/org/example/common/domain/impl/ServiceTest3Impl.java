package org.example.common.domain.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.common.domain.Service.ServiceTest3;
import org.springframework.stereotype.Component;

@Service
@Component
public class ServiceTest3Impl implements ServiceTest3 {
    @Override
    public String sayhello(String name) {
        return "Hello, " + name + "! This is from Dubbo provider3.";
    }
}
