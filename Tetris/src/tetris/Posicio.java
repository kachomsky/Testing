package tetris;

/*
 * Classe que permet guardar una posicio del tauler.
 */
public class Posicio {
	/*
	 * Coordenada X de la posicio.
	 */
	private int x;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	/*
	 * Coordenada Y de la posicio.
	 */
	private int y;
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Posicio(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
