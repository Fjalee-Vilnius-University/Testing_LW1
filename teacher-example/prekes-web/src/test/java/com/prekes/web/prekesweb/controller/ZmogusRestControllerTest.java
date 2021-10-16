package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

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
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.service.PirkimasService;
import com.prekes.web.prekesweb.service.ZmogusService;

//Narrow tests to only the web layer by using @WebMvcTest annotation
//Only ZmogusRestController context is needed for this test, not the whole application
@WebMvcTest(value = ZmogusRestController.class) 
class ZmogusRestControllerTest {
	// MockMvc provides support for Spring MVC testing. It prepares a fake web application context to mock the HTTP requests and responses. 
	// It encapsulates all web application beans and makes them available for testing.
	// Allows to make a call to the service.
	@Autowired
	private MockMvc mockMvc; 
	
	//WebApplicationContext provides a web application configuration. It loads all the application beans and controllers into the context.
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	// use @MockBean to add mock objects to the Spring application context. 
	@MockBean
	private ZmogusService zmogusService;
	
	@MockBean
	private PirkimasService pirkimasService;

	// Verify that WebApplicationContext object is loaded properly. 
	// Verify that the right servletContext is being attached.
	// Verify that controller bean exists in web context.
	@Test
	public void givenWac_whenServletContext_thenItProvidesController() {
	    ServletContext servletContext = webApplicationContext.getServletContext();
	    
	    assertNotNull(servletContext);
	    assertTrue(servletContext instanceof MockServletContext);
	    assertNotNull(webApplicationContext.getBean("zmogusRestController")); // pirma kontrolerio pav-mo raide mazoji
	}
	
	@Test
	void testZmonesJson() throws Exception {
		List<Zmogus> l = new ArrayList<Zmogus>();
		l.add(new Zmogus("AAA", "Admin"));
		l.add(new Zmogus("BBB", "User"));
		when(zmogusService.findAll()).thenReturn(l);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/zmones")
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(rb)
        				.andExpect(MockMvcResultMatchers.status().isOk()) // 200
        				.andReturn();

		String expected = "[{\"vardas\":\"AAA\",role:\"Admin\"},{\"vardas\":\"BBB\",role:\"User\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	void testZmogusById() throws Exception {
		Zmogus zm = new Zmogus("AAA", "Admin");
		when(zmogusService.findById(Mockito.anyInt())).thenReturn(zm);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/zmones/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(rb)
        				.andExpect(MockMvcResultMatchers.status().isOk())
        				.andReturn();

		String expected = "{\"vardas\":\"AAA\",role:\"Admin\"}";
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
				.post("/zmones/1/pirkimai")
				//.accept(MediaType.APPLICATION_JSON)
				.content(pirkimasJson)					  // !!! sending content in POST request body
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		// check the response status
		assertEquals(HttpStatus.CREATED.value(), response.getStatus()); // created 201

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
