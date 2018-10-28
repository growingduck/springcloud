package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {
//    @RabbitListener(queues = "myQueue")
// 自动创建队列   @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //自动创建，并绑定交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")))
    public void process(String msg){
        log.info("Receiver: {}",msg);
    }

}
