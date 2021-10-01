package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;


//@RunWith(SpringRunner.class) // run context
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PrekeControllerTest.class) // only PrekeControllerTest context is needed for this test, not the whole application
class PrekeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PrekeService serviceMock;
	
	//@Autowired
	//PrekeService service;

	//@Autowired
	@InjectMocks
	PrekeController prekeController;
	
	/*@BeforeEach
	void setUp() {
		service = new PrekeService();
		//TODO
		prekeController = new PrekeController(service);
	}*/

	@Test
	void testShowPrekesView() {
		ModelMap model = new ModelMap();
		String view = prekeController.showPrekes(model);
		assertEquals("list-prekes", view, "Wrong view returned");
		
		assertNotNull(model.get("prekes"));
	}

}

