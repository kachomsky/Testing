/**
 * 
 */
package game;

/**
 * Esta clase se encarga de almacenar la información sobre los barcos de la partida. 
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Barco {
	/****** ATRIBUTOS ******	
	/**
	 * Número de casillas que ocupa el barco.
	 */
	private int tamaño;
	/**
	 * Lista con las casillas que ocupa el barco.
	 */
	private Casilla[] casillasBarco;
	/**
	 * Atributo que permite saber si el barco ha sido hundido.
	 */
	private boolean hundido = false;
	/**
	 * Atributo que indica la orientación del barco: true -> vertical
	 * 												 false -> horizontal
	 */
	private boolean orientacionVertical;
		
	
	/****** FUNCIONES ******
	/**
	 * Función que inicializa los atributos de la clase.
	 * @param tamaño: tamaño del barco.
	 */
	public Barco(int tamaño) {
		this.tamaño = tamaño;
		this.casillasBarco = new Casilla[tamaño];
	}
	
	/**
	 * Función que devuelve el tamaño del barco (número de casillas).
	 * @return tamaño: número de casillas que ocupa el barco.
	 */
	public int getTamaño() {
		return this.tamaño;
	}
	
	/**
	 * Función que devuelve la lista con las casillas donde está situado el barco.
	 * @return casillasBarco: lista con las casillas del barco.
	 */
	public Casilla[] getCasillasBarco() {
		return this.casillasBarco;
	}
	
	/**
	 * Función que devuelve el estado del barco.
	 * @return hundido: estado del barco (hundido o no).
	 */
	public boolean getHundido(){
		return this.hundido;
	}
	
	/**
	 * Función que permite cambiar el estado del barco.
	 * @param hundido: true si el barco está hundido, false si no.
	 */
	public void setHundido(boolean hundido){
		this.hundido = hundido;
	}
	
	/**
	 * Función que devuelve la orientación del barco.
	 * @return orientacionVertical: indica si la orientación del barco es vertical o no.
	 * 								True: vertical.
	 * 								False: horizontal.
	 */
	public boolean getOrientacion() {
		return this.orientacionVertical;
	}
	
	/**
	 * Función que permite cambiar la orientación del barco.
	 * @param orientacion: nueva orientación del barco.
	 */
	public void setOrientacion(boolean orientacion) {
		this.orientacionVertical = orientacion;
	}
	
	/**
	 * Función que convierte la orientación en forma de letra a forma booleana y la introduce al barco.
	 * @param c: coordenada en forma de letra
	 * @return true: la orientación es correcta.
	 * 		   false: la orientación no es correcta.
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
	 * Función que añade cada casilla que ocupa el barco.
	 * @param x: coordenada X inicial del barco.
	 * @param y: coordenada y inicial del barco.
	 */
	public void addCasillas(int x, int y){
		for (int i = 0; i < this.tamaño; i++) {
			if (orientacionVertical) {
				this.casillasBarco[i] = new Casilla(x, y+i, 1);
			} else {
				this.casillasBarco[i] = new Casilla(x + i, y, 1);
			}
		}
	}
	
	/**
	 * Función que comprueba si la orientación seleccionada está permitida.
	 * @param x: coordenada X.
	 * @param y: coordenada Y.
	 * @param mapa: mapa al cual pertenece el barco.
	 * @return: true -> orientación correcta
	 * 			false -> orientación incorrecta
	 */
	public boolean orientacionPermitida(int x, int y, Mapa mapa) {
		if (!mapa.casillaExistente(x, y)) {
			return false;
		} else if (!mapa.casillasLibres(this, x, y)) {
			return false;
		} else if(this.orientacionVertical) {		// vertical
			if (!mapa.casillaExistente(x, y + this.tamaño - 1)) {
				return false;
			}
		} else {
			if (!mapa.casillaExistente(x + this.tamaño - 1, y)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Función que permite elegir al usuario una orientación válida para la posición del barco.
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
	 * Función que gestiona la colocación de los barcos en el mapa.
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
	 * Función que comprueba si el barco en cuestión está hundido o no.
	 * @return true: el barco está hundido.
	 * 		   false: el barco no está hundido.
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
