package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/hystrix")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    @GetMapping("/getProductList")
//    1、@HystrixCommand(fallbackMethod = "fallback")
    //2、使用默认方法，并设置超时时间为3s
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    //3、在配置文件指定超时时间，这里加@HystrixCommand就行
    @HystrixCommand
    public String getProductList(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8081/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);

//        throw new RuntimeException("去调用fallback方法吧");  //也可以通过抛异常手动实现服务降级
    }

    public String fallback(){
        return "我擦，出问题了";
    }

    public String defaultFallback(){
        return "默认：我擦，出问题了";
    }

}
