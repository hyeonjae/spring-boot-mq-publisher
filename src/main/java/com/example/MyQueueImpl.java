package com.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyQueueImpl implements MyQueue {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public boolean enqueue(Map msg) {
        try {
            this.rabbitTemplate.convertAndSend(msg);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
