package tetris;

import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestFigura {
	
	/**
	 * Comprova si dues posicions són iguals.
	 */
	private boolean posicionsIguals(Posicio posicio1, Posicio posicio2) {
		return (posicio1.getX() == posicio2.getX()) && (posicio1.getY() == posicio2.getY());
	}
	
	/**
	 * La funció rotar gestiona les rotacions de la figura que s’està movent. Quan 
	 * el jugador clica la tecla de rotar, es crida aquesta funció, que comprova si 
	 * amb la següent rotació de la figura aquesta solaparà amb algun element del 
	 * tauler. En cas de que no solapi amb res, realitza el gir.
	 */
	@Test
	public void testRotar() {		
	// Tauler buït
		Tauler tauler = new Tauler();
		Posicio posicioInicial = new Posicio(5, 5);
		
		// Figures amb 1 rotació
		Figura figura = new Figura(Figura.O, posicioInicial);
		assertEquals(1, figura.getRotacioActual());
		figura.rotar(tauler);
		assertEquals(1, figura.getRotacioActual());
		
		// Figures amb 2 rotacions
		for (int i = Figura.I; i <= Figura.S; i++) {
			figura = new Figura(Figura.Z, posicioInicial);
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(2, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}
		
		// Figures amb 4 rotacions
		for (int i = Figura.L; i <= Figura.P; i++) {
			figura = new Figura(i, posicioInicial);
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(2, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(3, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(4, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}		
		
	// Tauler amb figures encaixades
		TestTauler.omplirPosicio(tauler, Color.BLUE, 5, 5);
		TestTauler.omplirPosicio(tauler, Color.BLUE, 5, 6);
		TestTauler.omplirPosicio(tauler, Color.BLUE, 6, 5);
		TestTauler.omplirPosicio(tauler, Color.BLUE, 6, 6);
	
		
		//Figura amb index no contemplat
		figura = new Figura(20, new Posicio(0,0));
		assertEquals(1, figura.getRotacioActual());
		
		// Figures amb 1 rotació
		figura = new Figura(Figura.O, new Posicio(3, 5));
		assertEquals(1, figura.getRotacioActual());
		figura.rotar(tauler);
		assertEquals(1, figura.getNRotacions());
		
		// Figures amb 2 rotacions
			// Pot rotar
		figura = new Figura(Figura.I, new Posicio(1, 5));
		assertEquals(1, figura.getRotacioActual());
		figura.rotar(tauler);
		assertEquals(2, figura.getRotacioActual());
		figura.rotar(tauler);
		assertEquals(1, figura.getRotacioActual());
		
		for (int i = Figura.Z; i <= Figura.S; i++) {
			figura = new Figura(i, new Posicio(1, 5));
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(2, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}
		
			// No pot rotar
		figura = new Figura(Figura.I, new Posicio(4, 5));
		assertEquals(1, figura.getRotacioActual());
		figura.rotar(tauler);
		assertEquals(1, figura.getRotacioActual());
		
		for (int i = Figura.Z; i <= Figura.S; i++) {
			figura = new Figura(i, new Posicio(5, 4));
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}
		
		// Figures amb 4 rotacions
			// Si pot rotar
		for (int i = Figura.L; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(5, 2));
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(2, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(3, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(4, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}
		
			// No pot rotar
		for (int i = Figura.L; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(5, 4));
			assertEquals(1, figura.getRotacioActual());
			figura.rotar(tauler);
			assertEquals(1, figura.getRotacioActual());
		}
		
	}
	
	/**
	 * La funció moure s’encarrega de gestionar el moviments horitzontals de la 
	 * figura. Aquests moviments són els que indica el jugador mitjançant les tecles
	 * de moviment esquerra o dret. Quan es vol moure una figura, primer es comprova
	 * si amb el moviment a realitzar solaparà d’alguna manera amb el tauler i el 
	 * seu contingut. En cas de que no solapi, es realitzarà el moviment.
	 */
	@Test
	public void testMoure() {
	// Tauler buït
		Tauler tauler = new Tauler();
	
		// Posicio inicial al centre del tauler
		Posicio posicioInicial = new Posicio(5, 0);
		Posicio movimentEsquerra = new Posicio(4, 0);
		
		for (int i = Figura.O; i <= Figura.P; i++) {
			Figura figura = new Figura(i, new Posicio(5, 0));
			
			figura.moure(-1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), movimentEsquerra));
			figura.moure(1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));		
		}
		
		// Posicio inicial al límit esquerra del tauler
		posicioInicial = new Posicio(0, 0);
		Posicio movimentDreta = new Posicio(1, 0);
		
		for (int i = Figura.O; i <= Figura.P; i++) {
			Figura figura = new Figura(i, new Posicio(0, 0));
			
			figura.moure(-1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
			figura.moure(1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), movimentDreta));
		}
		
		// Posicio inicial al límit dret del tauler
			
			// Amplada = 1
		posicioInicial = new Posicio(9, 0);
		movimentEsquerra = new Posicio(8, 0);
		Figura figura = new Figura(Figura.I, new Posicio(9, 0));
		
		figura.moure(1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		figura.moure(-1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), movimentEsquerra));
		
			// Amplada = 2
		posicioInicial = new Posicio(8, 0);
		movimentEsquerra = new Posicio(7, 0);
		figura = new Figura(Figura.O, new Posicio(8, 0));
		
		figura.moure(1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		figura.moure(-1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), movimentEsquerra));
		
			// Amplada = 3
		posicioInicial = new Posicio(7, 0);
		movimentEsquerra = new Posicio(6, 0);
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(7, 0));
			
			figura.moure(1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
			figura.moure(-1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), movimentEsquerra));
		}		
	
	// Tauler amb figures encaixades
		TestTauler.omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 1, 6);
		TestTauler.omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 2, 6);
		TestTauler.omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 3, 6);
		TestTauler.omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 4, 6);
		
		// Moviment cap a l'esquerra, on es troba la figura encaixada
		posicioInicial = new Posicio(7, Tauler.nFiles - 4);
		for (int i = Figura.O; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(7, Tauler.nFiles - 4));
			
			figura.moure(-1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		}
		
		// Moviment cap a la dreta, on es troba la figura encaixada
			// Amplada = 1
		posicioInicial = new Posicio(5, Tauler.nFiles - 4);
		figura = new Figura(Figura.I, new Posicio(5, Tauler.nFiles - 4));
		
		figura.moure(1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		
			// Amplada = 2
		posicioInicial = new Posicio(4, Tauler.nFiles - 3);
		figura = new Figura(Figura.O, new Posicio(4, Tauler.nFiles - 3));
		
		figura.moure(1, tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		
			// Amplada = 3
		posicioInicial = new Posicio(3, Tauler.nFiles - 3);		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(3, Tauler.nFiles - 3));
			
			figura.moure(1, tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioInicial));
		}	
		
		
	}
	
	/**
	 * La funció caure fa que la figura que s’estigui movent baixi una posició 
	 * respecte a la actual. S’utilitzarà aquesta funció en cada torn i quan el 
	 * jugador cliqui la tecla de baixar. Abans de realitzar el moviment, es 
	 * comprova si és possible fer-ho perquè no solapa amb cap element.
	 */
	@Test
	public void testCaure() {
	// Tauler buit
		Tauler tauler =  new Tauler();
		// Posició inicial en la part superior del Tauler
		Posicio posicioFinal = new Posicio(5, 1);
		for (int i = Figura.O; i <= Figura.P; i++ ) {
			Figura figura = new Figura(i, new Posicio(5, 0));
			figura.caure(tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioFinal));
		}
		
		// Posició inicial en la part inferior del Tauler (valor frontera)
		posicioFinal = new Posicio(5, 20);
		for (int i = Figura.O; i <= Figura.P; i++) {
			Figura figura = new Figura(i, new Posicio(5, 20));
			figura.caure(tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioFinal));
		}
		
		// Posició inicial en una posició abans de la part inferior del Tauler
		// (valor límit interior).
		// Alçada = 4
		Figura figura = new Figura(Figura.I, new Posicio(0, 16));
		figura.caure(tauler);
		assertFalse(posicionsIguals(figura.getPosicio(), posicioFinal));
		
		// Alçada = 2
		figura = new Figura(Figura.O, new Posicio(0, 18));
		figura.caure(tauler);
		assertFalse(posicionsIguals(figura.getPosicio(), posicioFinal));
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(0, 18));
			figura.caure(tauler);
			assertFalse(posicionsIguals(figura.getPosicio(), posicioFinal));				
		}
		
	// Tauler amb Figures encaixades
		TestTauler.omplirFiles(tauler, Tauler.nFiles - 1, Tauler.nFiles - 1);
		
		// Alçada = 4
		posicioFinal = new Posicio(0, Tauler.nFiles - 5);
		figura = new Figura(Figura.I, new Posicio(0, Tauler.nFiles - 5));
		figura.caure(tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioFinal));
		
		// Alçada = 2
		posicioFinal = new Posicio(0, Tauler.nFiles - 3);
		figura = new Figura(Figura.O, new Posicio(0, Tauler.nFiles - 3));
		figura.caure(tauler);
		assertTrue(posicionsIguals(figura.getPosicio(), posicioFinal));
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(0, Tauler.nFiles - 3));
			figura.caure(tauler);
			assertTrue(posicionsIguals(figura.getPosicio(), posicioFinal));
		}
	}

}
