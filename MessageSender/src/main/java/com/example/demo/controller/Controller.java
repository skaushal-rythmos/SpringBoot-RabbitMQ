package com.example.demo.controller;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Places;
import com.example.demo.model.PlacesObject;
import com.example.demo.model.PlacesService;

@RestController
public class Controller {

	@Autowired
	private PlacesService placesService;
	

	@Autowired
	private AmqpTemplate amqpTemplate;

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public PlacesObject getAllPlaces() {
		
		List<Places> jsonObject = placesService.getAllplaces();
		PlacesObject placesObject = new PlacesObject();
		placesObject.setPlacesObject(jsonObject);
		return placesObject;
		
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public String addPlace(@RequestBody Places place) {
		placesService.addplace(place);

		String messageToBeSentToQueue = "City: " + place.getCity() + " and Country: " + place.getCountry()
				+ " is added to RabbitQueue with Id:" + place.getId();
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("Header", place.getCountry());
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(messageToBeSentToQueue, messageProperties);
		amqpTemplate.send("Exchanger", "", message);

		return "Sent to Rabbitmq Sucessfully with Id "+place.getId()+" and header value of message as "+place.getCountry()+".";
	}

}
