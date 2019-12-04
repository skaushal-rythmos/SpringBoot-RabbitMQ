package com.example.demo.Listner;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer implements MessageListener {

	public void onMessage(Message IncomingMessage) {
		System.out.println("Unrouted message from RabbitMq is " + new String(IncomingMessage.getBody()));
	}

}