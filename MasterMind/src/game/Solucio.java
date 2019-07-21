package game;

import java.util.Arrays;

public class Solucio {
	
	private int[] solucio;
	private int nNombres;
	
	public Solucio(int nNombres) {
		this.nNombres = nNombres;
		solucio = new int[nNombres];
	}
	
	public int[] getCombinacio() {
		return this.solucio;
	}
	
	public void setCombinacio(int[] combinacio){
		this.solucio = combinacio;
	}
	
	/*
	 * Genera de manera aleatoria nNombres nombres per a que sigui la 
	 * solució del joc.
	 */
	public void generarCombinacio(Nombres generador) {
		for (int i = 0; i < nNombres; i++) {
			this.solucio[i] = generador.generarNombreAleatori();
		}
		
	}
	
	/*
	 * Aquesta funcio retorna un array amb 2,1 o 0 que indiquen les condicions seguents:
	 * 2->nombre i posicio correctes
	 * 1->nombre correcte pero posicio incorrecte
	 * 0->ni nombre ni posicio correctes
	 */
	public int[] comprovarCombinacio(int[] combinacio) {
		int[] correctes = new int[nNombres];
		Arrays.fill(correctes, 0);
		int[] vegadesSurtNombreSolucio = new int[8];	// Conta quantes vegades surt un nombre en la solució
		Arrays.fill(vegadesSurtNombreSolucio, 0);
		int contadorNombreIPos = 0; 	// contador de numeros iguals amb la mateixa posicio
		int contadorNombre = 0; 	// contador de numeros amb mateix Nombre, posicio diferent
		
		for (int i = 0; i < nNombres; i++) {		// Conta les vegades que surt cada nombre de la solució
			vegadesSurtNombreSolucio[this.solucio[i] - 1] += 1;
		}
		
		for (int i = 0; i < nNombres; i++) {	
			if (combinacio[i] == this.solucio[i]) {
				contadorNombreIPos++;
				
				// Aquesta comprobació controla que no es produeixi el següent error.
				// Si tenim la solució:     1 2 3 3
				// I donem la combinació:   3 3 3 5
				// Com que el codi mira la combinació de esquerra a dreta, si no fessim aquesta comprobació ens retornaria
				// el següent:  2 1 1 1, ja que quan arriba al tercer 3 el conta encara que ja haguem arribat al nombre de vegades
				// que apareix el valor 3 en la solució. Amb aquest if-else, obtindrem el següent:    2 1 0 0, que es el resultat correcte.
				
				if (vegadesSurtNombreSolucio[combinacio[i] - 1] != 0) {
					vegadesSurtNombreSolucio[combinacio[i] - 1] -= 1;
				} else {
					contadorNombre -= 1;
				}
			} else {
				for (int j = 0; j < nNombres; j++) {
					if (combinacio[i] == this.solucio[j]) {
						
						// Aquesta comprobació controla que no es produeixi el següent error.
						// Si tenim la solució:     1 2 3 3
						// I donem la combinació:   3 3 2 2
						// Com que el codi mira la combinació de esquerra a dreta, si no fessim aquesta comprobació ens retornaria
						// el següent:  1 1 1 1, ja que quan arriba al segon 2 el conta encara que ja haguem arribat al nombre de vegades
						// que apareix el valor 2 en la solució. Amb aquest if, obtindrem el següent:    1 1 1 0, que es el resultat correcte.
						if (vegadesSurtNombreSolucio[combinacio[i] - 1] != 0) {
							contadorNombre++;
							vegadesSurtNombreSolucio[combinacio[i] - 1] -= 1;
							j = nNombres;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < contadorNombreIPos; i++) {
			correctes[i] = 2;
		}
		
		for (int i = 0; i < contadorNombre; i++) {
			correctes[i+contadorNombreIPos] = 1;
		}
	
		return correctes;
	}

}
