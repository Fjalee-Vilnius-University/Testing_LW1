package com.HelloWebApp.HelloWebApp.controller;

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

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TelNrController.class)
class telNrControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TelNrService telNrService;
    @MockBean
    SaskaitaService saskaitaService;

    @InjectMocks
    TelNrController telNrController;

    @Test
    void testShowAll() throws Exception {
        TelNr telNr1 = new TelNr("861111111", 1);
        TelNr telNr2 = new TelNr("862222222", 2);

        List<TelNr> telNrs = new ArrayList<TelNr>();
        telNrs.add(telNr1);
        telNrs.add(telNr2);
        when(telNrService.findAll()).thenReturn(telNrs);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-telNr")
                .accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-telNr"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-telNr.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("telNr"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-telNr");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("telNr"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/telNr.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("telNr"))
                .andExpect(MockMvcResultMatchers.model().attribute("telNr",  hasProperty("nr", nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("telNr",  hasProperty("userId", notNullValue())))
                .andReturn();
    }

    @Test
    void testAdd() throws Exception {
        var nr = "862222222";
        var userId = 2;

        when(telNrService.add(Mockito.any(TelNr.class))).thenReturn(new TelNr(nr, userId));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-telNr")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nr", nr)
                .param("userId", String.valueOf(userId))
                .flashAttr("telNr", new TelNr());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-telNr"));
    }
}