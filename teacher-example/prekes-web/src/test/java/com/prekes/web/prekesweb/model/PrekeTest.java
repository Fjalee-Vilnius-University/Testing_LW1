package com.prekes.web.prekesweb.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrekeTest {

	@Test
	void testPrekeIntStringStringFloat() {
		Preke p = new Preke("Pienas", "LT", 1);
		assertAll("Test Preke constructor",
				() -> assertEquals("Pienas", p.getPavadinimas()),
				() -> assertEquals("LT", p.getSalis()),
				() -> assertEquals(1, p.getKainaVnt())
				);
	}

	@Test
	void testCompareTo() {
		Preke p1 = new Preke(1, "Pienas", "LT", 1);
		Preke p2 = new Preke(1, "Pienas", "LT", 1);
		assertEquals(0, p1.compareTo(p2));
	}

	@Test
	void testEqualsObject() {
		Preke p1 = new Preke(1, "Pienas", "LT", 1);
		Preke p2 = new Preke(1, "Pienas", "LT", 1);
		assertTrue(p1.equals(p2));
	}

	@Test
	void testSetKodas() {
		Preke p = new Preke();
		assertThrows(NullPointerException.class, () -> {  p.setKodas(-1);  });
	}

	@Test
	void testSetPavadinimas() {
		Preke p = new Preke();
		assertThrows(NullPointerException.class, () -> {  p.setPavadinimas(null);  });
	}

}
