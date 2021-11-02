package com.HelloWebApp.HelloWebApp.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
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

@WebMvcTest(value = TelNrRestController.class)
class TelNrRestControllerTest {

    private TelNr mockTelNr1;
    private TelNr mockTelNr2;
    private List<TelNr> mockSaskaitos;

    @BeforeEach
    public void setup(){
        mockTelNr1 = new TelNr("861111111", 1);
        mockTelNr2 = new TelNr("862222222" ,2);

        mockSaskaitos = new ArrayList<TelNr>();
        mockSaskaitos.add(mockTelNr1);
        mockSaskaitos.add(mockTelNr2);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TelNrService serviceMock;
    @MockBean
    SaskaitaService saskaiaServiceMock;

    @Test
    void getTelNrTest() throws Exception {
        when(serviceMock.findAll()).thenReturn(mockSaskaitos);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/telNr")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "[{\"id\": 0,\"nr\": \"861111111\",\"userId\": 1},{\"id\": 0,\"nr\": \"862222222\",\"userId\": 2}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getTelNrByIdTest() throws Exception {
        when(serviceMock.findById(anyInt())).thenReturn(mockTelNr1);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/telNr/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "{\"id\": 0,\"nr\": \"861111111\",\"userId\": 1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
