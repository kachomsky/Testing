package game;

import static org.junit.Assert.*;
import org.junit.Test;

public class CombinacioTest {
	
	public class MockNombres extends Nombres {
		private int nombre = 0;
		
		public MockNombres() {
		}
		
		public int obtenirNombreValid(int i) {
			nombre++;
			return nombre;
		}
	}

	@Test
	public void testObtenirCombinacio() {
		// Probem per combinacions de 1 fins a 6 nombres.
		for (int i = 1; i < 7; i++) {
			MockNombres generadorNombres = new MockNombres();
			Combinacio combinacio = new Combinacio(i);
			int[] comb = combinacio.obtenirCombinacio(generadorNombres);
			
			for (int j = 0; j < i; j ++) {
				assertTrue("Nombre en la posicio " + j + " de resposta " + i + " fora de rang", 
						comb[j] > 0 && comb[j] < 9);
			}
		}
		
	}

}
