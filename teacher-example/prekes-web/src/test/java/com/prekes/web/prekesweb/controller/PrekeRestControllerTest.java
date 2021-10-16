package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;

@WebMvcTest(value = PrekeRestController.class)
class PrekeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PrekeService service;
	
	@Test
	void testShowPrekesList() throws Exception {
		List<Preke> l = new ArrayList<Preke>();
		l.add(new Preke(1, "AAA", "LT", 1.0f));
		l.add(new Preke(2, "BBB" , "LV", 2.0f));

		//1) setup:
        when(service.findAll()).thenReturn(l);

		//2) invocation:
		//send GET request to url "/prekes" and accept JSON response
        RequestBuilder rb = MockMvcRequestBuilders
				.get("/prekes")
				.accept(MediaType.APPLICATION_JSON);
		//MvcResult result = mockMvc.perform(rb).andReturn();
        MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		//3) verify:
        String expected = "["
                + "{\"kodas\":1,\"pavadinimas\":\"AAA\",\"salis\":\"LT\",\"kainaVnt\":1.0},"
                + "{\"kodas\":2,\"pavadinimas\":\"BBB\",\"salis\":\"LV\",\"kainaVnt\":2.0}"        		
                + "]";
        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
