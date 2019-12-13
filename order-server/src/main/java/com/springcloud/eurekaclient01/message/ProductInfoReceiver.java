package com.springcloud.eurekaclient01.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.eurekaclient01.form.JsonFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 14:55 2019/12/12
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        ObjectMapper mapper = new ObjectMapper();
        JsonFrom jsonFrom = new JsonFrom();
        try {
            jsonFrom = mapper.readValue(message,JsonFrom.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ProductInfoOutput> productInfoOutputList = jsonFrom.getProductInfoOutputList();
        log.info("从队列【{}】接收消息：{}","productInfo",productInfoOutputList);

        //存储到redis
        for(ProductInfoOutput productInfoOutput : productInfoOutputList){
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId())
                    ,String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
