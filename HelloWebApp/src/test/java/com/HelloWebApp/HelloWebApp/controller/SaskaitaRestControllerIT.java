package com.HelloWebApp.HelloWebApp.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HelloWebApp.HelloWebApp.HelloWebAppApplication;
import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = HelloWebAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaskaitaRestControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    SaskaitaService saskaitaService;
    @Autowired
    TelNrService telNrService;

    @Test
    void addSaskaita_correct() throws Exception {
        var expected1 = "[{\"id\":1,\"telNrId\":8,\"menuo\":3,\"suma\":5,\"telNr\":\"+37061111111\"},{\"id\":2,\"telNrId\":8,\"menuo\":5,\"suma\":75,\"telNr\":\"+37061111111\"},{\"id\":3,\"telNrId\":8,\"menuo\":7,\"suma\":53,\"telNr\":\"+37061111111\"},{\"id\":4,\"telNrId\":10,\"menuo\":2,\"suma\":30,\"telNr\":\"+37063333333\"},{\"id\":5,\"telNrId\":9,\"menuo\":2,\"suma\":21,\"telNr\":\"+37062222222\"},{\"id\":6,\"telNrId\":9,\"menuo\":8,\"suma\":72,\"telNr\":\"+37062222222\"},{\"id\":7,\"telNrId\":12,\"menuo\":7,\"suma\":50,\"telNr\":\"+37065555555\"}]";
        var expected2 = "[{\"id\":1,\"telNrId\":8,\"menuo\":3,\"suma\":5,\"telNr\":\"+37061111111\"},{\"id\":2,\"telNrId\":8,\"menuo\":5,\"suma\":75,\"telNr\":\"+37061111111\"},{\"id\":3,\"telNrId\":8,\"menuo\":7,\"suma\":53,\"telNr\":\"+37061111111\"},{\"id\":4,\"telNrId\":10,\"menuo\":2,\"suma\":30,\"telNr\":\"+37063333333\"},{\"id\":5,\"telNrId\":9,\"menuo\":2,\"suma\":21,\"telNr\":\"+37062222222\"},{\"id\":6,\"telNrId\":9,\"menuo\":8,\"suma\":72,\"telNr\":\"+37062222222\"},{\"id\":7,\"telNrId\":12,\"menuo\":7,\"suma\":50,\"telNr\":\"+37065555555\"},{\"id\":13,\"telNrId\":9,\"menuo\":1,\"suma\":1,\"telNr\":\"+37062222222\"}]";

        //Act
        var responseAsString1 = restTemplate.getForObject("/saskaita", String.class);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var sask = new Saskaita(9, 1, 1, "+37061111111");
        var entity = new HttpEntity<Saskaita>(sask, headers);
        var response = restTemplate.exchange("/saskaita", HttpMethod.POST, entity, String.class);
        var temp = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        var responseAsString2 = restTemplate.getForObject("/saskaita", String.class);

        //Assert
        JSONAssert.assertEquals(expected1, responseAsString1, false);
        JSONAssert.assertEquals(expected2, responseAsString2, false);
    }

    @Test
    void deleteSaskaita_correct() throws Exception {
        var expected1 = "[{\"id\":1,\"telNrId\":8,\"menuo\":3,\"suma\":5,\"telNr\":\"+37061111111\"},{\"id\":2,\"telNrId\":8,\"menuo\":5,\"suma\":75,\"telNr\":\"+37061111111\"},{\"id\":3,\"telNrId\":8,\"menuo\":7,\"suma\":53,\"telNr\":\"+37061111111\"},{\"id\":4,\"telNrId\":10,\"menuo\":2,\"suma\":30,\"telNr\":\"+37063333333\"},{\"id\":5,\"telNrId\":9,\"menuo\":2,\"suma\":21,\"telNr\":\"+37062222222\"},{\"id\":6,\"telNrId\":9,\"menuo\":8,\"suma\":72,\"telNr\":\"+37062222222\"},{\"id\":7,\"telNrId\":12,\"menuo\":7,\"suma\":50,\"telNr\":\"+37065555555\"},{\"id\":13,\"telNrId\":9,\"menuo\":1,\"suma\":1,\"telNr\":\"+37062222222\"}]";
        var expected2 = "[{\"id\":2,\"telNrId\":8,\"menuo\":5,\"suma\":75,\"telNr\":\"+37061111111\"},{\"id\":3,\"telNrId\":8,\"menuo\":7,\"suma\":53,\"telNr\":\"+37061111111\"},{\"id\":4,\"telNrId\":10,\"menuo\":2,\"suma\":30,\"telNr\":\"+37063333333\"},{\"id\":5,\"telNrId\":9,\"menuo\":2,\"suma\":21,\"telNr\":\"+37062222222\"},{\"id\":6,\"telNrId\":9,\"menuo\":8,\"suma\":72,\"telNr\":\"+37062222222\"},{\"id\":7,\"telNrId\":12,\"menuo\":7,\"suma\":50,\"telNr\":\"+37065555555\"},{\"id\":13,\"telNrId\":9,\"menuo\":1,\"suma\":1,\"telNr\":\"+37062222222\"}]";

        //Act
        var responseAsString1 = restTemplate.getForObject("/saskaita", String.class);

        var params = new HashMap< String, String >();
        params.put("id", "1");
        restTemplate.delete("/saskaita/{id}", params);
        var responseAsString2 = restTemplate.getForObject("/saskaita", String.class);

        //Assert
        JSONAssert.assertEquals(expected1, responseAsString1, false);
        JSONAssert.assertEquals(expected2, responseAsString2, false);
    }
}
