package com.luster.consumer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("luster-provider-client")
public interface ProviderServiceClient {
    @RequestMapping("/hello")
    String hello();
}
