package org.example.bdus2.domain.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.bdus2.domain.Service.ServiceTest2;
import org.springframework.stereotype.Component;

@Service
@Component
public class ServiceTest2Impl implements ServiceTest2 {
    @Override
    public String sayhello(String name) {
        return "Hello, " + name + "! This is from Dubbo provider2.";
    }
}
