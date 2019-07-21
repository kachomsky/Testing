package game;

public class Combinacio {
	
	private int nNombres;
	
	public Combinacio(int nNombres) {
		this.nNombres = nNombres;
	}
	
	/*
	 * Demana a l'usuari que introdueixi una combinacio de 
	 */
	public int[] obtenirCombinacio(Nombres generador) {
		int[] combinacio = new int[this.nNombres];
		for (int i = 0; i < nNombres; i++) {
			combinacio[i] = generador.obtenirNombreValid(i+1);
		}
		return combinacio; 
	}	

}
