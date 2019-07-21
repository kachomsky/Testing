package game;

import java.util.Scanner;

public class Menu {
	
	public int maxNombres = 4;
	
	public static void main(String argv[]) {
		
		Menu menu = new Menu();
		int opcio = 1;
		
		while (opcio != 3) {
			opcio = menu.menuJoc();
			
			switch(opcio) {
			case 1:
				Nombres generador = new Nombres();
				Solucio solucio = new Solucio(menu.maxNombres);
				solucio.generarCombinacio(generador);
				Combinacio combinacio = new Combinacio(menu.maxNombres);
				
				int jugada = 1;
				boolean victoria = false;
				
				
				// Mostra la solució generada.
				// Aquest fragment només s'utilitza per demostrar que funciona el codi.
				/*
				System.out.print(" Solucio ");
				for (int valor : solucio.getCombinacio()) {
					System.out.print(valor);
				}
				System.out.println("");
				*/
				
				System.out.println("Has d'endevinar la combinacio de " + menu.maxNombres + " xifres (de l'1 al 8) en un màxim de 12 intents.");
				System.out.println("");
				
				System.out.println("Codi de la resposta: ");
				System.out.println("   0 -> nombre incorrecte.");
				System.out.println("   1 -> nombre correcta fora de posicio.");
				System.out.println("   2 -> nombre i posicio correctes.");
				System.out.println("");
				System.out.println("******************************************************");
				System.out.println("");
				
				while(jugada <= 12 && !victoria ) {
					System.out.println("Introdueix combinacio de " + menu.maxNombres + " nombres, de un en un.");
					
					int[] resposta = combinacio.obtenirCombinacio(generador);
	
					int[] correctes = solucio.comprovarCombinacio(resposta);
					
					System.out.println("");
					System.out.print(jugada + ". ");
					for (int valor : correctes) {
						System.out.print(valor);
					}
					System.out.println("");
					System.out.println("");
					
					victoria = menu.victoria(correctes);
					jugada++;
				}
				
				if (victoria) {
					System.out.println("Has guanyat!");
				} else {
					System.out.println("Has perdut");
					System.out.println("La solucio era " + solucio.getCombinacio()[0] + solucio.getCombinacio()[1] + solucio.getCombinacio()[2]
							+ solucio.getCombinacio()[3]);
				}
				break;
				
			case 2:
				menu.menuCanviarNombreXifres();
				break;
				
			case 3:
				break;

			}
			
			for (int i = 0; i < 3; i++) {
				System.out.println("");
			}
			
		}	
		
	}
	
	public int menuJoc() {
		System.out.println("      ******************");
		System.out.println("      *   MASTERMIND   *");
		System.out.println("      ******************");
		System.out.println("");
		
		System.out.println("    1. Jugar");
		System.out.println("    2. Cambiar dificultat");
		System.out.println("    3. Sortir");
		System.out.println("");
		
		Scanner scanner = new Scanner(System.in);
		int opcio = scanner.nextInt();
		
		while(opcio < 1 || opcio > 3) {
			System.out.println("");
			System.out.println("      Opcio invalida");
			System.out.println("");
			opcio = scanner.nextInt();
		}
		
		return opcio;
	}
	
	public boolean canviarNombreXifres(int dificultat) {
		boolean correcte = false;
		
		switch(dificultat) {
		case 1:
			this.maxNombres = 4;
			correcte = true;
			break;
		case 2:
			this.maxNombres = 6;
			correcte = true;
			break;
		default:
			break;			
		}
		
		return correcte;
	}
	
	public void menuCanviarNombreXifres() {
		System.out.println("");
		System.out.println("      ** Dificultats **");
		System.out.println("    1. Normal (4 nombres)");
		System.out.println("    2. Difícil (6 nomres)");
		System.out.println("");
		
		Scanner scanner = new Scanner(System.in);
		
		int dificultat = scanner.nextInt();
		
		boolean correcte = canviarNombreXifres(dificultat);
		
		while(!correcte) {
			System.out.println("");
			System.out.println("      Opcio invalida");
			System.out.println("");
			dificultat = scanner.nextInt();
			correcte = canviarNombreXifres(dificultat);
		}

	}
	
	public boolean victoria(int[] resultat) {
		for (int valor : resultat) {
			if (valor != 2) {
				return false;
			}
		}
		return true;
	}

}
