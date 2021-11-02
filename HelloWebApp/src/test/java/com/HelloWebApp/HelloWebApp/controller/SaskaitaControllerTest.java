package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SaskaitaController.class)
class SaskaitaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TelNrService telNrService;
    @MockBean
    SaskaitaService saskaitaService;

    @InjectMocks
    SaskaitaController saskaitaController;

    @Test
    void testShowAll() throws Exception {
        var saskaita1 = new Saskaita(1, 1, 1, "861111111");
        var saskaita2 = new Saskaita(2, 2, 2, "862222222");

        List<Saskaita> saskaitos = new ArrayList<Saskaita>();
        saskaitos.add(saskaita1);
        saskaitos.add(saskaita2);
        when(saskaitaService.findAll()).thenReturn(saskaitos);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-saskaita")
                .accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-saskaita"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-saskaita.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("saskaita"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-saskaita");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("saskaita"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/saskaita.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("saskaita"))
                .andExpect(MockMvcResultMatchers.model().attribute("saskaita",  hasProperty("telNrId", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("saskaita",  hasProperty("menuo", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("saskaita",  hasProperty("suma", notNullValue())))
                .andReturn();
    }

    @Test
    void testAdd() throws Exception {
        var telNrId = 2;
        var menuo = 2;
        var suma = 45;
        var telNr = "+370656765";

        when(saskaitaService.add(Mockito.any(Saskaita.class))).thenReturn(new Saskaita(telNrId, menuo, suma, telNr));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-saskaita")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("telNr", telNr)
                .param("menuo", String.valueOf(menuo))
                .param("suma", String.valueOf(suma))
                .param("telNrId", String.valueOf(telNrId));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-saskaita"));
    }
}