/**
 * 
 */
package game;

/**
 * Esta clase se encarga de guardar la informaci�n de cada jugador de la partida.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Jugador {
	/****** ATRIBUTOS ******
	/**
	 * Nombre del jugador.
	 */
	private String nombre;
	/**
	 * Mapa con los barcos del jugador.
	 */
	private Mapa mapaJugador;
	
	
	/****** FUNCIONES ******
	/**
	 * Funci�n que inicializa los atributos de la clase
	 * @param nombre: nombre del jugador
	 */
	public Jugador(String nombre) {
		this.setNombre(nombre);
		this.mapaJugador = new Mapa();
	}
	
	/**
	 * Funci�n que devuelve el nombre del Jugador.
	 * @return nombre: nombre del Jugador.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Funci�n que permite indicar el nombre del Jugador.
	 * @param nombre: nombre del Jugador.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Funci�n que devuelve el mapa del Jugador.
	 * @return mapaJugador: mapa del Jugador.
	 */
	public Mapa getMapaJugador() {
		return mapaJugador;
	}

	/**
	 * Funci�n que permite indicar el mapa del Jugador.
	 * @param mapaJugador: mapa del Jugador.
	 */
	public void setMapaJugador(Mapa mapaJugador) {
		this.mapaJugador = mapaJugador;
	}	

}
