package tetris;

import static org.junit.Assert.*;
import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.awt.Color;

public class TestTauler {
	
	/**
	 * Omple algunes files del Tauler.
	 */
	public static void omplirFiles(Tauler tauler, int filaInicial, int filaFinal) {
		Color[][] taulerColors = tauler.getTauler();
		for (int i = filaInicial; i <= filaFinal; i++) {
			for (int j = 0; j < Tauler.nColumnes; j++) {
				taulerColors[i][j] = Color.BLUE;
			}
		}
	}
	
	/**
	 * Omple una posició concreta del Tauler amb el color indicat.
	 */
	public static void omplirPosicio(Tauler tauler, Color color, int fila, int columna) {
		Color[][] taulerColors = tauler.getTauler();
		taulerColors[fila][columna] = color;
	}
	
	/**
	 * Comprova si dos taulers són iguals.
	 */
	public static boolean taulersIguals(Color[][] tauler1, Color[][] tauler2) {		
		for (int fila = 0; fila < Tauler.nFiles; fila++) {
			for (int columna = 0; columna < Tauler.nColumnes; columna++) {
				if (tauler1[fila][columna] != tauler2[fila][columna]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * La funció posicioValida s’encarrega de comprovar si la posició donada per 
	 * paràmetre existeix dins del Tauler. La seva utilitat dins del codi del joc 
	 * és evitar que s’intenti accedir a posicions del Tauler que estan fora del 
	 * rang de la matriu i que sorgeixin errors.
	 */
	@Test
	public void testPosicioValida() {
		Tauler tauler = new Tauler();
		
		// Valors límits exteriors
		Posicio posicio = new Posicio(-1, 0);
		assertFalse(tauler.posicioValida(posicio));
		
		posicio = new Posicio(0, -1);
		assertFalse(tauler.posicioValida(posicio));
		
		posicio = new Posicio(Tauler.nColumnes, 0);
		assertFalse(tauler.posicioValida(posicio));
		
		posicio = new Posicio(0, Tauler.nFiles);
		assertFalse(tauler.posicioValida(posicio));
		
		// Valors límits interiors		
		posicio = new Posicio(2, 0);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(0, 2);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(Tauler.nColumnes - 2, 0);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(0, Tauler.nFiles - 2);
		assertTrue(tauler.posicioValida(posicio));
		
		// Valors frontera
			// Cantonades del tauler
		posicio = new Posicio(0, 0);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(Tauler.nColumnes - 1, 0);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(0, Tauler.nFiles - 1);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(Tauler.nColumnes - 1, Tauler.nFiles - 1);
		assertTrue(tauler.posicioValida(posicio));
		
			// Posicions intermitges
		posicio = new Posicio(0, 5);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(5, 0);
		assertTrue(tauler.posicioValida(posicio));		
		
		posicio = new Posicio(5, Tauler.nFiles -1);
		assertTrue(tauler.posicioValida(posicio));
		
		posicio = new Posicio(Tauler.nColumnes - 1, 5);
		assertTrue(tauler.posicioValida(posicio));
		
	}
	
	/**
	 * La funció solapa() s'encarrega de comprovar si la figura que s'ha de col·locar solapa amb els límits 
	 * del Tauler o alguna figura que ja ha sigut col·locada.
	 */
	@Test
	public void testSolapa() {
	// Tauler buit (solapa amb els límits)
		Tauler tauler = new Tauler();
		
		// Fora del tauler (valors límit exteriors)
		
			for (int i = Figura.O; i <= Figura.P; i++) {
				Figura figura = new Figura(i, new Posicio(0, 0));
								
			// Solapa amb el límit esquerra
				figura.setPosicio(new Posicio(-1, 0));
				assertTrue(tauler.solapa(figura, 0, 0));
				
			// Solapa amb el límit dret
				figura.setPosicio(new Posicio(11, 0));
				assertTrue(tauler.solapa(figura, 0, 0));
				
			// Solapa amb el límit inferior
				figura.setPosicio(new Posicio(5, 20));
				assertTrue(tauler.solapa(figura, 0, 0));
			}
		
		// Valors frontera: just als límits del tauler
			// No solapa amb el límit esquerra
			for (int i = Figura.O; i <= Figura.P; i++) {
				Figura figura = new Figura(i, new Posicio(0, 5));
				assertFalse(tauler.solapa(figura, 0, 0));
			}
		
			// No solapa amb el límit dret
				// Amplada = 1
				Figura figura = new Figura(Figura.I, new Posicio(9, 0));
				assertFalse(tauler.solapa(figura, 0, 0));
			
				// Amplada = 2
				figura = new Figura(Figura.O, new Posicio(8, 0));
				assertFalse(tauler.solapa(figura, 0, 0));
				
				// Amplada = 3
				for (int i = Figura.Z; i <= Figura.P; i++) {
					figura = new Figura(i, new Posicio(7, 0));
					assertFalse(tauler.solapa(figura, 0, 0));
				}
			
			// No solapa amb el límit inferior
				// Alçada = 4
				figura = new Figura(Figura.I, new Posicio(0, 17));
				assertFalse(tauler.solapa(figura, 0, 0));
				
				// Alçada = 2
				figura = new Figura(Figura.O, new Posicio(0, 19));
				assertFalse(tauler.solapa(figura, 0, 0));
				
				for (int i = Figura.Z; i <= Figura.P; i++) {
					figura = new Figura(i, new Posicio(0, 19));
					assertFalse(tauler.solapa(figura, 0, 0));					
				}
		
		// Dins del tauler (valor límit interior)
			
			for (int i = Figura.O; i <= Figura.P; i++) {
				figura = new Figura(i, new Posicio(1, 0));
			// No solapa pel límit esquerra
				assertFalse(tauler.solapa(figura, 0, 0));
			}
			
			// No solapa pel límit inferior
				// Alçada = 4
			figura = new Figura(Figura.I, new Posicio(0, 17));
			assertFalse(tauler.solapa(figura, 0, 0));
			
				// Alçada = 2
			figura = new Figura(Figura.O, new Posicio(0, 19));
			assertFalse(tauler.solapa(figura, 0, 0));
			
			for (int i = Figura.Z; i <= Figura.P; i++) {
				figura = new Figura(i, new Posicio(0, 19));
				assertFalse(tauler.solapa(figura, 0, 0));					
			}
			
			// No solapa pel límit dret
				// Amplada = 1
			figura = new Figura(Figura.I, new Posicio(9, 0));
			assertFalse(tauler.solapa(figura, 0, 0));
		
				// Amplada = 2
			figura = new Figura(Figura.O, new Posicio(8, 0));
			assertFalse(tauler.solapa(figura, 0, 0));
			
				// Amplada = 3
			for (int i = Figura.Z; i <= Figura.P; i++) {
				figura = new Figura(i, new Posicio(7, 0));
				assertFalse(tauler.solapa(figura, 0, 0));
			}
	
	// Tauler amb figures encaixades
		tauler = new Tauler();
		omplirPosicio(tauler, Color.YELLOW, 20, 5);
		omplirPosicio(tauler, Color.YELLOW, 20, 6);
		omplirPosicio(tauler, Color.YELLOW, 19, 5);
		omplirPosicio(tauler, Color.YELLOW, 19, 6);
		
		// Per la part dreta de la figura encaixada
		figura = new Figura(Figura.I, new Posicio(4, 17));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, 1, 0));
	
		figura = new Figura(Figura.O, new Posicio(3, 19));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, 1, 0));
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(2, 19));
			// No solapa
			assertFalse(tauler.solapa(figura, 0, 0));	
			// Si solapa
			assertTrue(tauler.solapa(figura, 1, 0));
		}
		
		// Per la part superior de la figura encaixada
		figura = new Figura(Figura.I, new Posicio(5, 15));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, 0, 1));
		
		figura = new Figura(Figura.O, new Posicio(5, 17));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, 0, 1));
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(5, 17));
			// No solapa
			assertFalse(tauler.solapa(figura, 0, 0));	
			// Si solapa
			assertTrue(tauler.solapa(figura, 0, 1));
		}
		
		// Per la part esquerra de la figura encaixada
		figura = new Figura(Figura.I, new Posicio(7, 17));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, -1, 0));
		
		figura = new Figura(Figura.O, new Posicio(7, 19));
			// No solapa
		assertFalse(tauler.solapa(figura, 0, 0));
			// Si solapa
		assertTrue(tauler.solapa(figura, -1, 0));
		
		for (int i = Figura.Z; i <= Figura.P; i++) {
			figura = new Figura(i, new Posicio(7, 19));
			// No solapa
			assertFalse(tauler.solapa(figura, 0, 0));	
			// Si solapa
			assertTrue(tauler.solapa(figura, -1, 0));
		}
		
		//path coverage (al document de caixa blanca estan indicats els paths escollits.
		
		//1.(1A, 2B, 3C, 4E, 10) Mai es complira, ja que tenim totes les figues amb almenys una mascara
		//2.(1A, 2B, 3C, 4F, 5G, 6H, 7I, 8J, 9D, 4E, 10) //posicio valida, color != BLACK, solapa true
		Tauler tauler2 = new Tauler();
		omplirPosicio(tauler2, Color.YELLOW, 20, 5);
		Figura figura2 = new Figura(Figura.I, new Posicio(20, 5));
		assertTrue(tauler2.solapa(figura2, 0, 0));
		//3.(1A, 2B, 3C, 4F, 5G, 6H, 7I, 8L, 6H, 7I, 8J, 9D, 4E, 10) //posicio no valida, solapa true
		figura2 = new Figura(Figura.I, new Posicio(30, 30));
		assertTrue(tauler2.solapa(figura2, 0, 0));
		//4.(1A, 2B, 3C, 4F, 5G, 6H, 7K, 9D, 4E, 10) //posicio valida, color == BLACK, solapa false
		tauler2 = new Tauler();
		omplirPosicio(tauler2, Color.YELLOW, 20, 5);
		omplirPosicio(tauler2, Color.YELLOW, 20, 6);
		omplirPosicio(tauler2, Color.YELLOW, 19, 5);
		omplirPosicio(tauler2, Color.YELLOW, 19, 6);
		
		figura2 = new Figura(Figura.I, new Posicio(4, 17));
		assertFalse(tauler2.solapa(figura2, 0, 0));
	}
	
	/**
	 * La funció eliminarFilaCompletada s’encarrega d’eliminar una fila que ja ha 
	 * estat completada. Quan la elimina, desplaça les files que es trobaven a 
	 * sobre d’ella una posició cap a baix. En el cas de que es completi la fila 
	 * superior, aquesta quedarà buida.
	 */
	@Test
	public void testEliminarFilesCompletades() {
		Tauler tauler = new Tauler();
		Tauler taulerBuit = new Tauler();
				
		// Última fila del Tauler completada (fila inferior)
			// Una sola fila
		omplirFiles(tauler, Tauler.nFiles - 1, Tauler.nFiles - 1);
		tauler.eliminarFilaCompletada(Tauler.nFiles - 1);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 1);
		for (int fila = Tauler.nFiles - 3; fila <= Tauler.nFiles - 1; fila++) {
			tauler.eliminarFilaCompletada(fila);
		}
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 3);
		omplirFiles(tauler, Tauler.nFiles - 1, Tauler.nFiles - 1);
		tauler.eliminarFilaCompletada(Tauler.nFiles - 3);
		tauler.eliminarFilaCompletada(Tauler.nFiles - 1);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		
		// Primera fila del Tauler completada (fila superior)
			// Una sola fila
		tauler = new Tauler();
		omplirFiles(tauler, 0, 0);
		tauler.eliminarFilaCompletada(0);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, 0, 2);
		for (int fila = 0; fila <= 2; fila++) {
			tauler.eliminarFilaCompletada(fila);
		}
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, 0, 0);
		omplirFiles(tauler, 2, 2);
		tauler.eliminarFilaCompletada(0);
		tauler.eliminarFilaCompletada(2);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		
		// Algunes files qualsevol
			// Una sola fila
		tauler = new Tauler();
		omplirFiles(tauler, 10, 10);
		tauler.eliminarFilaCompletada(10);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, 10, 12);
		for (int fila = 10; fila <= 12; fila++) {
			tauler.eliminarFilaCompletada(fila);
		}
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, 10, 10);
		omplirFiles(tauler, 12, 12);	
		tauler.eliminarFilaCompletada(12);
		tauler.eliminarFilaCompletada(11);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		
		
		// Loop testing
		Tauler taulerSolucio = new Tauler();
		omplirFiles(taulerSolucio, 20, 20);
		
		tauler = new Tauler();
		omplirFiles(tauler, 20, 20);
		
			// Evitar el loop
		tauler.eliminarFilaCompletada(-1);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
			// 1 passada del loop
		tauler.eliminarFilaCompletada(0);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
			// 2 passades
		tauler.eliminarFilaCompletada(1);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
			// 10 passades (m < n)
		tauler.eliminarFilaCompletada(10);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));	
			// n-1 passades
		tauler.eliminarFilaCompletada(Tauler.nFiles - 1);
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));		
	}
	
	/**
	 * La funció comprovarFilesCompletades s’encarrega de mirar si en el tauler hi 
	 * ha alguna fila completada. En cas afirmatiu, les borrarà i desplaçarà les 
	 * altres files cap a posicions inferiors. 
	 */
	@Test
	public void testComprovarFilesCompletades() {
		int filesCompletades;
		
	// El tauler acaba buït
		Tauler taulerBuit = new Tauler();
		// No hi ha cap fila Completada
		Tauler tauler = new Tauler();
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 0);
		
		// Última fila del Tauler completada (fila inferior)
			// Una sola fila
		tauler = new Tauler();
		omplirFiles(tauler, Tauler.nFiles - 1, Tauler.nFiles - 1);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 1);
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 1);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 3);
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 3);
		omplirFiles(tauler, Tauler.nFiles - 1, Tauler.nFiles - 1);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 2);
		
		// Primera fila del Tauler completada (fila superior)
			// Una sola fila
		tauler = new Tauler();
		omplirFiles(tauler, 0, 0);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 1);
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, 0, 2);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 3);
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, 0, 0);
		omplirFiles(tauler, 2, 2);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 2);
		
		// Algunes files qualsevol
			// Una sola fila
		tauler = new Tauler();
		omplirFiles(tauler, 10, 10);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 1);
			// Més d'una fila seguida
		tauler = new Tauler();
		omplirFiles(tauler, 10, 12);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 3);
			// Dues files (però no seguides)
		tauler = new Tauler();
		omplirFiles(tauler, 10, 10);
		omplirFiles(tauler, 12, 12);
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerBuit.getTauler()));
		assertEquals(filesCompletades, 2);
		
	// En el tauler encara queden Figures encaixades (no acaba buït)
		Tauler taulerSolucio = new Tauler();
		// Es completa només la fila que es troba més adalt
		tauler = new Tauler();
		for (int fila = Tauler.nFiles - 1; fila < Tauler.nFiles - 3; fila--) {
			for (int columna = 1; columna < Tauler.nColumnes - 3; columna++) {
				omplirPosicio(tauler, Color.BLUE, fila, columna);
				omplirPosicio(taulerSolucio, Color.BLUE, fila, columna);
			}
		}
		omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 4, 5);
		omplirPosicio(taulerSolucio, Color.BLUE, Tauler.nFiles - 3, 5);
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 3);
		
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		assertEquals(filesCompletades, 1);
		
		// Es completa només la fila que es troba més abaix
		tauler = new Tauler();
		taulerSolucio = new Tauler();
		for (int fila = Tauler.nFiles - 3; fila < Tauler.nFiles - 5; fila--) {
			for (int columna = 1; columna < Tauler.nColumnes - 3; columna++) {
				omplirPosicio(tauler, Color.BLUE, fila - 1, columna);
				omplirPosicio(taulerSolucio, Color.BLUE, fila - 1, columna);
			}
		}
		omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 1, 5);
		omplirPosicio(taulerSolucio, Color.BLUE, Tauler.nFiles - 1, 5);
		omplirFiles(tauler, Tauler.nFiles - 2, Tauler.nFiles - 2);
		
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		assertEquals(filesCompletades, 1);
		
		// Es completa només una fila del mig
		tauler = new Tauler();
		taulerSolucio = new Tauler();
		for (int fila = Tauler.nFiles - 1; fila < Tauler.nFiles - 3; fila--) {
			for (int columna = 1; columna < Tauler.nColumnes - 3; columna++) {
				omplirPosicio(tauler, Color.BLUE, fila, columna);
				omplirPosicio(taulerSolucio, Color.BLUE, fila, columna);
			}
		}
		omplirPosicio(tauler, Color.BLUE, Tauler.nFiles - 4, 5);
		omplirPosicio(taulerSolucio, Color.BLUE, Tauler.nFiles - 3, 5);
		omplirFiles(tauler, Tauler.nFiles - 3, Tauler.nFiles - 3);
		
		filesCompletades = tauler.comprovarFilesCompletades();
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		assertEquals(filesCompletades, 1);
	}
	
	/**
	 * La funció encaixarFigura s’utiltiza per encaixar una figura en el tauler en 
	 * la posició on es troba actualment. Aquesta funció es cridarà quan una figura 
	 * no pugui baixar més posicions, tant si ha arribat al final del tauler com si 
	 * ha sota seu es troba una figura ja col·locada. 
	 */
	@Test
	public void testEncaixarFigura() {
		Tauler tauler = new Tauler();
		Tauler taulerSolucio = new Tauler();
		
		// Tauler buit		
		Figura figura = new Figura(Figura.O, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		tauler.encaixarFigura(figura);		
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.I, new Posicio(0, 17));
		omplirPosicio(taulerSolucio, figura.getColor(), 17, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 18, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.Z, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 2);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.S, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 2);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.L, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 2);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 2);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.T, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 2);
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 1);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
		
		taulerSolucio = new Tauler();
		tauler = new Tauler();
		figura = new Figura(Figura.P, new Posicio(0, 19));
		omplirPosicio(taulerSolucio, figura.getColor(), 19, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 0);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 1);
		omplirPosicio(taulerSolucio, figura.getColor(), 20, 2);
		tauler.encaixarFigura(figura);
		assertTrue(taulersIguals(tauler.getTauler(), taulerSolucio.getTauler()));
	
	}
	
	

}
