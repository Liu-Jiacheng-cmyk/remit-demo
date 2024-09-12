package org.example.gateway.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.gateway.api.ServiceGateway;
import org.springframework.stereotype.Component;

@Service
@Component
public class ServiceGatewayImpl implements ServiceGateway {
    @Override
    public String sayhello(String name) {
        return "Hello, " + name + "! This is from Dubbo provider gateway.";
    }
}
