/**
 * 
 */
package game;

/**
 * Esta clase se encarga de guardar la información de cada jugador de la partida.
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
	 * Función que inicializa los atributos de la clase
	 * @param nombre: nombre del jugador
	 */
	public Jugador(String nombre) {
		this.setNombre(nombre);
		this.mapaJugador = new Mapa();
	}
	
	/**
	 * Función que devuelve el nombre del Jugador.
	 * @return nombre: nombre del Jugador.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Función que permite indicar el nombre del Jugador.
	 * @param nombre: nombre del Jugador.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Función que devuelve el mapa del Jugador.
	 * @return mapaJugador: mapa del Jugador.
	 */
	public Mapa getMapaJugador() {
		return mapaJugador;
	}

	/**
	 * Función que permite indicar el mapa del Jugador.
	 * @param mapaJugador: mapa del Jugador.
	 */
	public void setMapaJugador(Mapa mapaJugador) {
		this.mapaJugador = mapaJugador;
	}	

}
