package com.example.demo.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Listner.MessageConsumer;

@Configuration
public class Config {


	@Bean
	Queue UnroutedMessagesQueue() {
		return new Queue("UnroutedMessagesQueue", true);
	}
	
	@Bean
	Queue IndianQueue() {
		return new Queue("IndianQueue", true);
	}
	
	@Bean
	Queue ForeignQueue() {
		return new Queue("ForeignQueue", true);
	}

	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		simpleMessageListenerContainer.addQueues(UnroutedMessagesQueue(),IndianQueue(),ForeignQueue());
		simpleMessageListenerContainer.setMessageListener(new MessageConsumer());
		return simpleMessageListenerContainer;

	}

}