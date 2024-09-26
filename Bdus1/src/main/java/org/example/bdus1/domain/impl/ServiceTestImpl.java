package org.example.bdus1.domain.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.bdus1.domain.service.ServiceTest;
import org.springframework.stereotype.Component;

@Service
@Component
public class ServiceTestImpl implements ServiceTest {

    @Override
    public String sayhello(String name) {
        return "Hello, " + name + "! This is from Dubbo provider.";
    }
}
