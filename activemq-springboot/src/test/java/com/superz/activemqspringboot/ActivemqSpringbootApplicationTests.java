package com.superz.activemqspringboot;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
class ActivemqSpringbootApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	void contextLoads() {
		String message = "我是一条测试🐟数鱼🐟";
		jmsTemplate.convertAndSend(new ActiveMQTopic("testTopic"), message);
	}

}
