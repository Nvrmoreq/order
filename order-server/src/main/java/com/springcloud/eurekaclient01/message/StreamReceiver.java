package com.springcloud.eurekaclient01.message;

import com.springcloud.eurekaclient01.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 9:58 2019/12/12
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT2)
    public String process(OrderVO message){
        log.info("StreamReceiver input1:{}",message);
        return "received";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process2(String message){
        log.info("StreamReceiver input2:{}",message);
    }
}
