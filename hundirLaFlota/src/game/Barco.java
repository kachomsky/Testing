/**
 * 
 */
package game;

/**
 * Esta clase se encarga de almacenar la informaci�n sobre los barcos de la partida. 
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Barco {
	/****** ATRIBUTOS ******	
	/**
	 * N�mero de casillas que ocupa el barco.
	 */
	private int tama�o;
	/**
	 * Lista con las casillas que ocupa el barco.
	 */
	private Casilla[] casillasBarco;
	/**
	 * Atributo que permite saber si el barco ha sido hundido.
	 */
	private boolean hundido = false;
	/**
	 * Atributo que indica la orientaci�n del barco: true -> vertical
	 * 												 false -> horizontal
	 */
	private boolean orientacionVertical;
		
	
	/****** FUNCIONES ******
	/**
	 * Funci�n que inicializa los atributos de la clase.
	 * @param tama�o: tama�o del barco.
	 */
	public Barco(int tama�o) {
		this.tama�o = tama�o;
		this.casillasBarco = new Casilla[tama�o];
	}
	
	/**
	 * Funci�n que devuelve el tama�o del barco (n�mero de casillas).
	 * @return tama�o: n�mero de casillas que ocupa el barco.
	 */
	public int getTama�o() {
		return this.tama�o;
	}
	
	/**
	 * Funci�n que devuelve la lista con las casillas donde est� situado el barco.
	 * @return casillasBarco: lista con las casillas del barco.
	 */
	public Casilla[] getCasillasBarco() {
		return this.casillasBarco;
	}
	
	/**
	 * Funci�n que devuelve el estado del barco.
	 * @return hundido: estado del barco (hundido o no).
	 */
	public boolean getHundido(){
		return this.hundido;
	}
	
	/**
	 * Funci�n que permite cambiar el estado del barco.
	 * @param hundido: true si el barco est� hundido, false si no.
	 */
	public void setHundido(boolean hundido){
		this.hundido = hundido;
	}
	
	/**
	 * Funci�n que devuelve la orientaci�n del barco.
	 * @return orientacionVertical: indica si la orientaci�n del barco es vertical o no.
	 * 								True: vertical.
	 * 								False: horizontal.
	 */
	public boolean getOrientacion() {
		return this.orientacionVertical;
	}
	
	/**
	 * Funci�n que permite cambiar la orientaci�n del barco.
	 * @param orientacion: nueva orientaci�n del barco.
	 */
	public void setOrientacion(boolean orientacion) {
		this.orientacionVertical = orientacion;
	}
	
	/**
	 * Funci�n que convierte la orientaci�n en forma de letra a forma booleana y la introduce al barco.
	 * @param c: coordenada en forma de letra
	 * @return true: la orientaci�n es correcta.
	 * 		   false: la orientaci�n no es correcta.
	 */
	public boolean charOrientacionToBool(char c){
		if(c == 'v'){
			setOrientacion(true);
			return true;
		}else if (c == 'h') {
			setOrientacion(false);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Funci�n que a�ade cada casilla que ocupa el barco.
	 * @param x: coordenada X inicial del barco.
	 * @param y: coordenada y inicial del barco.
	 */
	public void addCasillas(int x, int y){
		for (int i = 0; i < this.tama�o; i++) {
			if (orientacionVertical) {
				this.casillasBarco[i] = new Casilla(x, y+i, 1);
			} else {
				this.casillasBarco[i] = new Casilla(x + i, y, 1);
			}
		}
	}
	
	/**
	 * Funci�n que comprueba si la orientaci�n seleccionada est� permitida.
	 * @param x: coordenada X.
	 * @param y: coordenada Y.
	 * @param mapa: mapa al cual pertenece el barco.
	 * @return: true -> orientaci�n correcta
	 * 			false -> orientaci�n incorrecta
	 */
	public boolean orientacionPermitida(int x, int y, Mapa mapa) {
		if (!mapa.casillaExistente(x, y)) {
			return false;
		} else if (!mapa.casillasLibres(this, x, y)) {
			return false;
		} else if(this.orientacionVertical) {		// vertical
			if (!mapa.casillaExistente(x, y + this.tama�o - 1)) {
				return false;
			}
		} else {
			if (!mapa.casillaExistente(x + this.tama�o - 1, y)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Funci�n que permite elegir al usuario una orientaci�n v�lida para la posici�n del barco.
	 * @param x: coordenada X.
	 * @param y: coordenada Y.
	 * @param mapa: mapa al cual pertenece el barco.
	 */
	public boolean pedirOrientacion(int x, int y, Mapa mapa, char orientacionChar) {	
		boolean orientacionExistente = charOrientacionToBool(orientacionChar);
		boolean orientacionCorrecta = orientacionPermitida(x, y, mapa);
				
		if (!orientacionExistente && orientacionCorrecta) {
			return false;
		}
		return true;
	}
	
	/**
	 * Funci�n que gestiona la colocaci�n de los barcos en el mapa.
	 * @param mapa: mapa en el que se deben colocar los barcos.
	 * @param x: coordenada X donde colocar el barco.
	 * @param y: coordenada Y donde colocar el barco.
	 * @param orientacionChar: orientacion del barco a colocar.
	 * @return true: se ha podido colocar el barco.
	 * 		   false: se ha podido colocar el barco.
	 */
	public boolean colocarBarco(Mapa mapa, int x, int y,char orientacionChar) {
		
		pedirOrientacion(x, y, mapa, orientacionChar);
		
		if(!orientacionPermitida(x, y, mapa)) {
			return false;
		}
		
		addCasillas(x, y);
		
		for (int i = 0; i < this.casillasBarco.length; i++) {
			mapa.cambiarEstado(1, this.getCasillasBarco()[i].getCoordenadaX(), this.getCasillasBarco()[i].getCoordenadaY());
		}
		
		return true;
	}
	
	/**
	 * Funci�n que comprueba si el barco en cuesti�n est� hundido o no.
	 * @return true: el barco est� hundido.
	 * 		   false: el barco no est� hundido.
	 */
	public boolean comprobarHundido(){
		int contador = 0;
		for (int i = 0; i < this.casillasBarco.length; i++) {
			if (this.casillasBarco[i].getEstado() == 3) {
				contador++;
			}
		}
		if (contador == this.casillasBarco.length) {
			return true;
		}
		else {
			return false;
		}
	}
}
