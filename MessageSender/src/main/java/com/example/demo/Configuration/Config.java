package com.example.demo.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	Map<String, Object> queueArguments = new HashMap<String, Object>();
	
	private void foo(){	
		queueArguments.put("x-expires", 1800000);
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
	Queue UnroutedMessagesQueue() {
		foo();
		return new Queue("UnroutedMessagesQueue", true ,false, false, queueArguments);
	}
	

	@Bean
	HeadersExchange headerExchange() {
		return new HeadersExchange("Exchanger");
	}

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("UnroutedMessagesExchanger");
	}

	@Bean
	Binding indianBinding(Queue IndianQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(IndianQueue).to(headerExchange).where("Header").matches("India");
	}

	@Bean
	Binding foreignBinding(Queue ForeignQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(ForeignQueue).to(headerExchange).where("Header").matches("Foreign");
	}

	@Bean
	Binding UnroutedMessagesBinding(Queue UnroutedMessagesQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(UnroutedMessagesQueue).to(fanoutExchange);
	}

}