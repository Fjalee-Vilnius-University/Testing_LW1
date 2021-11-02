package com.HelloWebApp.HelloWebApp.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = SaskaitaRestController.class)
class SaskaitaRestControllerTest {

    private Saskaita mockSaskaita1;
    private Saskaita mockSaskaita2;
    private List<Saskaita> mockSaskaitos;

    @BeforeEach
    public void setup(){
        mockSaskaita1 = new Saskaita(1, 1, 1, "861111111");
        mockSaskaita2 = new Saskaita(2, 2, 2, "862222222");

        mockSaskaitos = new ArrayList<Saskaita>();
        mockSaskaitos.add(mockSaskaita1);
        mockSaskaitos.add(mockSaskaita2);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SaskaitaService serviceMock;
    @MockBean
    TelNrService telNrServiceMock;

    @Test
    void getSaskaitaTest() throws Exception {
        when(serviceMock.findAll()).thenReturn(mockSaskaitos);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saskaita")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "[{\"id\": 0,\"telNrId\": 1,\"menuo\": 1,\"suma\": 1,\"telNr\": \"861111111\"},{\"id\": 0,\"telNrId\": 2,\"menuo\": 2,\"suma\": 2,\"telNr\": \"862222222\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getSaskaitaByIdTest() throws Exception {
        when(serviceMock.findById(anyInt())).thenReturn(mockSaskaita1);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saskaita/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "{\"id\": 0,\"telNrId\": 1,\"menuo\": 1,\"suma\": 1,\"telNr\": \"861111111\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
