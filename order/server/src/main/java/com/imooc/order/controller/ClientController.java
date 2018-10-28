package com.imooc.order.controller;


import com.imooc.product.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ClientController {
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
//        //1、第一种方式-需要手动写地址（多个地址就没法了）
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.getForObject("http://localhost:8081/msg", String.class);
//
//        //2、第二种方式-使用LoadBalancerClient轮询获取多个地址中的一个
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");//PRODUCT为服务名
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()+"/msg");
//        String response = restTemplate.getForObject(url, String.class);
//
//        //3、第三种-把RestTemplate注解成一个Bean,再把loadBalancerClient注入RestTemplate中，让RestTemplate也有轮询的功能
////        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//

//下面使用feign来调用
//        String msg = productClient.productMsg();
//        return msg;
        return null;
    }


}
