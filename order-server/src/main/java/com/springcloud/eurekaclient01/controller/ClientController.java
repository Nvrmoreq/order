package com.springcloud.eurekaclient01.controller;

import com.springcloud.client.ProductClient;
import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient01.bean.ProductInfo;

import com.springcloud.eurekaclient01.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 10:12 2019/11/22
 */
@Slf4j
@RestController
public class ClientController {

    @Autowired
    private ProductClient productClient;

    /**
     * 模拟调用商品服务接口
     * 1.使用RestTemplate
     * 2.使用Feign(它是声明式REST客户端（伪RPC），采用了基于接口的注解)
     *
     * @return
     */
    /*@GetMapping("/getProductMsg")
    public String getProductMsg(){
        String response = productClient.productMsg();
        log.info("response={}",response);
        return response;
    }*/
    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> list = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response={}", list);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875227953464068", 2), new DecreaseStockInput("164103465734242707", 2)));
        return "ok";
    }
}
