package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Figura {
	
	public final static int O = 0;
	public final static int I = 1;
	public final static int Z = 2;
	public final static int S = 3;
	public final static int L = 4;
	public final static int T = 5;
	public final static int P = 6;
	
	/*
	 * Posició actual de la Figura.
	 */
	private Posicio posicio;
	
	public Posicio getPosicio() {
		return this.posicio;
	}
	
	public void setPosicio(Posicio posicio) {
		this.posicio = posicio;
	}
	
	/*
	 * Màscara amb les posicions de la Figura segons les rotacions que
	 * pot tenir.
	 */
	private Posicio[][] masqueres;
	
	// Retorna la màscara corresponent a la rotació actual.
	public Posicio[] getMascara() {
		return this.masqueres[this.rotacioActual - 1];
	}
	
	/*
	 * Color de la Figura.
	 */
	private Color color;
	
	public Color getColor() {
		return this.color;
	}
	
	/*
	 * Nombre total de rotacions que pot fer la Figura.
	 */
	private int nRotacions;
	
	public int getNRotacions() {
		return this.nRotacions;
	}
	
	/*
	 * Rotació actual en la que es troba la figura.
	 */
	private int rotacioActual = 1;
	
	public int getRotacioActual() {
		return this.rotacioActual;
	}
	
	public void setRotacioActual(int rotacioActual){
		this.rotacioActual = rotacioActual;
	}
	
	/*
	 * Index que identifica la Figura.
	 */
	private int indexFigura;
	
	public int getIndexFigura() {
		return this.indexFigura;
	}
	
	public Figura(int indexFigura, Posicio posicioInicial) {
		this.indexFigura = indexFigura;
		this.posicio = posicioInicial;
		
		switch(indexFigura) {
		case O:
			this.nRotacions = 1;
			this.color = Color.YELLOW;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(0, 0);
			this.masqueres[0][1] = new Posicio(0, 1);
			this.masqueres[0][2] = new Posicio(1, 0);
			this.masqueres[0][3] = new Posicio(1, 1);			
			break;
			
		case I:
			this.nRotacions = 2;
			this.color = Color.CYAN;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(0, 0);
			this.masqueres[0][1] = new Posicio(0, 1);
			this.masqueres[0][2] = new Posicio(0, 2);
			this.masqueres[0][3] = new Posicio(0, 3);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(0, 0);
			this.masqueres[1][1] = new Posicio(1, 0);
			this.masqueres[1][2] = new Posicio(2, 0);
			this.masqueres[1][3] = new Posicio(3, 0);
			break;
		
		case Z:
			this.nRotacions = 2;
			this.color = Color.RED;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(0, 0);
			this.masqueres[0][1] = new Posicio(1, 0);
			this.masqueres[0][2] = new Posicio(1, 1);
			this.masqueres[0][3] = new Posicio(2, 1);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(1, 0);
			this.masqueres[1][1] = new Posicio(0, 1);
			this.masqueres[1][2] = new Posicio(1, 1);
			this.masqueres[1][3] = new Posicio(0, 2);
			break;
			
		case S:
			this.nRotacions = 2;
			this.color = Color.GREEN;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(1, 0);
			this.masqueres[0][1] = new Posicio(2, 0);
			this.masqueres[0][2] = new Posicio(0, 1);
			this.masqueres[0][3] = new Posicio(1, 1);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(0, 0);
			this.masqueres[1][1] = new Posicio(0, 1);
			this.masqueres[1][2] = new Posicio(1, 1);
			this.masqueres[1][3] = new Posicio(1, 2);	
			break;
			
		case L:
			this.nRotacions = 4;
			this.color = Color.BLUE;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(0, 1);
			this.masqueres[0][1] = new Posicio(1, 1);
			this.masqueres[0][2] = new Posicio(2, 1);
			this.masqueres[0][3] = new Posicio(2, 0);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(1, 0);
			this.masqueres[1][1] = new Posicio(1, 1);
			this.masqueres[1][2] = new Posicio(1, 2);
			this.masqueres[1][3] = new Posicio(2, 2);
			// Rotacio 3
			this.masqueres[2][0] = new Posicio(0, 1);
			this.masqueres[2][1] = new Posicio(1, 1);
			this.masqueres[2][2] = new Posicio(2, 1);
			this.masqueres[2][3] = new Posicio(0, 2);
			// Rotacio 4
			this.masqueres[3][0] = new Posicio(1, 0);
			this.masqueres[3][1] = new Posicio(1, 1);
			this.masqueres[3][2] = new Posicio(1, 2);
			this.masqueres[3][3] = new Posicio(0, 0);
			break;
			
		case T:
			this.nRotacions = 4;
			this.color = Color.PINK;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(1, 0);
			this.masqueres[0][1] = new Posicio(0, 1);
			this.masqueres[0][2] = new Posicio(1, 1);
			this.masqueres[0][3] = new Posicio(2, 1);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(1, 0);
			this.masqueres[1][1] = new Posicio(0, 1);
			this.masqueres[1][2] = new Posicio(1, 1);
			this.masqueres[1][3] = new Posicio(1, 2);
			// Rotacio 3
			this.masqueres[2][0] = new Posicio(0, 1);
			this.masqueres[2][1] = new Posicio(1, 1);
			this.masqueres[2][2] = new Posicio(2, 1);
			this.masqueres[2][3] = new Posicio(1, 2);
			// Rotacio 4
			this.masqueres[3][0] = new Posicio(1, 0);
			this.masqueres[3][1] = new Posicio(1, 1);
			this.masqueres[3][2] = new Posicio(2, 1);
			this.masqueres[3][3] = new Posicio(1, 2);	
			break;
			
		case P:
			this.nRotacions = 4;
			this.color = Color.ORANGE;
			this.masqueres = new Posicio[this.nRotacions][4];
			// Rotacio 1
			this.masqueres[0][0] = new Posicio(0, 1);
			this.masqueres[0][1] = new Posicio(1, 1);
			this.masqueres[0][2] = new Posicio(2, 1);
			this.masqueres[0][3] = new Posicio(0, 0);
			// Rotacio 2
			this.masqueres[1][0] = new Posicio(1, 0);
			this.masqueres[1][1] = new Posicio(1, 1);
			this.masqueres[1][2] = new Posicio(1, 2);
			this.masqueres[1][3] = new Posicio(2, 0);	
			// Rotacio 3
			this.masqueres[2][0] = new Posicio(0, 1);
			this.masqueres[2][1] = new Posicio(1, 1);
			this.masqueres[2][2] = new Posicio(2, 1);
			this.masqueres[2][3] = new Posicio(2, 2);
			// Rotacio 4
			this.masqueres[3][0] = new Posicio(1, 0);
			this.masqueres[3][1] = new Posicio(1, 1);
			this.masqueres[3][2] = new Posicio(1, 2);
			this.masqueres[3][3] = new Posicio(0, 2);
			break;
			
		default:
			this.nRotacions = -1;
			break;
		}
	}
	
	/*
	 * Pinta la Figura en el Tauler.
	 */
	public void pintarFigura(Graphics graphics) {
		graphics.setColor(this.color);
		for (Posicio posicio : this.masqueres[this.rotacioActual - 1]) {
			graphics.fillRect((this.posicio.getX() + posicio.getX() + 1) * 26, 
					(this.posicio.getY() + posicio.getY()) * 26, 25, 25);
		}
	}
	
	/*
	 * Mou la figura cap on ho indica el jugador (en horitzontal).
	 */
	public void moure(int movimentHoritzontal, Tauler tauler) {
		if (!tauler.solapa(this, movimentHoritzontal, 0)) {
			this.posicio.setX(this.posicio.getX() + movimentHoritzontal);
		}
	}
	
	/*
	 * Rota una vegada la Figura si és possible fer-ho (no solapa amb cap
	 * element del Tauler).
	 */
	public void rotar(Tauler tauler) {
		int possibleRotacio = ((this.rotacioActual) % this.nRotacions) + 1;
		Figura figuraGirada = new Figura(this.indexFigura, this.posicio);
		figuraGirada.setRotacioActual(possibleRotacio);
		if (!tauler.solapa(figuraGirada, 0, 0)) {
			this.rotacioActual = possibleRotacio;
		}
	}
	
	/*
	 * Baixa una posició la Figura.
	 * Retorna true si ha s'ha pogut realitzar el moviment i false si no.
	 */
	public boolean caure(Tauler tauler) {
		boolean movimentRealitzat = false;
		if (this.posicio.getY() + 1 <= Tauler.nFiles - 1) {
			if (!tauler.solapa(this, 0, 1)) {
				this.posicio.setY(this.posicio.getY() + 1);	
				movimentRealitzat = true;
			}
		}
		return movimentRealitzat;
	}

}
