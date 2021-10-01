package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PrekeControllerTest.class) // only PrekeControllerTest context is needed for this test, not the whole application
class PrekeControllerRestTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PrekeService service;
	
	@Test
	void testShowPrekesList() throws Exception {
		List<Preke> mockList = Arrays.asList(
				new Preke(1, "Pienas", "LT", 1),
				new Preke(2, "Duona", "LV", 2));

		//1) setup:
        when(service.findAll()).thenReturn(mockList);

		//2) invocation:
		//send GET request to url "/list-prekes-json" and accept JSON response
		RequestBuilder rb = MockMvcRequestBuilders.get("/list-prekes-json").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(rb).andReturn();

		System.out.println("~~~" + result.getResponse().getContentAsString());
		System.out.println("~~~");
		//3) verify:
        String expected = "["
                + "{\"kodas\":1,\"pavadinimas\":\"Pienas\",\"salis\":\"LT\",\"kainaVnt\":1.0},"
                + "{\"kodas\":2,\"pavadinimas\":\"Duona\",\"salis\":\"LV\",\"kainaVnt\":0.5}"        		
                + "]";
        //[{"kodas":1,"pavadinimas":"Pienas","salis":"LT","kainaVnt":1.0},{"kodas":2,"pavadinimas":"Duona","salis":"LV","kainaVnt":0.5},{"kodas":3,"pavadinimas":"Knyga","salis":"EST","kainaVnt":10.0}]
        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
