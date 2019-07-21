package tetris;
import java.util.ArrayList;
import java.util.Collections;


public class MockGeneradorFiguraAleatoria {
	
	private ArrayList<Integer> figuraSeguent = new ArrayList<Integer>();
	/*
	 * Genera un array amb les figures sense barrejarles, per tal de poder
	 * fer una partida al jubula amb unes figures ple-establertes
	 */
	public int novaFigura() {
		if (this.figuraSeguent.isEmpty()) {
			Collections.addAll(this.figuraSeguent, Figura.O, Figura.I, Figura.Z, Figura.S,
					Figura.L, Figura.T, Figura.P);
		}
		int novaFigura = this.figuraSeguent.get(0);
		this.figuraSeguent.remove(0);
		return novaFigura;
	}
}
