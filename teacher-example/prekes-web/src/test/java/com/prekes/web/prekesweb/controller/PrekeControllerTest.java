package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;

//https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-forms/
//https://gist.github.com/rajivrnair/627961c5cde37fcf768cab68c9f46a43
	
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PrekeController.class) // only PrekeControllerTest context is needed for this test, not the whole application
class PrekeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PrekeService service;
	
	@InjectMocks
	PrekeController prekeController;
	
	@Test
	void testShowAll() throws Exception {
		List<Preke> l = new ArrayList<Preke>();
		l.add(new Preke("AAA","LT",1));
		l.add(new Preke("BBB","LV",2));
		when(service.findAll()).thenReturn(l);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/list-prekes")
				.accept(MediaType.TEXT_HTML);
				
		MvcResult result = mockMvc.perform(rb)
        				.andExpect(MockMvcResultMatchers.status().isOk()) // 200
        				.andExpect(MockMvcResultMatchers.view().name("list-prekes"))
        				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-prekes.jsp"))
        				.andExpect(MockMvcResultMatchers.model().attributeExists("prekes"))
        				.andReturn();
	}
	
	@Test
    public void testShowAddPage() throws Exception {
		RequestBuilder rb = MockMvcRequestBuilders.get("/add-preke");

        MvcResult result = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("preke"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/preke.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("preke"))
				.andExpect(MockMvcResultMatchers.model().attribute("preke",  hasProperty("kodas", notNullValue())))
				.andExpect(MockMvcResultMatchers.model().attribute("preke",  hasProperty("pavadinimas", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("preke",  hasProperty("salis", emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("preke",  hasProperty("kainaVnt", equalTo(0.0f))))
				.andReturn();
    }
	
	@Test
    void testAdd() throws Exception {

		when(service.add(Mockito.any(Preke.class))).thenReturn(new Preke("AAA","LT",1));
		
		// Send POST request
		// Pass @ModelAttribute preke object with flashAttr()
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/add-preke")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pavadinimas", "AAA")		// params are packed to preke modelAttribute 
                .param("salis", "LT")
                .param("kainaVnt", "1")
                .flashAttr("preke", new Preke());
    
		mockMvc.perform(rb)
        .andExpect(MockMvcResultMatchers.status().isFound()) // 302
        .andExpect(MockMvcResultMatchers.view().name("redirect:/list-prekes"));
        
		verify(service).add(Mockito.any(Preke.class)); // verifying mock call 1 time
    }

	
}

