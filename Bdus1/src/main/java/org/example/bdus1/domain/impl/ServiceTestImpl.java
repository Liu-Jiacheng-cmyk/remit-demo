package org.example.bdus1.domain.impl;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.bdus1.domain.api.ServiceTest;
import org.springframework.stereotype.Component;

@Service
@Component
public class ServiceTestImpl implements ServiceTest {

    @Override
    public String sayhello(String name) {
        return "Hello, " + name + "! This is from Dubbo provider.";
    }
}
