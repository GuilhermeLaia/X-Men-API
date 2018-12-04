package com.mercadolivre.xmen.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.xmen.config.App;
import com.mercadolivre.xmen.domain.Mutant;
import com.mercadolivre.xmen.request.Request;
import com.mercadolivre.xmen.service.IService;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class ControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private Controller controller;

	@MockBean
	private IService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.controller).build();
	}


	@Test
	public void testIsMutante() throws Exception {
		
		String jsonPost = getJsonPost();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/mutant").accept(MediaType.APPLICATION_JSON).content(jsonPost)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		System.out.println(response.getContentAsString());
		String expected = "true";

		assertEquals(expected, response.getContentAsString());
	}

	@Test
	public void testStats() throws Exception {
		
		Mockito.when(
				service.findStatus()).thenReturn(new Mutant(1, 0, 0));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/stats").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		System.out.println(response.getContentAsString());
		String expected = getJsonResponse();

		assertEquals(expected, response.getContentAsString());
	}
	
	private String getJsonPost() throws JsonProcessingException {
		
		Request request = new Request();
		request.setDna(new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		return mapper.writeValueAsString(request);
	}
	
	private String getJsonResponse() throws JsonProcessingException {
		
		Mutant request = new Mutant(1, 0, 0);
		return mapper.writeValueAsString(request);
	}

}
