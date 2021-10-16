package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.service.PirkimasService;

@WebMvcTest(value = PirkimasRestController.class)
class PirkimasRestControllerTest {

	@Autowired
    private MockMvc mockMvc; // allows to make a call to the service
 
    @MockBean
    PirkimasService serviceMock;
 
    @Test
    void getPirkimasTestById() throws Exception {
        Pirkimas mockPirkimas = new Pirkimas(1, 1, 1, "2001-02-25");
        //1) setup:
        when(serviceMock.findById(Mockito.anyString())).thenReturn(mockPirkimas);
 
        //2) invocation:
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/pirkimai/1-1-2001-02-25")
                .accept(MediaType.APPLICATION_JSON);
 
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
 
        //3) verify:
        String expected = "{zmogausId:1, prekesKodas:1, vnt:1, date:\"2001-02-25\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
