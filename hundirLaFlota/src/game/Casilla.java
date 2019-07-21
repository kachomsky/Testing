/**
 * 
 */
package game;

/**
 * Esta clase se encarga de gestionar la informaci�n de las casillas que forman los mapas.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Casilla {
	/****** ATRIBUTOS ******
	/**
	 * Coordenada de la casilla seg�n el eje X.
	 */
	private int coordenadaX;
	/**
	 * Coordenada de la casilla seg�n el eje Y.
	 */
	private int coordenadaY;
	/**
	 * Estado de la casilla: 0 -> agua
	 * 						 1 -> contiene parte de un barco
	 * 						 2 -> agua, pero ha sido tocado
	 * 						 3 -> barco, pero ha sido tocado
	 */
	private int estado;
	/**
	 * Imagen que se muestra en pantalla de la casilla: - -> casilla sin tocar
	 * 													~ -> casilla tocada con agua
	 * 													x -> casilla tocada con barco
	 * 													* -> casilla sin tocar con barco							
	 */
	private String imagen = "-";
	
	
	/****** FUNCIONES ******
	/**
	 * Funci�n que inicializa los atributos de la clase.
	 * @param coordenadaX: coordenada de la casilla seg�n el eje X.
	 * @param coordenadaY: Coordenada de la casilla seg�n el eje Y.
	 * @param estado: estado de la casilla, determina cual es su funci�n.
	 */
	public Casilla(int coordenadaX, int coordenadaY, int estado) {
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.estado = estado;
	}
	
	/**
	 * Funci�n que devuelve el estado de la casilla.
	 * @return this.estado: estado de la casilla.
	 */
	public int getEstado() {
		return this.estado;
	}

	/**
	 * Funci�n que modifica el estado de la casilla.
	 * @param estado: nuevo estado de la casilla.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	/**
	 * Funci�n que devuelve la imagen de la casilla. 
	 * @return imagen: imagen de la casilla.
	 */
	public String getImagen() {
		return this.imagen;
	}
	
	/**
	 * Funci�n que modifica la imagen de la casilla. 
	 * @param imagen: nueva imagen de la casilla.
	 */
	public void setImagen(String imagen){
		this.imagen = imagen;
	}	
	
	/**
	 * Funci�n que devuelve la coordenada X de la casilla.
	 * @return coordenadaX: coordenada X de la casilla.
	 */
	public int getCoordenadaX(){
		return this.coordenadaX;
	}
	
	/**
	 * Funci�n que modifica la coordenada X de la casilla.
	 * @param coordenadaX: nuevo valor de la coordenada X.
	 */
	public void setCoordenadaX(int coordenadaX){
		this.coordenadaX = coordenadaX;
	}
	
	/**
	 * Funci�n que devuelve la coordenada Y de la casilla.
	 * @return coordenadaY: coordenada Y de la casilla.
	 */
	public int getCoordenadaY(){
		return this.coordenadaY;
	}
	
	/**
	 * Funci�n que modifica la coordenada Y de la casilla.
	 * @param coordenadaY: nuevo valor de la coordenada X.
	 */
	public void setCoordenadaY(int coordenadaY){
		this.coordenadaY = coordenadaY;
	}
}
