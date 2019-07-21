package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class NombresTest {
	
	@Test
	public void testNombreValid() {
		Nombres nombres = new Nombres();
		
		boolean valid_0 = nombres.nombreValid(0);
		assertFalse(valid_0);
		
		boolean valid_1 = nombres.nombreValid(1);
		assertTrue(valid_1);
		
		boolean valid_2 = nombres.nombreValid(2);
		assertTrue(valid_2);
		
		boolean valid_7 = nombres.nombreValid(7);
		assertTrue(valid_7);
		
		boolean valid_8 = nombres.nombreValid(8);
		assertTrue(valid_8);
		
		boolean valid_9 = nombres.nombreValid(9);
		assertFalse(valid_9);
		
	}
	
	@Test
	public void testGenerarNombreAleatori() {
		Nombres nombres = new Nombres();
		
		for (int i = 0; i < 20; i++) {
			int aleatori = nombres.generarNombreAleatori();
			boolean valid = nombres.nombreValid(aleatori);
			assertTrue(valid);
		}
		
	}

}
