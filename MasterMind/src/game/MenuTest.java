package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MenuTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCambiarNombreXifres() {
		Menu menu = new Menu();
		
		// particions equivalents:
		// Correcte: 0 < x < 3
		// Incorrecte: x <= 0
		// Incorrecte: x >= 3
		
		// Valors frontera
		boolean canvi_1 = menu.canviarNombreXifres(1);
		assertTrue(canvi_1);
		
		boolean canvi_2 =  menu.canviarNombreXifres(2);
		assertTrue(canvi_2);
		
		// Valor límit exterior superior
		boolean canvi_3 = menu.canviarNombreXifres(3);
		assertFalse(canvi_3);
		
		// Valor límit exterior inferior
		boolean canvi_0 = menu.canviarNombreXifres(0);
		assertFalse(canvi_0);
		
	}

	@Test
	public void testVictoria() {
		Menu menu = new Menu();
		
		int[] resposta_0 = {0, 0, 0, 0};		
		boolean victoria_0 = menu.victoria(resposta_0);
		assertFalse(victoria_0);
		
		int[] resposta_1 = {1, 1, 1, 1};		
		boolean victoria_1 = menu.victoria(resposta_1);
		assertFalse(victoria_1);
		
		int[] resposta_2 = {0, 0, 1, 1};
		boolean victoria_2 = menu.victoria(resposta_2);
		assertFalse(victoria_2);
		
		int[] resposta_3 = {1, 2, 1, 2};
		boolean victoria_3 = menu.victoria(resposta_3);
		assertFalse(victoria_3);
		
		int[] resposta_4 = {2, 2, 2, 2};
		boolean victoria_4 = menu.victoria(resposta_4);
		assertTrue(victoria_4);
	}

}
