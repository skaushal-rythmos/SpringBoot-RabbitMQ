package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.Places;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockMvcTests extends MessageSenderApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addPlaceTtest() throws Exception {
		Places places = new Places();
		places.setId("101");
		places.setCity("Vizag");
		places.setCountry("India");
		String jsonObject = objectMapper.writeValueAsString(places);
		// System.out.println(jsonObject);
		MvcResult result = mockMvc
				.perform(post("/send").content(jsonObject).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		// System.out.println(result);
		String resultContent = result.getResponse().getContentAsString();
		Assert.assertEquals(resultContent,
				"Sent to Rabbitmq Sucessfully with Id "+places.getId()+" and header value of message as "+places.getCountry()+".");
		System.out.println("Test Name : addPlaceTest() got passed!!");

	}

	@Test
	public void getAllPlacesTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/all").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		// System.out.println(resultContent);
		assertNotNull(resultContent);
		System.out.println("Test Name : getAllPlacesTest() got passed!!");
	}

}
