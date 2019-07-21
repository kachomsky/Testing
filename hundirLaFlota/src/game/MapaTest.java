/**
 * 
 */
package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe Mapa.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class MapaTest {
	
	Mapa mapa;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa();
		mapa.inicializarTablero();
	}
	
		
	// Test: inicializarTablero()
		// Tablero correcto
	@Test
	public void inicializarTableroTest(){
		Casilla tablero[][] = new Casilla[10][10];		
			for (int i = 0; i < 10; i++) { //y
				for (int j = 0; j < 10; j++) { //x
					tablero[i][j] = new Casilla(i,j,0);
				}
			}
		
			// Tablero que ejecuta inicializarTablero()
		Casilla[][] res_0 = mapa.getTablero();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertEquals(res_0[i][j].getEstado(), tablero[i][j].getEstado());
			}
		}
	}
	
	
	// Test: crearCabeceraTablero
	@Test
	public void crearCabeceraTableroTest(){ 
			// Cabecera correcta
		String cabecera = "    0 1 2 3 4 5 6 7 8 9";
			// Cabecera a testear
		String res_1 = mapa.crearCabeceraTablero();
		assertEquals(res_1, cabecera);
	}
	
	// Test: casillaExistente
	public void casillaExistente(){
		boolean res_2 = mapa.casillaExistente(5, 5);
		assertTrue(res_2);	
		
		boolean res_3 = mapa.casillaExistente(0, 0);
		assertTrue(res_3);
				
		boolean res_4 = mapa.casillaExistente(-1, 0);
		assertFalse(res_4);
				
		boolean res_5 = mapa.casillaExistente(0, -1);
		assertFalse(res_5);
				
		boolean res_6 = mapa.casillaExistente(9, 9);
		assertTrue(res_6);
				
		boolean res_7 = mapa.casillaExistente(10, 5);
		assertFalse(res_7);
				
		boolean res_8 = mapa.casillaExistente(5, 10);
		assertFalse(res_8);
				
		boolean res_9 = mapa.casillaExistente(10, 10);
		assertFalse(res_9);						
	}
	
	// Test: getEstado
	@Test
	public void getEstadoTest(){
		int res_10 = mapa.getEstadoCasilla(0, 0);
		assertEquals(res_10, 0);
	}
	
	
	// Test: cambiarEstado
	@Test
	public void cambiarEstadoTest(){
		mapa.cambiarEstado(1, 1, 1);	// Estado 1: casilla con barco
		assertEquals(mapa.getEstadoCasilla(1, 1), 1);
		
		mapa.cambiarEstado(2, 2, 2);	// Estado 2: casilla con agua disparada
		assertEquals(mapa.getEstadoCasilla(2, 2), 2);
		
		mapa.cambiarEstado(3,  3,  3);		// Estdo 3: casilla con barco disparada
		assertEquals(mapa.getEstadoCasilla(3, 3), 3);
		
		mapa.cambiarEstado(0, 0, 0);		// Estado 0: casilla con agua
		assertEquals(mapa.getEstadoCasilla(0, 0), 0);
		
		mapa.cambiarEstado(-1, 4, 4);
		assertEquals(mapa.getEstadoCasilla(4, 4), 0);
		
		mapa.cambiarEstado(5, 5, 5);
		assertEquals(mapa.getEstadoCasilla(5, 5), 0);
	}

	// Test: casillaLibre
	@Test
	public void casillaLibreTest(){
		Barco b = new Barco(2);
		int[] barcos = {2,2,2,2};
		mapa.setMaxBarcos(4);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();
		
		//casilla libre, casilla ocupada, casilla fuera tablero
		
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(9, 0, 'v', b, 1);
		mapa.colocarBarcos(0, 8, 'v', b, 2);
		mapa.colocarBarcos(9, 8, 'v', b, 3);
		
		boolean resCasillaLibre0 = mapa.casillaLibre(0, 0);
		assertFalse(resCasillaLibre0);
		
		boolean resCasillaLibre1 = mapa.casillaLibre(0, 1);
		assertFalse(resCasillaLibre1);
		
		boolean resCasillaLibre2 = mapa.casillaLibre(9, 0);
		assertFalse(resCasillaLibre2);
		
		boolean resCasillaLibre3 = mapa.casillaLibre(0, 8);
		assertFalse(resCasillaLibre3);
		
		boolean resCasillaLibre4 = mapa.casillaLibre(9, 8);
		assertFalse(resCasillaLibre4);
		
		boolean resCasillaLibre5 = mapa.casillaLibre(2, 2);
		assertTrue(resCasillaLibre5);
		
		boolean resCasillaLibre6 = mapa.casillaLibre(-1, 2);
		assertFalse(resCasillaLibre6);
		
		boolean resCasillaLibre7 = mapa.casillaLibre(10, 2);
		assertFalse(resCasillaLibre7);
		
		boolean resCasillaLibre8 = mapa.casillaLibre(0, -1);
		assertFalse(resCasillaLibre8);
		
		boolean resCasillaLibre9 = mapa.casillaLibre(0, 10);
		assertFalse(resCasillaLibre9);
	}
	
	// Test: comprobarEstadoCasilla
	@Test
	public void comprobarEstadoCasillaTest() {		
		//true nomes si es agua
		Barco b = new Barco(2);
		int[] barcos = {2,2,2,2};
		mapa.setMaxBarcos(4);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();
		
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(9, 0, 'v', b, 1);
		mapa.colocarBarcos(0, 8, 'v', b, 2);
		mapa.colocarBarcos(9, 8, 'v', b, 3);
		
		boolean res_16 = mapa.comprobarEstadoCasilla(0, 0);
		assertFalse(res_16);
		
		boolean res_17 = mapa.comprobarEstadoCasilla(9, 0);
		assertFalse(res_17);
		
		boolean res_18 = mapa.comprobarEstadoCasilla(0, 8);
		assertFalse(res_18);
		
		boolean res_19 = mapa.comprobarEstadoCasilla(1, 1);
		assertTrue(res_19);
		
		boolean res_20 = mapa.comprobarEstadoCasilla(10, 10);
		assertFalse(res_20);
	}
	
	// Test: casillaYaDisparada
	@Test
	public void casillaYaDisparadaTest(){
		// cambiando estados casilals
		mapa.cambiarEstado(0, 0, 0);
		mapa.cambiarEstado(1, 1, 1);
		mapa.cambiarEstado(2, 2, 2);
		mapa.cambiarEstado(3, 3, 3);
		
		boolean res_21 = mapa.casillaYaDisparada(6, 6);
		assertFalse(res_21);
		
		boolean res_22 = mapa.casillaYaDisparada(1, 1);
		assertFalse(res_22);
		
		boolean res_23 = mapa.casillaYaDisparada(2, 2);
		assertTrue(res_23);
		
		boolean res_24 = mapa.casillaYaDisparada(3, 3);
		assertTrue(res_24);
		
		boolean res_25 = mapa.casillaYaDisparada(10, 10);
		assertFalse(res_25);
	}
	
	// Test: casillasLibres
	@Test
	public void casillasLibresTest(){
		//reiniciando mapa y ocupando alguna casilla
		mapa.cambiarEstado(1, 4, 4);
		mapa.cambiarEstado(1, 4, 5);
		mapa.cambiarEstado(1, 5, 4);
		mapa.cambiarEstado(1, 5, 5);		
			// barcos para probar
		Barco vertical = new Barco(3);
		vertical.setOrientacion(true);
		Barco horizontal = new Barco(4);
		horizontal.setOrientacion(false);
		
			// barco vertical		
		boolean res_26 = mapa.casillasLibres(vertical, 0, 0);
		assertTrue(res_26);
		
		boolean res_27 = mapa.casillasLibres(vertical, 0, 7);
		assertTrue(res_27);
		
		boolean res_28 = mapa.casillasLibres(vertical, 0, 8);
		assertFalse(res_28);
		
		boolean res_29 = mapa.casillasLibres(vertical, 9, 0);
		assertTrue(res_29);
		
		boolean res_30 = mapa.casillasLibres(vertical, 9, 7);
		assertTrue(res_30);
		
		boolean res_31 = mapa.casillasLibres(vertical, 9, 8);
		assertFalse(res_31);
		
		boolean res_32 = mapa.casillasLibres(vertical, 4, 1);
		assertTrue(res_32);
		
		boolean res_33 = mapa.casillasLibres(vertical, 4, 2);
		assertFalse(res_33);
		
		boolean res_34 = mapa.casillasLibres(vertical, 4, 6);
		assertTrue(res_34);
		
		boolean res_35 = mapa.casillasLibres(vertical, 4, 5);
		assertFalse(res_35);
		
		boolean res_46 = mapa.casillasLibres(vertical, 10, 10);
		assertFalse(res_46);
		
			// barco horizontal
		boolean res_36 = mapa.casillasLibres(horizontal, 0, 0);
		assertTrue(res_36);
		
		boolean res_37 = mapa.casillasLibres(horizontal, 0, 9);
		assertTrue(res_37);
		
		boolean res_38 = mapa.casillasLibres(horizontal, 6, 0);
		assertTrue(res_38);
		
		boolean res_39 = mapa.casillasLibres(horizontal, 7, 0);
		assertFalse(res_39);
		
		boolean res_40 = mapa.casillasLibres(horizontal, 6, 9);
		assertTrue(res_40);
		
		boolean res_41 = mapa.casillasLibres(horizontal, 7, 9);
		assertFalse(res_41);
		
		boolean res_42 = mapa.casillasLibres(horizontal, 0, 4);
		assertTrue(res_42);
		
		boolean res_43 = mapa.casillasLibres(horizontal, 1, 4);
		assertFalse(res_43);
		
		boolean res_44 = mapa.casillasLibres(horizontal, 6, 4);
		assertTrue(res_44);
		
		boolean res_45 = mapa.casillasLibres(horizontal, 5, 4);
		assertFalse(res_45);
		
		boolean res_47 = mapa.casillasLibres(horizontal, 10, 10);
		assertFalse(res_47);
	}
	
	@Test
	public void ocultarBarcosTest(){
		Barco b = new Barco(2);
		Barco b2 = new Barco(2);
		Barco b3 = new Barco(2);
		Barco b4 = new Barco(2);
		int[] barcos = {2,2,2,2};
		mapa.setMaxBarcos(4);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();
		
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(9, 0, 'v', b2, 1);
		mapa.colocarBarcos(9, 8, 'v', b3,2);
		mapa.colocarBarcos(0, 8, 'v', b4, 3);
		
		
		mapa.ocultarBarcos();
		mapa.printTablero();
		
		String resOcultar1 = mapa.getTablero()[0][0].getImagen();
		assertEquals(resOcultar1,"-");
		String resOcultar2 = mapa.getTablero()[0][9].getImagen();
		assertEquals(resOcultar2,"-");
		String resOcultar3 = mapa.getTablero()[8][0].getImagen();
		assertEquals(resOcultar3,"-");
		String resOcultar4 = mapa.getTablero()[8][9].getImagen();
		assertEquals(resOcultar4,"-");
	}

	@Test
	public void colocarBarcosTest(){
		//particiones equivalentes: valores fuera de la matriz, valores dentro de la matriz, valores negativos posicion array barcos
		//valores frontera -> [0,9],interiores[1,8],fuera[-1,11] para X e Y
		Barco b = new Barco(2);
		int[] barcos = {2,2,2,2};
		mapa.setMaxBarcos(4);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();

		//frontera		
			//vertical
		boolean resMapa0 = mapa.colocarBarcos(0, 0, 'v', b, 0);
		assertTrue(resMapa0);
		boolean resMapa1 = mapa.colocarBarcos(0, 8, 'v', b, 1);
		assertTrue(resMapa1);
		boolean resMapa2 = mapa.colocarBarcos(9, 0, 'v', b, 2);
		assertTrue(resMapa2);
		boolean resMapa3 = mapa.colocarBarcos(9, 8, 'v', b, 3);
		assertTrue(resMapa3);
			//horizontal
		mapa.inicializarTablero();
		boolean resMapa4 = mapa.colocarBarcos(0, 0, 'h', b, 0);
		assertTrue(resMapa4);
		boolean resMapa5 = mapa.colocarBarcos(8, 0, 'h', b, 1);
		assertTrue(resMapa5);
		boolean resMapa6 = mapa.colocarBarcos(0, 9, 'h', b, 2);
		assertTrue(resMapa6);
		boolean resMapa7 = mapa.colocarBarcos(8, 9, 'h', b, 3);
		assertTrue(resMapa7);
		
		//valor interior frontera bordes
		mapa.inicializarTablero(); 
		boolean resMapa8 = mapa.colocarBarcos(0, 3, 'v', b, 0);
		assertTrue(resMapa8);
		boolean resMapa9 = mapa.colocarBarcos(9, 3, 'v', b, 1);
		assertTrue(resMapa9);
		boolean resMapa10 = mapa.colocarBarcos(3, 0, 'h', b, 2);
		assertTrue(resMapa10);
		boolean resMapa11 = mapa.colocarBarcos(3, 9, 'h', b, 3);
		assertTrue(resMapa11);
		
		//valores interiores
		mapa.inicializarTablero(); 
		boolean resMapa12 = mapa.colocarBarcos(1, 1, 'v', b, 0);
		assertTrue(resMapa12);
		boolean resMapa13 = mapa.colocarBarcos(8, 8, 'v', b, 1);
		assertTrue(resMapa13);
		boolean resMapa14 = mapa.colocarBarcos(8, 1, 'v', b, 2);
		assertTrue(resMapa14);
		boolean resMapa15 = mapa.colocarBarcos(1, 8, 'v', b, 3);
		assertTrue(resMapa15);
		
		//valores fuera de la matriz
		mapa.inicializarTablero(); 
		boolean resMapa16 = mapa.colocarBarcos(-1, 1, 'v', b, 0);
		assertFalse(resMapa16);
		boolean resMapa17 = mapa.colocarBarcos(1, 11, 'v', b, 1);
		assertFalse(resMapa17);
		boolean resMapa18 = mapa.colocarBarcos(11, 1, 'v', b, 2);
		assertFalse(resMapa18);
		boolean resMapa19 = mapa.colocarBarcos(1, -1, 'v', b, 3);
		assertFalse(resMapa19);
		
		//valores que salen de la matriz con la orientacion elegida
		int[] barcosTamaños = {2, 2};
		mapa.setMaxBarcos(2);
		mapa.setTamañoBarcos(barcosTamaños);
		mapa.restartBarcos();
		mapa.inicializarTablero(); 
		boolean resMapa20 = mapa.colocarBarcos(0, 9, 'v', b, 0);
		assertFalse(resMapa20);
		boolean resMapa21 = mapa.colocarBarcos(9, 0, 'h', b, 1);
		assertFalse(resMapa21);
		
		//cruce con otro barco / encima de otro barco
		int[] barcosTamaños2 = {3, 3};
		Barco existingBarco = new Barco(3);
		Barco newBarco = new Barco(3);
		mapa.setMaxBarcos(2);
		mapa.setTamañoBarcos(barcosTamaños2);
		mapa.restartBarcos();
		mapa.inicializarTablero();
		mapa.colocarBarcos(4, 5, 'v', existingBarco, 0);
		
		boolean resMapa22 = mapa.colocarBarcos(3, 6, 'h', newBarco, 1);
		assertFalse(resMapa22);
		
		boolean resMapa23 = mapa.colocarBarcos(4, 5, 'h', newBarco, 1);
		assertFalse(resMapa23);
		
		boolean resMapa24 = mapa.colocarBarcos(2, 5, 'h', newBarco, 1);
		assertFalse(resMapa24);		
	}
	
	@Test
	public void atacarCasillaTest(){
		Barco b = new Barco(2);
		int[] barcos = {2};
		mapa.setMaxBarcos(1);
		mapa.setTamañoBarcos(barcos);
		mapa.colocarBarcos(2,2,'v',b,0);
		//particio equivalent -> valor fora de matriu, valor dins matriu, diferents estats casellas
		//valors frontera -> [0,9],interiors[1,8],fora[-1,11] per a X i Y
		
		//valores frontera
		boolean res48 = mapa.atacarCasilla(0, 0);
		assertTrue(res48);
		assertEquals(mapa.getTablero()[0][0].getEstado(),2);
		
		boolean res49 = mapa.atacarCasilla(9, 9);
		assertTrue(res49);
		assertEquals(mapa.getTablero()[9][9].getEstado(),2);
		
		boolean res50 = mapa.atacarCasilla(0, 9);
		assertTrue(res50);
		assertEquals(mapa.getTablero()[9][0].getEstado(),2);
		
		boolean res51 = mapa.atacarCasilla(9, 0);
		assertTrue(res51);
		assertEquals(mapa.getTablero()[0][9].getEstado(),2);
		
		//valores interiores a frontera
		boolean res52 = mapa.atacarCasilla(1, 0);
		assertTrue(res52);
		assertEquals(mapa.getTablero()[0][1].getEstado(),2);
		
		boolean res53 = mapa.atacarCasilla(8, 0);
		assertTrue(res53);
		assertEquals(mapa.getTablero()[0][8].getEstado(),2);
		
		boolean res54 = mapa.atacarCasilla(0, 1);
		assertTrue(res54);
		assertEquals(mapa.getTablero()[1][0].getEstado(),2);
		
		boolean res55 = mapa.atacarCasilla(0, 8);
		assertTrue(res55);
		assertEquals(mapa.getTablero()[8][0].getEstado(),2);
		
		boolean res56 = mapa.atacarCasilla(9, 1);
		assertTrue(res56);
		assertEquals(mapa.getTablero()[1][9].getEstado(),2);
		
		boolean res57 = mapa.atacarCasilla(1, 9);
		assertTrue(res57);
		assertEquals(mapa.getTablero()[9][1].getEstado(),2);
		
		boolean res58 = mapa.atacarCasilla(9, 8);
		assertTrue(res58);
		assertEquals(mapa.getTablero()[8][9].getEstado(),2);
		
		boolean res59 = mapa.atacarCasilla(8, 9);
		assertTrue(res59);
		assertEquals(mapa.getTablero()[9][8].getEstado(),2);
		
		//valores exteriores a frontera
		boolean res60 = mapa.atacarCasilla(-1, 0);
		assertFalse(res60);
		
		boolean res61 = mapa.atacarCasilla(-11, 0);
		assertFalse(res61);
		
		boolean res62 = mapa.atacarCasilla(0, -1);
		assertFalse(res62);
		
		boolean res63 = mapa.atacarCasilla(0, -11);
		assertFalse(res63);
		
		/*diferentes estados casilla
		 * 0 -> agua -> 2
		 * 1 -> contiene parte de un barco -> 3
		 * 2 -> agua, pero ha sido tocado -> 2
		 * 3 -> barco, pero ha sido tocado -> 3
		 */
		
		mapa.atacarCasilla(2, 2);
		assertEquals(mapa.getTablero()[2][2].getEstado(),3);
		
		mapa.atacarCasilla(6, 6);
		assertEquals(mapa.getTablero()[6][6].getEstado(),2);
		
		mapa.atacarCasilla(6, 6);
		assertEquals(mapa.getTablero()[6][6].getEstado(),2);
		
		mapa.atacarCasilla(2, 2);
		assertEquals(mapa.getTablero()[2][2].getEstado(),3);
	}
	
	@Test
	public void comprobarBarcoTocadoTest(){
		//particion equivalente -> casillas con barco, casillas sin barco, casilla fuera del mapa
		//casillas con barco y casillas sin barco en valores frontera, en limite interior, en limite exterior
		
		//valores frontera vertical
		Barco b = new Barco(2);
		int[] barcos = {2,2,2,2};
		mapa.setMaxBarcos(4);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();

		mapa.colocarBarcos(0,0,'v',b,0);
		mapa.atacarCasilla(0, 0);
		mapa.atacarCasilla(0, 1);
		assertTrue(mapa.comprobarBarcoTocado(0, 0));
		
		mapa.colocarBarcos(0,8,'v',b,1);
		mapa.atacarCasilla(0, 8);
		mapa.atacarCasilla(0, 9);
		assertTrue(mapa.comprobarBarcoTocado(0, 8));
		
		mapa.colocarBarcos(9,8,'v',b,2);
		mapa.atacarCasilla(9, 8);
		mapa.atacarCasilla(9, 9);
		assertTrue(mapa.comprobarBarcoTocado(9, 8));
		
		mapa.colocarBarcos(9,0,'v',b,3);
		mapa.atacarCasilla(9, 0);
		mapa.atacarCasilla(9, 1);
		assertTrue(mapa.comprobarBarcoTocado(9, 0));
		//valores frontera horizontal
		mapa.inicializarTablero();
		
		mapa.colocarBarcos(0,0,'h',b,0);
		mapa.atacarCasilla(0, 0);
		mapa.atacarCasilla(1, 0);
		assertTrue(mapa.comprobarBarcoTocado(0, 0));
		
		mapa.colocarBarcos(8,0,'h',b,1);
		mapa.atacarCasilla(8, 0);
		mapa.atacarCasilla(9, 0);
		assertTrue(mapa.comprobarBarcoTocado(8, 0));
		
		mapa.colocarBarcos(8,9,'h',b,2);
		mapa.atacarCasilla(8, 9);
		mapa.atacarCasilla(9, 9);
		assertTrue(mapa.comprobarBarcoTocado(8, 9));
		
		mapa.colocarBarcos(0,9,'h',b,3);
		mapa.atacarCasilla(0, 9);
		mapa.atacarCasilla(1, 9);
		assertTrue(mapa.comprobarBarcoTocado(0, 9));
		
		//valores interior frontera
		mapa.inicializarTablero();
		
		mapa.colocarBarcos(1,1,'h',b,0);
		mapa.atacarCasilla(1, 1);
		mapa.atacarCasilla(2, 1);
		assertTrue(mapa.comprobarBarcoTocado(1, 1));
		
		mapa.colocarBarcos(7,1,'h',b,1);
		mapa.atacarCasilla(7, 1);
		mapa.atacarCasilla(8, 1);
		assertTrue(mapa.comprobarBarcoTocado(7, 1));
		
		mapa.colocarBarcos(7,8,'h',b,2);
		mapa.atacarCasilla(7, 8);
		mapa.atacarCasilla(8, 8);
		assertTrue(mapa.comprobarBarcoTocado(7, 8));
		
		mapa.colocarBarcos(1,8,'h',b,3);
		mapa.atacarCasilla(1, 8);
		mapa.atacarCasilla(2, 8);
		assertTrue(mapa.comprobarBarcoTocado(1, 8));
		
		//valores fuera de frontera
		mapa.inicializarTablero();
		
		mapa.colocarBarcos(-1,0,'h',b,0);
		mapa.atacarCasilla(-1, 0);
		mapa.atacarCasilla(0, 0);
		assertFalse(mapa.comprobarBarcoTocado(-1, 0));
		
		mapa.colocarBarcos(0,-1,'v',b,0);
		mapa.atacarCasilla(0, -1);
		mapa.atacarCasilla(0, 0);
		assertFalse(mapa.comprobarBarcoTocado(0, -1));
		
		mapa.colocarBarcos(0,10,'v',b,0);
		mapa.atacarCasilla(0, 10);
		mapa.atacarCasilla(0, 11);
		assertFalse(mapa.comprobarBarcoTocado(0, 10));
		
		mapa.colocarBarcos(10,0,'v',b,0);
		mapa.atacarCasilla(10, 0);
		mapa.atacarCasilla(11, 0);
		assertFalse(mapa.comprobarBarcoTocado(10, 0));
		
		//atacar casilla sin barco
		mapa.inicializarTablero();
		mapa.atacarCasilla(0, 0);
		assertFalse(mapa.comprobarBarcoTocado(0, 0));
		
	}
	
	@Test
	public void comprobarVictoriaTest(){
		/*Dos barcos. Opciones
		 * 1) uno tocado y el otro sin tocar
		 * 2) uno hundido y el otro sin tocar
		 * 3) los dos hundidos
		 * 4) dos barcos tocados pero no hundidos
		 */
		
		//1
		Barco b = new Barco(2);
		int[] barcos = {2,2};
		mapa.setMaxBarcos(2);
		mapa.setTamañoBarcos(barcos);
		mapa.restartBarcos();
		
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(8, 9, 'h', b, 1);
		mapa.atacarCasilla(0, 0);//solo un barco tocado
		boolean res64 = mapa.comprobarVictoria();
		assertFalse(res64);
		
		//2
		mapa.inicializarTablero();
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(8, 9, 'h', b, 1);
		mapa.atacarCasilla(0, 0);
		mapa.atacarCasilla(0, 1);//Un barco hundido
		boolean res65 = mapa.comprobarVictoria();
		assertFalse(res65);
		
		//3
		mapa.inicializarTablero();
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(8, 9, 'h', b, 1);
		mapa.atacarCasilla(0, 0);
		mapa.atacarCasilla(0, 1);
		mapa.atacarCasilla(8, 9);
		mapa.atacarCasilla(9, 9);//Los dos barcos hundidos
		boolean res66 = mapa.comprobarVictoria();
		assertTrue(res66);
		
		//4
		mapa.inicializarTablero();
		mapa.colocarBarcos(0, 0, 'v', b, 0);
		mapa.colocarBarcos(8, 9, 'h', b, 1);
		mapa.atacarCasilla(0, 0);
		mapa.atacarCasilla(8, 9); //Dos barcos tocados pero no hundidos
		
		boolean res67 = mapa.comprobarVictoria();
		assertFalse(res67);
	}
}
