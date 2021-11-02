package com.HelloWebApp.HelloWebApp.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HelloWebApp.HelloWebApp.HelloWebAppApplication;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.junit.jupiter.api.*;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = HelloWebAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TelNrRestControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TelNrService telNrService;
    @Autowired
    SaskaitaService saskaitaService;

    @Test
    @Order(1)
    void addTelNr_correct() throws Exception {
        var expected1 = "[{\"id\":8,\"nr\":\"+37061111111\",\"userId\":1},{\"id\":9,\"nr\":\"+37062222222\",\"userId\":2},{\"id\":10,\"nr\":\"+37063333333\",\"userId\":3},{\"id\":11,\"nr\":\"+37064444444\",\"userId\":4},{\"id\":12,\"nr\":\"+37065555555\",\"userId\":5}]";
        var expected2 = "[{\"id\":8,\"nr\":\"+37061111111\",\"userId\":1},{\"id\":9,\"nr\":\"+37062222222\",\"userId\":2},{\"id\":10,\"nr\":\"+37063333333\",\"userId\":3},{\"id\":11,\"nr\":\"+37064444444\",\"userId\":4},{\"id\":12,\"nr\":\"+37065555555\",\"userId\":5},{\"id\":13,\"nr\":\"+37061111111\",\"userId\":1}]";

        //Act
        var responseAsString1 = restTemplate.getForObject("/telNr", String.class);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var sask = new TelNr("+37061111111", 1);
        var entity = new HttpEntity<TelNr>(sask, headers);
        var response = restTemplate.exchange("/telNr", HttpMethod.POST, entity, String.class);
        var temp = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        var responseAsString2 = restTemplate.getForObject("/telNr", String.class);

        //Assert
        JSONAssert.assertEquals(expected1, responseAsString1, false);
        JSONAssert.assertEquals(expected2, responseAsString2, false);
    }

    @Test
    @Order(2)
    void deleteTelNr_correct() throws Exception {
        var expected1 = "[{\"id\":8,\"nr\":\"+37061111111\",\"userId\":1},{\"id\":9,\"nr\":\"+37062222222\",\"userId\":2},{\"id\":10,\"nr\":\"+37063333333\",\"userId\":3},{\"id\":11,\"nr\":\"+37064444444\",\"userId\":4},{\"id\":12,\"nr\":\"+37065555555\",\"userId\":5},{\"id\":13,\"nr\":\"+37061111111\",\"userId\":1}]";
        var expected2 = "[{\"id\":9,\"nr\":\"+37062222222\",\"userId\":2},{\"id\":10,\"nr\":\"+37063333333\",\"userId\":3},{\"id\":11,\"nr\":\"+37064444444\",\"userId\":4},{\"id\":12,\"nr\":\"+37065555555\",\"userId\":5},{\"id\":13,\"nr\":\"+37061111111\",\"userId\":1}]";


        //Act
        var responseAsString1 = restTemplate.getForObject("/telNr", String.class);

        var params = new HashMap< String, String >();
        params.put("id", "8");
        restTemplate.delete("/telNr/{id}", params);
        var responseAsString2 = restTemplate.getForObject("/telNr", String.class);

        //Assert
        JSONAssert.assertEquals(expected1, responseAsString1, false);
        JSONAssert.assertEquals(expected2, responseAsString2, false);
    }
}
