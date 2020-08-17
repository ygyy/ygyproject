package com.ygy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign客户端接口
 */
@FeignClient(value = "hello-service")
public interface HelloServiceFeignClient {

    @GetMapping("/hello/{name}")
    String consumer(@PathVariable String name);
}
