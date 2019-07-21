/**
 * 
 */
package game;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase se encarga de gestionar una partida del juego.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Juego {
	/****** Atributos ******/
	/**
	 * Array que guarda los dos jugadores de la partida.
	 */
	static Jugador jugadores[] = new Jugador[2];
	
	/****** FUNCIONES ******
	/**
	 * Función que limpia toda la información que se muestra en el terminal del juego.
	 */
	public static void limpiarPantalla(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Función que espera el input de Enter por parte del usuario.
	 */
	private static void presionarEnter() {
		String enter;
		do {
			Scanner scanner = new Scanner(System.in);
			enter = scanner.nextLine();
		}while (!enter.equals(""));	
	}
	
	/**
	 * Función que muestra el menú de inicio del juego.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void mostrarMenuInicio(){
		limpiarPantalla();
		for (int i = 0; i < 2; i++) {
			System.out.println("");
		}
		System.out.println("      ***********************");
		System.out.println("      *   HUNDIR LA FLOTA   *");
		System.out.println("      ***********************");
		System.out.println("");
		System.out.print("        Enter para empezar:");		
		presionarEnter();
		
		limpiarPantalla();
	}
	
	/**
	 * Función que genera el menú para crear los jugadores de la partida.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void menuCrearJugadores() throws IOException, InterruptedException {
		for (int i = 0; i < 2; i ++) {
			System.out.println("");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 2; i++) {
			System.out.print("   Introduce el nombre del jugador " + (i+1) + ": ");
			String nombre = scanner.nextLine();
			jugadores[i] = new Jugador(nombre);
			jugadores[i].getMapaJugador().inicializarTablero();
			System.out.println("");		
		}
		
		limpiarPantalla();
		
		System.out.println("");
		System.out.println("     ¡Que empiece la guerra!");	
		TimeUnit.SECONDS.sleep(2);
		limpiarPantalla();
	}
	
	/**
	 * Función que coloca los barcos de un jugador de la partida.
	 * @param jugador: jugador que está colocando los barcos.
	 */
	public static void colocarBarcosJugador(int jugador) {
		Scanner scanner = new Scanner(System.in);
		boolean colocadoCorrecto = false;
		String cabecera = jugadores[jugador].getMapaJugador().crearCabeceraTablero();
		boolean orientacionExiste = false;
		
		for (int i = 0; i < jugadores[jugador].getMapaJugador().getMaxBarcos(); i++) {
			System.out.println("**** TURNO DEL JUGADOR "+jugadores[jugador].getNombre()+" ****\n");
			jugadores[jugador].getMapaJugador().setCabeceraTablero(cabecera);
			jugadores[jugador].getMapaJugador().printTablero();
			
			System.out.println("Colocar barco de " + jugadores[jugador].getMapaJugador().getTamañoBarcos()[i] + " casillas. Elige la coordenada donde colocar extremo inicial.");
			System.out.println("(El eje horizontal corresponde a la X y el eje vertical a la Y).");
			int x = jugadores[jugador].getMapaJugador().pedirCoordenada("X", scanner);
			int y = jugadores[jugador].getMapaJugador().pedirCoordenada("Y", scanner);
			
			System.out.println("Ahora debes elegir la orientación del barco a partir de las coordenadas que has dado.");
			System.out.print("Orientacion vertical (hacia abajo) o horizontal (hacia derecha) (v/h): ");
			char orientacionChar = scanner.next().charAt(0);
			Barco b = new Barco(jugadores[jugador].getMapaJugador().getTamañoBarcos()[i]);
			orientacionExiste = b.pedirOrientacion(x, y, jugadores[jugador].getMapaJugador(), orientacionChar);
			while(!orientacionExiste){
				System.out.print("Orientacion no valida (v/h). Vuelve a introducir un valor: ");
				orientacionChar = scanner.next().charAt(0);
				orientacionExiste =  b.pedirOrientacion(x, y, jugadores[jugador].getMapaJugador(), orientacionChar);
			}
			colocadoCorrecto = jugadores[jugador].getMapaJugador().colocarBarcos(x,y,orientacionChar,b,i);
			
			while(!colocadoCorrecto){
				limpiarPantalla();
				System.out.println("**** TURNO DEL JUGADOR "+jugadores[jugador].getNombre()+" ****\n");
				jugadores[jugador].getMapaJugador().setCabeceraTablero(cabecera);
				jugadores[jugador].getMapaJugador().printTablero();
				
				System.out.println("En esta posicion no hay espacio para colocar el barco. Elije otra posicion:");
				System.out.println("Colocar barco de " + jugadores[jugador].getMapaJugador().getTamañoBarcos()[i] + " casillas. Elige la coordenada donde colocar extremo inicial.");
				x = jugadores[jugador].getMapaJugador().pedirCoordenada("X", scanner);
				y = jugadores[jugador].getMapaJugador().pedirCoordenada("Y", scanner);
				
				System.out.println("Ahora debes elegir la orientación del barco a partir de las coordenadas que has dado.");
				System.out.print("Orientacion vertical (hacia abajo) o horizontal (hacia derecha) (v/h): ");
				orientacionChar = scanner.next().charAt(0);
				orientacionExiste = b.pedirOrientacion(x, y, jugadores[jugador].getMapaJugador(), orientacionChar);
				while(!orientacionExiste){
					System.out.print("Orientacion no valida (v/h). Vuelve a introducir un valor: ");
					orientacionChar = scanner.next().charAt(0);
					orientacionExiste =  b.pedirOrientacion(x, y, jugadores[jugador].getMapaJugador(), orientacionChar);
				}
				colocadoCorrecto = jugadores[jugador].getMapaJugador().colocarBarcos(x,y,orientacionChar,b,i);
			}
		}
		
		System.out.println("**** TURNO DEL JUGADOR "+jugadores[jugador].getNombre()+" ****\n");
		jugadores[jugador].getMapaJugador().setCabeceraTablero(cabecera);
		jugadores[jugador].getMapaJugador().printTablero();
		
		System.out.println("-----------------------------------------------");
		System.out.println("-- Los barcos se han colocado correctamente! --");
		System.out.println("-----------------------------------------------\n");
	}
	
	/**
	 * Función que gestiona el ataque de un jugador.
	 * @param jugador: indice del jugador al cual le toca atacar.
	 * @return ganador: contiene el jugador que ha ganado, en caso contrario tiene valor null.
	 */
	public static Jugador atacarJugador(int jugador) {
		boolean victoria = false;
		Jugador ganador = null;
		boolean agua = false;
		int rival = (jugador + 1) % 2;
		limpiarPantalla();
		System.out.println("**** TURNO DEL JUGADOR "+jugadores[jugador].getNombre()+" ****\n\n");
		jugadores[rival].getMapaJugador().printTablero();
		do {
			Scanner scanner = new Scanner(System.in);			
			System.out.println("Elige la coordenada que deseas atacar:");
			int x = jugadores[rival].getMapaJugador().pedirCoordenada("X", scanner);
			int y = jugadores[rival].getMapaJugador().pedirCoordenada("Y", scanner);
			agua = jugadores[rival].getMapaJugador().atacarCasilla(x,y);
			victoria = jugadores[rival].getMapaJugador().comprobarVictoria();
		}while (!agua && !victoria);	
		
		
		if(!victoria) {
			System.out.println("****** ENTER para pasar de turno! ******");
			presionarEnter();
		} else {
			ganador = jugadores[jugador];
		}
		
		return ganador;
	}
	
	/**
	 * Función main que controla la funcionalidad básica del programa.
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException, InterruptedException {
		mostrarMenuInicio();		
		menuCrearJugadores();
		for (int i = 0; i < 2; i++) {
			colocarBarcosJugador(i);
			jugadores[i].getMapaJugador().ocultarBarcos();
			if (i < jugadores.length - 1) {
				System.out.println("****** ENTER para colocar los barcos del siguiente jugador. ******");
				presionarEnter();
			} else {
				System.out.println("****** ENTER para comenzar a jugar! ******");
				presionarEnter();
			}
			limpiarPantalla();
		}
		
		Jugador ganador = null;
		int jugador = 0;
		while (ganador == null) {
			ganador = atacarJugador(jugador);
			jugador = (jugador + 1) % 2;
		}
		
		System.out.println("******************************************************");
		System.out.println("**** "+ganador.getNombre()+" HA GANADO LA PARTIDA.****\n\n");
		System.out.println("******************************************************");
		
	}

}
