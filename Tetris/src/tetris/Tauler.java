package tetris;

import java.awt.Color;
import java.util.Arrays;
import java.awt.Graphics;

public class Tauler {
	
	public static int nFiles = 21;
	public static int nColumnes = 10;
	private int amplada = 326;
	private int alcada = 680;
	private int midaCasella = 25;
	
	private Posicio posicio = new Posicio(0, 0);
	
	/*
	 * Tauler que conté les Figures encaixades del joc.
	 */
	private Color[][] matriuTauler = new Color[nFiles][nColumnes];
	
	public Color[][] getTauler() {
		return this.matriuTauler;
	}
	
	public Tauler() {
		for (Color[] fila : this.matriuTauler) {
			Arrays.fill(fila, Color.BLACK);
		}
	}
	
	/*
	 * Pinta el Tauler de joc amb tot el seu contigut (les Figures que estan encaixades).
	 */
	public void pintarTauler(Graphics graphics) {
		// Pintar fons del tauler (buït)
		graphics.setColor(Color.BLACK);
		graphics.fillRect(this.posicio.getX(), this.posicio.getY(),
				this.amplada, this.alcada);
		
		// Pintar limits tauler i el seu contingut
		for (int fila = 0; fila < nFiles; fila++) {
			graphics.setColor(Color.GRAY);
			graphics.fillRect(0, fila * 26, this.midaCasella, this.midaCasella);
			
			// Contingut
			for (int columna = 0; columna < nColumnes; columna++) {
				graphics.setColor(this.matriuTauler[fila][columna]);
				graphics.fillRect((columna + 1) * 26, (fila) * 26, this.midaCasella, this.midaCasella);
				
			}
			graphics.setColor(Color.GRAY);
			graphics.fillRect(11 * 26, fila * 26, this.midaCasella, this.midaCasella);
		}
		for (int columna = 0; columna <= (nColumnes + 1); columna++) {
			graphics.fillRect(columna * 26, nFiles * 26, this.midaCasella, this.midaCasella);
		}
	}
	
	/*
	 * Pinta la puntuació actual de la partida.
	 */
	public void pintarPuntuacio(Graphics graphics, int puntuacio) {
		graphics.setColor(Color.WHITE);
		graphics.drawString("Puntuacio: " + puntuacio, 25, 600);
	}

	/*
	 * Comprova si la figura solapa amb alguna Figura encaixada en el
	 * Tauler o amb el Tauler mateix.
	 * False: no solapa, True: si solapa.
	 */
	public boolean solapa(Figura figura, int movimentX, int movimentY) {
		boolean solapaBool = false;
		for (Posicio posicioMascara : figura.getMascara()) {
			Posicio posicioAComprovar = 
					new Posicio(posicioMascara.getX() + figura.getPosicio().getX() + movimentX, 
					posicioMascara.getY() + figura.getPosicio().getY() + movimentY);
			if (posicioValida(posicioAComprovar)) {
				if (matriuTauler[posicioAComprovar.getY()]
						[posicioAComprovar.getX()] != Color.BLACK) {
					solapaBool = true;
				}
			} else {
				solapaBool = true;
			}
		}
		return solapaBool;
	}
	
	/*
	 * Comprova si la posició donada existeix dins del Tauler.
	 */
	public boolean posicioValida(Posicio posicio) {
		if (posicio.getX() >= 0 && posicio.getX() <= (nColumnes - 1) &&
				posicio.getY() >= 0 && posicio.getY() <= (nFiles - 1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Encaixa la Figura en el Tauler.
	 */
	public void encaixarFigura(Figura figura) {
		for (Posicio posicio : figura.getMascara()) {
			this.matriuTauler[figura.getPosicio().getY() + posicio.getY()]
					[figura.getPosicio().getX() + posicio.getX()] = figura.getColor();
		}		
	}
	
	/*
	 * Comprova si en el Tauler hi ha alguna fila completada, i en el cas de
	 * que hi hagi alguna, l'elimina.
	 */
	public int comprovarFilesCompletades() {
		int filesCompletades = 0;
		boolean forat;
		for (int fila = nFiles - 1; fila >= 0; fila--) {
			forat = false;
			for (int columna = 0; columna < nColumnes; columna++) {
				if (this.matriuTauler[fila][columna] == Color.BLACK) {
					forat = true;
					break;
				}
			}
			if (!forat) {
				eliminarFilaCompletada(fila);
				filesCompletades++;
				fila++;
			}
		}
		return filesCompletades;
	}
	
	/*
	 * Elimina les files completades del Tauler.
	 */
	public void eliminarFilaCompletada(int filaAEliminar) {
		if (filaAEliminar != 0) {
			for (int fila = filaAEliminar; fila > 0; fila--) {
				for (int columna = 0; columna < nColumnes; columna++) {
					this.matriuTauler[fila][columna] = this.matriuTauler[fila - 1][columna];
				}
			}
			for (int columna = 0; columna < nColumnes; columna++) {
				this.matriuTauler[0][columna] = Color.BLACK;
			}
		} else {
			for (int columna = 0; columna < nColumnes; columna++) {
				this.matriuTauler[0][columna] = Color.BLACK;
			}
		}
	}
	
	
}
