package com.prekes.web.prekesweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.service.PirkimasService;

@ExtendWith(MockitoExtension.class)
class PirkimasControllerTest {
	@Mock
	PirkimasService serviceMock;
	
//	@Spy
//	PirkimasService serviceSpy;

	@InjectMocks
	PirkimasRestController controller;
	
	@Test
	void testShowPirkimaiJson() {
		serviceMock.add(new Pirkimas());
		List<Pirkimas> pirkimai = controller.showPirkimaiJson();
		assertNotNull(pirkimai);
	}

}
