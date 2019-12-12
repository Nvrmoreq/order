package com.springcloud.eurekaclient01.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.DestinationVariable;

/**
 * @Author: ZQ
 * @Description:
 * @Date created in 9:53 2019/12/12
 */
public interface StreamClient {

    String INPUT = "myMessageIn";

    String OUTPUT = "myMessageOut";

    String INPUT2 = "myMessageIn2";

    String OUTPUT2 = "myMessageOut2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT2)
    SubscribableChannel input1();

    @Output(StreamClient.OUTPUT2)
    MessageChannel output2();

}
