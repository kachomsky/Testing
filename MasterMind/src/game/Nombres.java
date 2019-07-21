package game;

import java.util.Random;
import java.util.Scanner;

public class Nombres {
	
	public Nombres() {	
	}
	
	/*
	 * Comproba si el nombre es vàlid per al joc (valor entre 1 i 8, els dos inclosos).
	 */
	public boolean nombreValid(int nombre) {
		if (nombre > 0 && nombre < 9) {
			return true;
		}
		return false;
	}
	
	/*
	 * Genera un nombre aleatori entre 1 i 8 (inclosos).
	 */
	public int generarNombreAleatori() {
		Random random = new Random();
		return random.nextInt((9) - 1) + 1;
	}
	
	/*
	 * Demana a l'usuari que introdueixi un nombre.
	 */
	public int obtenirNombre() {
		Scanner scanner = new Scanner(System.in);
		int nombre = scanner.nextInt();
		return nombre;
	}
	
	/*
	 * Demana a l'usuari que introdueixi un nombre i comproba que aquest sigui
	 * vàlid per al joc.
	 */
	public int obtenirNombreValid(int xifra) {
		System.out.print("Introdueix nombre " + xifra + ": ");
		int nombre = obtenirNombre();
		while (!nombreValid(nombre)) {
			System.out.print("El nombre introduit es invalid. Escull un nou nombre: ");
			nombre = obtenirNombre();
		}
		return nombre;
	}

}
