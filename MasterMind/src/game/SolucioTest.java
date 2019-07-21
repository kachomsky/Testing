package game;

import static org.junit.Assert.*;
import org.junit.Test;

public class SolucioTest {
	
	public class MockNombres extends Nombres {
		private int nombre = 0;
		
		public MockNombres() {
		}
		
		public int generarNombreAleatori() {
			nombre++;
			return nombre;
		}
	}
	
	@Test
	public void testGenerarCombinacio() {
		// Probem per combinacions de 1 fins a 6 nombres.
		for (int i = 1; i < 7; i++) {
			MockNombres generador = new MockNombres();
			Solucio solucio = new Solucio(i);
			solucio.generarCombinacio(generador);
			
			for (int j = 0; j < solucio.getCombinacio().length; j++) {
				assertTrue("Element " + j + " de la combinacio " + i + " es menor que 1", 
						solucio.getCombinacio()[j] > 0);
				assertTrue("Element " + j + " de la combinacio " + i + " es major que 8", 
						solucio.getCombinacio()[j] < 9);
			}
		}
		
	}
	
	@Test
	public void testcomprovarCombinacioCorrecta() {
		/* 0 -> no coincideix cap nombre
		 * 1-> coincideix el nombre però no la posició
		 * 2-> coincideixen el nombre i la posició
		 */
		
		// Control flow: Comprovat el control flow amb eclemma  
		// Statement coverage: totes les sentencies s'executen com a mínim un cop
		// Decision coverage: totes les decisions prenen el valor true o false
		// Condition coverage: les conditions de la funció només tenen una decisió, per tant 
		//					   ja es compleixen amb el Decision Coverage.
		// Path coverage: la combinació de les següents probes passa per tots els paths independents.
		//				  Els paths seleccionats es mostren en el PDF de les Proves de Caixa Blanca.
		// Loop testing: es proven tots els loops de la funció (en aquest cas, els for's), des de
		//				 el valor 0 fins a n.
						  
		
		
		Solucio solucio = new Solucio(4);
		int[] combinacio = {1,2,3,3};
		solucio.setCombinacio(combinacio);
		
		
		//COINCIDEIXEN TOTS ELS NOMBRE I POSICIONS
		int[] correctes = {2,2,2,2};
		
		int[] comb_0 = solucio.comprovarCombinacio(combinacio);
		assertArrayEquals(comb_0, correctes);
		
		//COINCIDEIXEN TOTS ELS NOMBRES PERO NO POSICIONS
		int[] combinacio2 = {3,3,2,1};
		int[] correctes2 = {1,1,1,1};
		
		int[] comb_1 = solucio.comprovarCombinacio(combinacio2);
		assertArrayEquals(comb_1, correctes2);
		
		//NO COINCIDEIX CAP NOMBRE NI POSICIO
		int[] combinacio3 = {5,6,7,8};
		int[] correctes3 = {0,0,0,0};
		
		int[] comb_2 = solucio.comprovarCombinacio(combinacio3);
		assertArrayEquals(comb_2, correctes3);
		
		//COINCIDEIX NOMES UN NOMBRE PERO NO LA SEVA POSICIO
		int[] combinacio4 = {5,1,7,8};
		int[] correctes4 = {1,0,0,0};
		
		int[] comb_3 = solucio.comprovarCombinacio(combinacio4);
		assertArrayEquals(comb_3, correctes4);
		
		//COINCIDEIXEN 2 NOMBRES PERO NO POSICIO
		int[] combinacio5 = {5,1,2,8};
		int[] correctes5 = {1,1,0,0};
		
		int[] comb_4 = solucio.comprovarCombinacio(combinacio5);
		assertArrayEquals(comb_4, correctes5);
		
		//COINCIDEIX 1 NOMBRE I LA SEVA POSICIO
		int[] combinacio6 = {1,5,7,8};
		int[] correctes6 = {2,0,0,0};
		
		int[] comb_5 = solucio.comprovarCombinacio(combinacio6);
		assertArrayEquals(comb_5, correctes6);
		
		//COINCIDEIXEX 2 NOMBRES I LES SEVES POSICIONS
		int[] combinacio7 = {1,2,7,8};
		int[] correctes7 = {2,2,0,0};
		
		int[] comb_6 = solucio.comprovarCombinacio(combinacio7);
		assertArrayEquals(comb_6, correctes7);
		
		//COINCIDEIXEX 1 NOMBRE I 1 NOMBRE AMB LA SEVA POSICIO
		int[] combinacio8 = {1,5,2,8};
		int[] correctes8 = {2,1,0,0};
		
		int[] comb_7 = solucio.comprovarCombinacio(combinacio8);
		assertArrayEquals(comb_7, correctes8);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 1 VEGADA, PERO FORA DE POSICIÓ
		int[] combinacio9 = {3,5,5,6};
		int[] correctes9 = {1,0,0,0};
		
		int[] comb_9 = solucio.comprovarCombinacio(combinacio9);
		assertArrayEquals(comb_9, correctes9);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 1 VEGADA EN LA POSICIÓ CORRECTA
		int[] combinacio10 = {5,5,3,6};
		int[] correctes10 = {2,0,0,0};
		
		int[] comb_10 = solucio.comprovarCombinacio(combinacio10);
		assertArrayEquals(comb_10, correctes10);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 2 VEGADES, LES 2 FORA DE POSICIÓ
		int[] combinacio11 = {3,3,5,6};
		int[] correctes11 = {1,1,0,0};
		
		int[] comb_11 = solucio.comprovarCombinacio(combinacio11);
		assertArrayEquals(comb_11, correctes11);	
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 1 VEGADA EN LA POSICIÓ CORRECTA
		//			  I 1 ALTRA VEGADA FORA DE POSICIÓ
		int[] combinacio12 = {3,5,3,6};
		int[] correctes12 = {2,1,0,0};
		
		int[] comb_12 = solucio.comprovarCombinacio(combinacio12);
		assertArrayEquals(comb_12, correctes12);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 2 VEGADES EN LES POSICIONS CORRECTES
		int[] combinacio13 = {6,5,3,3};
		int[] correctes13 = {2,2,0,0};
		
		int[] comb_13 = solucio.comprovarCombinacio(combinacio13);
		assertArrayEquals(comb_13, correctes13);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 1 VEGADA EN LA POSICIÓ CORRECTA I
		//			  2 VEGADES FORA DE POSICIÓ		
		int[] combinacio14 = {3,3,3,5};
		int[] correctes14 = {2,1,0,0};
		
		int[] comb_14 = solucio.comprovarCombinacio(combinacio14);
		assertArrayEquals(comb_14, correctes14);
		
		//SOLUCIÓ: 1 NOMBRE REPETIT 2 VEGADES
		//COMBINACIÓ: EL NOMBRE REPETIT APAREIX 2 VEGADES FORA DE POSICIÓ I
		//			  UN ALTRE NOMBRE, QUE NO ES DE LA SOLUCIÓ, APAREIX REPETIT 2 VEGADES
		int[] combinacio15 = {3,3,2,2};
		int[] correctes15 = {1,1,1,0};
		int[] comb_15 = solucio.comprovarCombinacio(combinacio15);
		assertArrayEquals(comb_15, correctes15);
		
		//VALORS FRONTERA
		//COINCIDEIX POSICIO I NUMERO NOMES PRIMER I ULTIM
		int[] combinacio16 = {1,8,8,3};
		int[] correctes16 = {2,2,0,0};
		
		int[] comb_16 = solucio.comprovarCombinacio(combinacio16);
		assertArrayEquals(comb_16, correctes16);
		
		//COINCIDEIX NOMES NUMERO PRIMER I ULTIM ELEMENT
		int[] combinacio17 = {8,3,1,8};
		int[] correctes17 = {1,1,0,0};
		
		int[] comb_17 = solucio.comprovarCombinacio(combinacio17);
		assertArrayEquals(comb_17, correctes17);
		
		//VALORS INTERIOR FRONTERA
		//COINCIDEIX POSICIO I NUMERO NOMES PRIMER I ULTIM
		int[] combinacio18 = {8,2,3,8};
		int[] correctes18 = {2,2,0,0};
		
		int[] comb_18 = solucio.comprovarCombinacio(combinacio18);
		assertArrayEquals(comb_18, correctes18);
		
		//COINCIDEIX NOMES NUMERO VALORS INTERIOR FRONTERA
		int[] combinacio19 = {2,3,8,8};
		int[] correctes19 = {1,1,0,0};
		
		int[] comb_19 = solucio.comprovarCombinacio(combinacio19);
		assertArrayEquals(comb_19, correctes19);
		
		
		
		// LOOP TESTING
		
		//Bucles nNombres
			// nNombres = 0 (cap pasada del bucle)
		solucio = new Solucio(0);
		int[] combinacio20 = {1,2,3,4};
		int[] correctes20 = new int[0];
		
		int[] comb_20 = solucio.comprovarCombinacio(combinacio20);
		assertArrayEquals(comb_20, correctes20);
		
			// nNombres = 1 (una pasada del bucle)
		solucio = new Solucio(1);
		int[] combinacio_solucio1 = {1};
		solucio.setCombinacio(combinacio_solucio1);
		int[] combinacio21 = {1};
		int[] correctes21 = {2};
		
		int[] comb_21 = solucio.comprovarCombinacio(combinacio21);
		assertArrayEquals(comb_21, correctes21);
		
			// nNombres = 2
		solucio = new Solucio(2);
		int[] combinacio_solucio2 = {1,2};
		solucio.setCombinacio(combinacio_solucio2);
		int[] combinacio22 = {1,2};
		int[] correctes22 = {2,2};
		
		int[] comb_22 = solucio.comprovarCombinacio(combinacio22);
		assertArrayEquals(comb_22, correctes22);
		
			// nNombres = 3
		solucio = new Solucio(3);
		int[] combinacio_solucio3 = {1,2,3};
		solucio.setCombinacio(combinacio_solucio3);
		int[] combinacio23 = {1,2,3};
		int[] correctes23 = {2,2,2};
		
		int[] comb_23 = solucio.comprovarCombinacio(combinacio23);
		assertArrayEquals(comb_23, correctes23);
		
			// nNombres = 4
		solucio = new Solucio(4);
		int[] combinacio_solucio4 = {1,2,3,4};
		solucio.setCombinacio(combinacio_solucio4);
		int[] combinacio24 = {1,2,3,4};
		int[] correctes24 = {2,2,2,2};
		
		int[] comb_24 = solucio.comprovarCombinacio(combinacio24);
		assertArrayEquals(comb_24, correctes24);
		
			// nNombres = 5
		solucio = new Solucio(5);
		int[] combinacio_solucio5 = {1,2,3,4,5};
		solucio.setCombinacio(combinacio_solucio5);
		int[] combinacio25 = {1,2,3,4,5};
		int[] correctes25 = {2,2,2,2,2};
		
		int[] comb_25 = solucio.comprovarCombinacio(combinacio25);
		assertArrayEquals(comb_25, correctes25);
		
			// nNombres = 6
		solucio = new Solucio(6);
		int[] combinacio_solucio6 = {1,2,3,4,5,6};
		solucio.setCombinacio(combinacio_solucio6);
		int[] combinacio26 = {1,2,3,4,5,6};
		int[] correctes26 = {2,2,2,2,2,2};
		
		int[] comb_26 = solucio.comprovarCombinacio(combinacio26);
		assertArrayEquals(comb_26, correctes26);
		
		
		
		// Bucle contadorNombreIPos
		solucio = new Solucio(6);
		int[] combinacio_solucio7 = {1,2,3,4,5,6};
		solucio.setCombinacio(combinacio_solucio7);
		
			// contadorNombreIPos = 0
		int[] combinacio27 = {6,5,4,3,2,1};
		int[] correctes27 = {1,1,1,1,1,1};
		
		int[] comb_27 = solucio.comprovarCombinacio(combinacio27);
		assertArrayEquals(comb_27, correctes27);
		
		// contadorNombreIPos = 1
		int[] combinacio28 = {1,6,5,3,4,2};
		int[] correctes28 = {2,1,1,1,1,1};
		
		int[] comb_28 = solucio.comprovarCombinacio(combinacio28);
		assertArrayEquals(comb_28, correctes28);
		
		// contadorNombreIPos = 2
		int[] combinacio29 = {1,2,6,5,4,3};
		int[] correctes29 = {2,2,1,1,1,1};
		
		int[] comb_29 = solucio.comprovarCombinacio(combinacio29);
		assertArrayEquals(comb_29, correctes29);
		
		// contadorNombreIPos = 3
		int[] combinacio30 = {1,2,3,5,6,4};
		int[] correctes30 = {2,2,2,1,1,1};
		
		int[] comb_30 = solucio.comprovarCombinacio(combinacio30);
		assertArrayEquals(comb_30, correctes30);
		
		// contadorNombreIPos = 4
		int[] combinacio31 = {1,2,3,4,6,5};
		int[] correctes31 = {2,2,2,2,1,1};
		
		int[] comb_31 = solucio.comprovarCombinacio(combinacio31);
		assertArrayEquals(comb_31, correctes31);
		
		// contadorNombreIPos = 5
		int[] combinacio32 = {1,2,3,4,5,8};
		int[] correctes32 = {2,2,2,2,2,0};
		
		int[] comb_32 = solucio.comprovarCombinacio(combinacio32);
		assertArrayEquals(comb_32, correctes32);
		
		// contadorNombreIPos = 6
		int[] combinacio33 = {1,2,3,4,5,6};
		int[] correctes33 = {2,2,2,2,2,2};
		
		int[] comb_33 = solucio.comprovarCombinacio(combinacio33);
		assertArrayEquals(comb_33, correctes33);
		
		
		// Bucle contadorNombre
			// contadorNombre = 0
		int[] combinacio34 = {8,8,8,8,8,8};
		int[] correctes34 = {0,0,0,0,0,0};
		
		int[] comb_34 = solucio.comprovarCombinacio(combinacio34);
		assertArrayEquals(comb_34, correctes34);
		
			// contadorNombre = 1
		int[] combinacio35 = {8,8,8,8,8,1};
		int[] correctes35 = {1,0,0,0,0,0};
		
		int[] comb_35 = solucio.comprovarCombinacio(combinacio35);
		assertArrayEquals(comb_35, correctes35);
		
			// contadorNombre = 2
		int[] combinacio36 = {8,8,8,8,2,1};
		int[] correctes36 = {1,1,0,0,0,0};
		
		int[] comb_36 = solucio.comprovarCombinacio(combinacio36);
		assertArrayEquals(comb_36, correctes36);
		
			// contadorNombre = 3
		int[] combinacio37 = {8,8,8,3,2,1};
		int[] correctes37 = {1,1,1,0,0,0};
		
		int[] comb_37 = solucio.comprovarCombinacio(combinacio37);
		assertArrayEquals(comb_37, correctes37);
		
			// contadorNombre = 4
		int[] combinacio38 = {8,8,4,3,2,1};
		int[] correctes38 = {1,1,1,1,0,0};
		
		int[] comb_38 = solucio.comprovarCombinacio(combinacio38);
		assertArrayEquals(comb_38, correctes38);
		
			// contadorNombre = 5
		int[] combinacio39 = {8,5,4,3,2,1};
		int[] correctes39 = {1,1,1,1,1,0};
		
		int[] comb_39 = solucio.comprovarCombinacio(combinacio39);
		assertArrayEquals(comb_39, correctes39);
		
			// contadorNombre = 6
		int[] combinacio40 = {6,5,4,3,2,1};
		int[] correctes40 = {1,1,1,1,1,1};
		
		int[] comb_40 = solucio.comprovarCombinacio(combinacio40);
		assertArrayEquals(comb_40, correctes40);		

	}

}
