package com.luster.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TestController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private ProviderServiceClient providerService;

    @RequestMapping("/ribbon")
    public String ribbon() {
        ServiceInstance instance = loadBalancer.choose("luster-provider-client");
        URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        // ... do something with the URI
        return "Hello world";
    }

    @RequestMapping("/feign")
    public String feign() {
        return providerService.hello();
    }

}
