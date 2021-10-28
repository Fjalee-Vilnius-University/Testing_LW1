package com.prekes.web.prekesweb.controller;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.service.PirkimasService;
import com.prekes.web.prekesweb.service.ZmogusService;

//only ZmogusController context is needed for this test, not the whole application
@WebMvcTest(value = ZmogusController.class) 
class ZmogusControllerTest {

	// MockMvc provides support for Spring MVC testing. It prepares a fake web
	// application context to mock the HTTP requests and responses.
	// It encapsulates all web application beans and makes them available for
	// testing.
	// Allows to make a call to the service.
	@Autowired
	private MockMvc mockMvc;

	// WebApplicationContext provides a web application configuration. It loads all
	// the application beans and controllers into the context.
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
		assertNotNull(webApplicationContext.getBean("zmogusController")); // pirma kontrolerio pav-mo raide mazoji
	}

	@Test
	public void testShowAll() throws Exception {
		List<Zmogus> l = new ArrayList<Zmogus>();
		l.add(new Zmogus("AAA", "Admin"));
		l.add(new Zmogus("BBB", "User"));
		when(zmogusService.findAll()).thenReturn(l);

		RequestBuilder rb = MockMvcRequestBuilders.get("/list-zmones").accept(MediaType.TEXT_HTML);

		MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk()) // 200
				.andExpect(MockMvcResultMatchers.view().name("list-zmones"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-zmones.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("zmones"))
				.andReturn();
	}

	@Test
    public void testShowAddPage() throws Exception {
		RequestBuilder rb = MockMvcRequestBuilders.get("/add-zmogus");

        MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("zmogus"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/zmogus.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("zmogus"))
				.andExpect(MockMvcResultMatchers.model().attribute("zmogus",  hasProperty("vardas", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("zmogus",  hasProperty("role", emptyOrNullString())))
				.andReturn();
    }
	
	@Test
	void testAdd() throws Exception {

		Zmogus o = new Zmogus("vardas555", "Admin");
		when(zmogusService.add(Mockito.any(Zmogus.class))).thenReturn(o);

		// Send POST request
		// Pass @ModelAttribute Zmogus object with flashAttr()
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/add-zmogus")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas", "AAA")
				.param("role", "Admin")
				.flashAttr("zmogus", new Zmogus("AAA", "Admin"));

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()) // 302 -	status found after redirect
				.andExpect(MockMvcResultMatchers.redirectedUrl("/list-zmones")) // .andExpect(MockMvcResultMatchers.view().name("redirect:/list-zmones"))
				.andReturn();
		
		verify(zmogusService).add(Mockito.any(Zmogus.class)); // verifying mock call 1 time
	}

	@Test
    public void testShowUpdatePage() throws Exception {
		when(zmogusService.findById(Mockito.anyInt())).thenReturn(new Zmogus("AAA", "Admin"));
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/update-zmogus/1");

        MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("zmogus"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/zmogus.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("zmogus"))
				.andExpect(MockMvcResultMatchers.model().attribute("zmogus",  hasProperty("vardas", Matchers.equalTo("AAA"))))
				.andExpect(MockMvcResultMatchers.model().attribute("zmogus",  hasProperty("role", Matchers.equalTo("Admin"))))
				.andReturn();
    }
	
	@Test
	void testUpdate() throws Exception {
		// Send POST request
		// Pass @ModelAttribute Zmogus object with flashAttr()
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/update-zmogus/1")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas", "AAA")
				.param("role", "Admin")
				.flashAttr("zmogus", new Zmogus("AAA", "Admin"));

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()) // 302 -	status found after redirect
				.andExpect(MockMvcResultMatchers.redirectedUrl("/list-zmones")) // .andExpect(MockMvcResultMatchers.view().name("redirect:/list-zmones"))
				.andReturn();
		
		verify(zmogusService).update(Mockito.any(Zmogus.class)); // verifying mock call 1 time
	}
	
	@Test
	void testDelete() throws Exception {
		// Send POST request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete-zmogus/1");

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound()) // 302 -	status found after redirect
				.andExpect(MockMvcResultMatchers.redirectedUrl("/list-zmones")) // .andExpect(MockMvcResultMatchers.view().name("redirect:/list-zmones"))
				.andReturn();
		
		verify(zmogusService).deleteById(Mockito.anyInt()); // verifying mock call 1 time
	}
}
