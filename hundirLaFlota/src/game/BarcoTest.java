/**
 * 
 */
package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test para la clase Barco.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class BarcoTest {

	
	Barco b;
	Mapa mapa;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		b = new Barco(3);
		mapa = new Mapa();
		mapa.inicializarTablero();	
	}	
		
	
	// Test: charOrientacionToBool
	@Test
	public void charOrientacionToBoolTest() {
		char letra = 'h';
		boolean res_0 = b.charOrientacionToBool(letra);
		assertFalse(b.getOrientacion());
		assertTrue(res_0);
		
		
		letra = 'v';
		boolean res_1 = b.charOrientacionToBool(letra);
		assertTrue(b.getOrientacion());
		assertTrue(res_1);

		letra = 'c';
		boolean res_2 = b.charOrientacionToBool(letra);
		assertFalse(res_2);		
	}
	
	// Test: addCasillas
	@Test
	public void addCasillasTest(){
		Casilla[] casillas = new Casilla[3];
		for (int i = 0; i < 3; i++) {
			casillas[i] = new Casilla(0, 0 + i, 1);
		}
		b.setOrientacion(true);
			// test
		b.addCasillas(0, 0);
		for (int i = 0; i < 3; i ++) {
			assertEquals(b.getCasillasBarco()[i].getCoordenadaX(), casillas[i].getCoordenadaX());
			assertEquals(b.getCasillasBarco()[i].getCoordenadaY(), casillas[i].getCoordenadaY());
			assertEquals(b.getCasillasBarco()[i].getEstado(), casillas[i].getEstado());
		}
	}
	
	
	// Test: orientacionPermitida
	@Test
	public void orientacionPermitidaTest() {
		mapa.cambiarEstado(1, 4, 4);
		mapa.cambiarEstado(1, 4, 5);
		mapa.cambiarEstado(1, 5, 4);
		mapa.cambiarEstado(1, 5, 5);
			// horizontal
		b.setOrientacion(false);
		boolean res_3 = b.orientacionPermitida(0, 0, mapa);
		assertTrue(res_3);
		
		boolean res_4 = b.orientacionPermitida(-1, 0, mapa);
		assertFalse(res_4);
		
		boolean res_5 = b.orientacionPermitida(0, 9, mapa);
		assertTrue(res_5);
		
		boolean res_6 = b.orientacionPermitida(-1, 9, mapa);
		assertFalse(res_6);
		
		boolean res_7 = b.orientacionPermitida(7, 0, mapa);
		assertTrue(res_7);
		
		boolean res_8 = b.orientacionPermitida(8, 0, mapa);
		assertFalse(res_8);
		
		boolean res_9 = b.orientacionPermitida(7, 9, mapa);
		assertTrue(res_9);
		
		boolean res_10 = b.orientacionPermitida(8, 9, mapa);
		assertFalse(res_10);
		
		boolean res_11 = b.orientacionPermitida(1, 4, mapa);
		assertTrue(res_11);
		
		boolean res_12 = b.orientacionPermitida(2, 4, mapa);
		assertFalse(res_12);
		
		boolean res_13 = b.orientacionPermitida(6, 4, mapa);
		assertTrue(res_13);
		
		boolean res_14 = b.orientacionPermitida(5, 4, mapa);
		assertFalse(res_14);
		
			// vertical
		b.setOrientacion(true);
		boolean res_15 = b.orientacionPermitida(0, 0, mapa);
		assertTrue(res_15);
		
		boolean res_16 = b.orientacionPermitida(0, -1, mapa);
		assertFalse(res_16);
		
		boolean res_17 = b.orientacionPermitida(0, 7, mapa);
		assertTrue(res_17);
		
		boolean res_18 = b.orientacionPermitida(0, 8, mapa);
		assertFalse(res_18);
		
		boolean res_19 = b.orientacionPermitida(9, 0, mapa);
		assertTrue(res_19);
		
		boolean res_20 = b.orientacionPermitida(9, -1, mapa);
		assertFalse(res_20);
		
		boolean res_21 = b.orientacionPermitida(9, 7, mapa);
		assertTrue(res_21);
		
		boolean res_22 = b.orientacionPermitida(9, 8, mapa);
		assertFalse(res_22);
		
		boolean res_23 = b.orientacionPermitida(4, 1, mapa);
		assertTrue(res_23);
		
		boolean res_24 = b.orientacionPermitida(4, 2, mapa);
		assertFalse(res_24);
		
		boolean res_25 = b.orientacionPermitida(4, 6, mapa);
		assertTrue(res_25);
		
		boolean res_26 = b.orientacionPermitida(4, 5, mapa);
		assertFalse(res_26);	
	
	}
	
	// Test pedirOrientacionTest
	// Tiene que comprobar que se escribe una orientación existente. Si está en la posición correcta o no se encarga otra función.
	@Test
	public void pedirOrientacionTest(){
		boolean res_pedir1 = b.pedirOrientacion(0, 0, mapa, 'v');
		assertTrue(res_pedir1);
		
		boolean res_pedir2 = b.pedirOrientacion(0, 0, mapa, '2');
		assertFalse(res_pedir2);
		
		boolean res_pedir3 = b.pedirOrientacion(0, 0, mapa, 'm');
		assertFalse(res_pedir3);
		
		boolean res_pedir4 = b.pedirOrientacion(10, 0, mapa, 'v');
		assertTrue(res_pedir4);
	}

	// Test: colocarBarco
	@Test
	public void colocarBarcoTest() {
		Barco b = new Barco(2);
		mapa.setMaxBarcos(4);
		int[] tamañoBarcos = {2, 2, 2, 2};
		mapa.setTamañoBarcos(tamañoBarcos);
		mapa.restartBarcos();
		
		//frontera		
			//vertical
		boolean resMapa0 = b.colocarBarco(mapa, 0, 0, 'v');
		assertTrue(resMapa0);
		boolean resMapa1 = b.colocarBarco(mapa, 0, 8, 'v');
		assertTrue(resMapa1);
		boolean resMapa2 = b.colocarBarco(mapa, 9, 0, 'v');
		assertTrue(resMapa2);
		boolean resMapa3 = b.colocarBarco(mapa, 9, 8, 'v');
		assertTrue(resMapa3);
			//horizontal
		mapa.inicializarTablero();
		boolean resMapa4 = b.colocarBarco(mapa, 0, 0, 'h');
		assertTrue(resMapa4);
		boolean resMapa5 = b.colocarBarco(mapa, 8, 0, 'h');
		assertTrue(resMapa5);
		boolean resMapa6 = b.colocarBarco(mapa, 0, 9, 'h');
		assertTrue(resMapa6);
		boolean resMapa7 = b.colocarBarco(mapa, 8, 9, 'h');
		assertTrue(resMapa7);
		
		//valor interior frontera bordes
		mapa.inicializarTablero(); 
		boolean resMapa8 = b.colocarBarco(mapa, 0, 3, 'v');
		assertTrue(resMapa8);
		boolean resMapa9 = b.colocarBarco(mapa, 9, 3, 'v');
		assertTrue(resMapa9);
		boolean resMapa10 = b.colocarBarco(mapa, 3, 0, 'h');
		assertTrue(resMapa10);
		boolean resMapa11 = b.colocarBarco(mapa, 3, 9, 'h');
		assertTrue(resMapa11);
		
		//valores interiores
		mapa.inicializarTablero(); 
		boolean resMapa12 = b.colocarBarco(mapa, 1, 1, 'v');
		assertTrue(resMapa12);
		boolean resMapa13 = b.colocarBarco(mapa, 8, 8, 'v');
		assertTrue(resMapa13);
		boolean resMapa14 = b.colocarBarco(mapa, 8, 1, 'v');
		assertTrue(resMapa14);
		boolean resMapa15 = b.colocarBarco(mapa, 1, 8, 'v');
		assertTrue(resMapa15);
		
		//valores fuera de la matriz
		mapa.inicializarTablero(); 
		boolean resMapa16 = b.colocarBarco(mapa, -1, 1, 'v');
		assertFalse(resMapa16);
		boolean resMapa17 = b.colocarBarco(mapa, 1, 11, 'v');
		assertFalse(resMapa17);
		boolean resMapa18 = b.colocarBarco(mapa, 11, 1, 'v');
		assertFalse(resMapa18);
		boolean resMapa19 = b.colocarBarco(mapa, 1, -1, 'v');
		assertFalse(resMapa19);
		
		//valores que salen de la matriz con la orientacion elegida
		int[] barcosTamaños = {2, 2};
		mapa.setMaxBarcos(2);
		mapa.setTamañoBarcos(barcosTamaños);
		mapa.restartBarcos();
		mapa.inicializarTablero(); 
		boolean resMapa20 = b.colocarBarco(mapa, 0, 9, 'v');
		assertFalse(resMapa20);
		boolean resMapa21 = b.colocarBarco(mapa, 9, 0, 'h');
		assertFalse(resMapa21);
		
		//cruce con otro barco / encima de otro barco
		int[] barcosTamaños2 = {3, 3};
		Barco existingBarco = new Barco(3);
		Barco newBarco = new Barco(3);
		mapa.setMaxBarcos(2);
		mapa.setTamañoBarcos(barcosTamaños2);
		mapa.restartBarcos();
		mapa.inicializarTablero();
		existingBarco.colocarBarco(mapa, 4, 5, 'v');
		
		boolean resMapa22 = newBarco.colocarBarco(mapa, 3, 6, 'h');
		assertFalse(resMapa22);
		
		boolean resMapa23 = newBarco.colocarBarco(mapa, 4, 5, 'h');
		assertFalse(resMapa23);
		
		boolean resMapa24 = newBarco.colocarBarco(mapa, 2, 5, 'h');
		assertFalse(resMapa24);
	}
	
	
	// Test: comprobarHundido
	@Test
	public void comprobarHundidoTest() {
		Barco b = new Barco(2);
		b.colocarBarco(mapa, 2, 2,'v');
		boolean res_27 = b.comprobarHundido();
		assertFalse(res_27);
		
		b.getCasillasBarco()[0].setEstado(3);
		b.getCasillasBarco()[1].setEstado(3);
		boolean res_28 = b.comprobarHundido();
		assertTrue(res_28);
	}
	


}
