package com.superz.activemqspringboot.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zhang.chao
 * @date 2020/12/2
 */
@Component
public class JmsMessageListener {

    @JmsListener(destination = "VTC.A.testTopic", containerFactory = "jmsListenerContainerFactory")
    public void receive1Message(String message){
        System.out.println("1 接收到消息： " + message);
    }

    @JmsListener(destination = "VTC.B.testTopic", containerFactory = "jmsListenerContainerFactory")
    public void receive2Message(String message){
        System.out.println("2 接收到消息： " + message);
    }

    @JmsListener(destination = "VTC.B.testTopic", containerFactory = "jmsListenerContainerFactory")
    public void receive3Message(String message){
        System.out.println("3 接收到消息： " + message);
    }
}
