package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.service.PirkimasService;
import com.prekes.web.prekesweb.service.ZmogusService;


//only ZmogusRestController context is needed for this test, not the whole application
@WebMvcTest(value = ZmogusRestController.class) 
class ZmogusRestControllerTest {
	@Autowired
	private MockMvc mockMvc; // allows to make a call to the service
	
	// use @MockBean to add mock objects to the Spring application context. 
	@MockBean
	private ZmogusService zmogusService;
	
	@MockBean
	PirkimasService pirkimasService;
	
	@Test
	void testZmonesJson() throws Exception {
		List<Zmogus> l = new ArrayList<Zmogus>();
		l.add(new Zmogus(5, "AAA"));
		l.add(new Zmogus(6, "BBB"));
		when(zmogusService.findAll()).thenReturn(l);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/zmones")
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(rb)
        				.andExpect(MockMvcResultMatchers.status().isOk())
        				.andReturn();

		String expected = "[{\"id\":5,\"vardas\":\"AAA\"},{\"id\":6,\"vardas\":\"BBB\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	void testZmogusById() throws Exception {
		Zmogus zm = new Zmogus(1, "AAA");
		when(zmogusService.findById(Mockito.anyInt())).thenReturn(zm);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/zmones/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(rb)
        				.andExpect(MockMvcResultMatchers.status().isOk())
        				.andReturn();

		String expected = "{\"id\":1,\"vardas\":\"AAA\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	void testFindPirkimaiForZmogus() throws Exception {
        List<Pirkimas> mockList = Arrays.asList(
                new Pirkimas(1, 1, 1, "1111-11-11"),
                new Pirkimas(1, 2, 1, "1111-11-11")
                );

        when(zmogusService.findPirkimaiByCustomerId(Mockito.anyInt())).thenReturn(mockList);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/zmones/1/pirkimai")
                                .accept(MediaType.APPLICATION_JSON))
                				.andExpect(MockMvcResultMatchers.status().isOk())
                				.andReturn();

        String expected = "["
                + "{\"zmogausId\":1,\"prekesKodas\":1,\"vnt\":1,\"date\":\"1111-11-11\",\"prekesPav\":null},"
                + "{\"zmogausId\":1,\"prekesKodas\":2,\"vnt\":1,\"date\":\"1111-11-11\",\"prekesPav\":null}"        		
                + "]";
        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	void testAddPirkimasToZmogus() throws Exception {
		
		Pirkimas mockPirkimas = new Pirkimas(1, 1, 1, "1111-11-11");
		when(pirkimasService.add(Mockito.any(Pirkimas.class))).thenReturn(mockPirkimas);

		String pirkimasJson = "{\"zmogausId\":1,\"prekesKodas\":1,\"vnt\":1,\"date\":\"1111-11-11\",\"prekesPav\":null}";
		
		//Send pirkimas as POST request body to /zmones/1/pirkimai
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(
				"/zmones/1/pirkimai")
				.accept(MediaType.APPLICATION_JSON)
				.content(pirkimasJson)					  // !!! sending content in POST request body
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		// check the response status
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		//now check the response header
		// pirkimoId = "zmogausId-prekesKodas-data"
		assertEquals("http://localhost/zmones/1/pirkimai/1-1-1111-11-11", response.getHeader(HttpHeaders.LOCATION));
    
	}

	@Test
	void testRetrieveDetailsForPirkimas() throws Exception {
		//1) setup:
		when(pirkimasService.findById(Mockito.anyString()) ).thenReturn( new Pirkimas(1,1,1,"1111-11-11") );
		
		//2) invocation:
		//send GET request to url "/zmones/{zmogausId}/pirkimai/{pirkimoId}" and accept JSON response
		RequestBuilder rb = MockMvcRequestBuilders.get("/zmones/1/pirkimai/1-1-1111-11-11").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(rb).andReturn();

		//3) verify:		
		String expected = "{\"zmogausId\":1,\"prekesKodas\":1,\"vnt\":1,\"date\":\"1111-11-11\",\"prekesPav\":null}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

}
