package com.springcloud.eurekaclient01.controller;

import com.springcloud.eurekaclient01.message.StreamClient;
import com.springcloud.eurekaclient01.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 10:01 2019/12/12
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId("111111");
        streamClient.output().send(MessageBuilder.withPayload(orderVO).build());
    }
}
