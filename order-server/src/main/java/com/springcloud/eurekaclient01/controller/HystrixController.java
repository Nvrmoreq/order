package com.springcloud.eurekaclient01.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 17:18 2019/12/26
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //@HystrixCommand(fallbackMethod = "fallback")

    /*@HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")//超时时间，降级
    })*/

   /* @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),                   //设置熔断，断路器及时切断故障主逻辑调用
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),      //断路器最小请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//休眠时间窗设置断路器为half-open状态，发送一次请求，正常返回断路器闭合，主逻辑恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //断路器打开错误百分比条件
    })*/
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number){
        if(number % 2 == 0){
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8002/product/listForOrder", Arrays.asList("157875196366160022"),String.class);
    }

    private String fallback(){
        return "太拥挤了请稍后再试...";
    }
    private String defaultFallback(){
        return "默认提示:太拥挤了请稍后再试...";
    }
}
