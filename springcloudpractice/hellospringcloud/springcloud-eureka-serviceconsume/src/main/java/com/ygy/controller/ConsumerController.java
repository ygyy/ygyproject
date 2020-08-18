package com.ygy.controller;

import com.ygy.service.HelloServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    HelloServiceFeignClient helloServiceFeignClient;

    @GetMapping("/consumer/{name}")
    public String helloWorld(@PathVariable("name") String name){
        //第一种调用方式,直接调用，不经过注册中心服务列表，直接访问servicesupport
        //String forObject = new RestTemplate().getForObject("http://localhost:8071/Hello/World?s=" + s, String.class);

        //第二种调用方式
        //根据服务名 获取服务列表 根据算法选取某个服务 并访问某个服务的网络位置。
        //ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-SERVICE");
        //String forObject = new RestTemplate().getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/Hello/World?s="+s,String.class);

        //第三种调用方式 需要restTemplate注入的方式
        //String forObject = restTemplate.getForObject("http://helloservice/hello/"+name, String.class);

        //用feign
        return helloServiceFeignClient.consumer(name)+"   消费";
    }
}