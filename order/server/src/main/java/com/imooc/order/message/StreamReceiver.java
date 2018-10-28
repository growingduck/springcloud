package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {
//    @Autowired
//    private StreamClient streamClient;
//    @StreamListener("myMessage")
//    public void process(String msg){
//        log.info("StreamReceiver:{}",msg);
//    }

    /**
     * 接收orderDTO对象 消息
     * @param message
     */
    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message) {
        log.info("StreamReceiver: {}", message);
        return "received.";
    }

    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message) {
        log.info("StreamReceiver2: {}", message);
    }
}
