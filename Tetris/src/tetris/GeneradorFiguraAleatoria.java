package tetris;

import java.util.ArrayList;
import java.util.Collections;

public class GeneradorFiguraAleatoria {
	
	private ArrayList<Integer> figuraSeguent = new ArrayList<Integer>();
	
	/*
	 * Genera un nou índex de figura de manera "aleatoria". 
	 * Aquest sistema fa que el jugador no pugui saber quina figura li sortirà a 
	 * continuació, però també evita que una figura surti molts pocs cops.
	 */
	public int novaFigura() {
		if (this.figuraSeguent.isEmpty()) {
			Collections.addAll(this.figuraSeguent, Figura.O, Figura.I, Figura.Z, Figura.S,
					Figura.L, Figura.T, Figura.P);
			Collections.shuffle(this.figuraSeguent);
			
		}
		int novaFigura = this.figuraSeguent.get(0);
		this.figuraSeguent.remove(0);
		return novaFigura;
	}


}
