/**
 * 
 */
package game;
import java.util.Scanner;

/**
 * Esta clase se encarga de gestionar la información relacionada con los mapas de los jugadores.
 * @author Alex, Marc
 * @version 18/10/2017
 */
public class Mapa {
	/****** ATRIBUTOS ******
	/**
	 * Número máximos de barcos que puede contener un mapa.
	 */
	private int maxBarcos = 7;
	
	/**
	 * Número de casillas máximo por cada fila y/o columna del tablero.
	 */
	public static final int NUM_CASILLAS = 10;
	
	/**
	 * Tamaños de los barcos del mapa.
	 */
	private int[] tamañoBarcos = {2, 2, 3, 3, 4, 4, 5};
	
	/**
	 * Número máximo de casillas que puede contener un mapa.
	 */
	private int numCasillas;
	/**
	 * Matriz que contiene las casillas del tablero.
	 */
	private Casilla tablero[][];
	/**
	 * Lista de los barcos que se situarán en el mapa. 
	 */
	private Barco barcos[] = new Barco[maxBarcos];
	/**
	 * String con la cabecera y el nombre del jugador 
	 */
	private String cabeceraTablero;
	
	
	/****** FUNCIONES ******
	/**
	 * Función que inicializa los atributos de la clase.
	 * @param maxCasillas: número máximo de casillas que puede contener una columna.
	 */
	public Mapa() {
		this.numCasillas = NUM_CASILLAS * NUM_CASILLAS;
		tablero = new Casilla[this.numCasillas][this.numCasillas];
	}
	
	/**
	 * Función que devuelve el estado de una casilla del tablero.
	 * @param x: coordenada X de la casilla.
	 * @param y: coordenada Y de la casilla.
	 * @return Estado de la casilla.
	 */
	public int getEstadoCasilla(int x, int y) {
		return this.tablero[y][x].getEstado();
	}
	
	/**
	 * Función que devuelve el tablero de juego.
	 * @return tablero: mapa donde se encuentras las casillas del juego.
	 */
	public Casilla[][] getTablero() {
		return this.tablero;
	}
	
	/**
	 * Función que devuelve el número máximo de barcos que puede haber en el mapa.
	 * @return maxBarcos: número máximo de barcos que puede haber en el mapa.
	 */
	public int getMaxBarcos(){
		return this.maxBarcos;
	}
	
	/**
	 * Función que permite modificar el número máximo de barcos que puede haber en el mapa.
	 * @param maxBarcos: nuevo número máximo de barcos permitido.
	 */
	public void setMaxBarcos(int maxBarcos){
		this.maxBarcos = maxBarcos;
	}
	
	/**
	 * Función que devuelve una lista con los tamaños de los barcos del mapa.
	 * @return tamañoBarcos: lista con los tamaños de los barcos del mapa.
	 */
	public int[] getTamañoBarcos(){
		return this.tamañoBarcos;
	}
	
	/**
	 * Función que permite cambiar el tamaño de los barcos del mapa.
	 * @param arrayBarcos: array con los tamaños de los barcos del mapa.
	 */
	public void setTamañoBarcos(int[] arrayBarcos){
		this.tamañoBarcos = arrayBarcos;
	}
	
	/**
	 * Función que borra los barcos que se encuentran en el mapa.
	 */
	public void restartBarcos(){
		this.barcos = new Barco[this.maxBarcos];
	}
	
	/**
	 * Función que asigna una cabecera al mapa.
	 * @param cabecera: nueva cabecera del mapa.
	 */
	public void setCabeceraTablero(String cabecera) {
		this.cabeceraTablero = cabecera;
	}
	
	/**
	 * Función que dibujará y incializará los valores a una matriz.
	 */
	public void inicializarTablero() {
		this.tablero = new Casilla[NUM_CASILLAS][NUM_CASILLAS];
		for (int i = 0; i < NUM_CASILLAS; i++) { //y
			for (int j = 0; j < NUM_CASILLAS; j++) { //x
				this.tablero[i][j] = new Casilla(i,j,0);
			}
		}
	}

	/** 
	 * Función que creará una cabecera para el tablero, adaptable si cambiamos el número máximo de casillas.
	 * @return cabecera: cabecera del tablero
	 */	
	public String crearCabeceraTablero() {
		String cabecera = "";
		
		for (int i = 0; i < NUM_CASILLAS; i++) {
			if (i == 0) {
				cabecera += "    " + i;
			} else {
				cabecera += " " + i;
			}
		}
		return cabecera;
	}
	
	/** 
	 * Función que dibujará un mapa.
	 */
	public void printTablero() {
		System.out.print("\n");
		for (int i = 0; i < NUM_CASILLAS; i++) {
			if (i == 0) {
				System.out.println(this.cabeceraTablero);
			}
			System.out.print("  " + i + " ");
			for (int j = 0;j < NUM_CASILLAS; j++) {			
				System.out.print(this.tablero[i][j].getImagen()+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	/** 
	 * Función que permite saber si las coordenadas dadas corresponden a una casilla del mapa.
	 * @param x: coordenada del eje X.
	 * @param y: coordenada del eje Y.
	 * @return true: la casilla existe
	 * 		   false: la casilla no existe (fuera de rango)
	 */
	public boolean casillaExistente(int x, int y) {
		if (x < 0 || x > (NUM_CASILLAS - 1)) {
			return false;
		} else if (y < 0 || y > (NUM_CASILLAS - 1)) {
			return false;
		} else {
			return true;
		}		
	}
	
	/**
	 * Función que permite cambiar el estado de una casilla del mapa.
	 * @param estado: valor del nuevo estado para esa casilla.
	 * @param x: coordenada x del mapa.
	 * @param y: coordenada y del mapa.
	 * @return 0: el estado es válido.
	 * 		   -1: el estado no es válido.
	 */	
	public int cambiarEstado(int estado, int x, int y){
		if (!casillaExistente(x, y)) {
			return -1;
		}
		
		if (estado >= 0 && estado < 4) {
			this.tablero[y][x].setEstado(estado);
			
			switch(estado){
				case 0:
					this.tablero[y][x].setImagen("-");
					return 0;
				case 1:
					this.tablero[y][x].setImagen("*");
					return 0;
				case 2:
					this.tablero[y][x].setImagen("~");
					return 0;
				case 3:
					this.tablero[y][x].setImagen("x");
					return 0;
				default:
					return -1;
			}
		} else {
			return -1;
		}
	}
	
	/**
	 * Función que pide al usuario una coordenada del mapa.
	 * @param nombreCoordenada: nombre de la coordenada (X o Y).
	 * @param scanner: Scanner que permite leer lo que introduce el usuario por teclado.
	 * @return coordenada: valor de la coordenada.
	 */
	public int pedirCoordenada(String nombreCoordenada, Scanner scanner) {
		System.out.print("Coordenada " + nombreCoordenada + ": ");
		int coordenada = 0;
		boolean typeCorrect = false;
		  do {
		   try {
			   Scanner scan = new Scanner(System.in);
		        coordenada = scan.nextInt();
		        while (!casillaExistente(coordenada, 0)) {
					System.out.print("Coordenada " + nombreCoordenada + " fuera del mapa. Vuelve a introducirla: ");
					coordenada = scanner.nextInt();
				}
		        typeCorrect = true;
		  } catch (Exception e) {
		       System.out.println ("Porfavor introduce un numero:");
		  }
		  }while(!typeCorrect);
		return coordenada;
	}
	
	/**
	 * Función que comprueba si una casilla está libre o no (contiene la parte de un barco).
	 * @param x: coordenada X.
	 * @param y: coordenada Y.
	 * @return true: está libre.
	 * 		   false: está ocupada.
	 */
	public boolean casillaLibre(int x, int y) {
		if (casillaExistente(x, y)) {
			if (this.tablero[y][x].getEstado() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Función que comprueba el estado de una casilla tocada y actúa según este. 
	 * @param x: coordenada X de la casilla tocada.
	 * @param y: coordenada Y de la casilla tocada.
	 * @return true: la casilla que se ha disparado es agua.
	 * 		   false: la casilla que se ha disparado no es agua. 
	 */
	public boolean comprobarEstadoCasilla(int x, int y) {
		Juego.limpiarPantalla();
		
		if (casillaExistente(x, y)) {
			int estado = getEstadoCasilla(x, y);
			switch(estado) {
			case 0:		// agua
				cambiarEstado(2, x, y);
				System.out.println("");
				System.out.println("¡Agua!");
				System.out.println("");
				return true;
			case 1:		// barco
				cambiarEstado(3, x, y);
				System.out.println("");
				System.out.println("¡Has tocado un barco!");
				System.out.println("");
				return false;
				
			case 2:
				System.out.println("");
				System.out.println("Ya se ha disparado en esta casilla. Apunta a otra casilla:");
				System.out.println("");
				return false;
			case 3:
				System.out.println("");
				System.out.println("Esa casilla ya ha sido hundida. Apunta a otra casilla:");
				System.out.println("");
				return false;
			default:
				System.out.println("");
				System.out.println("Casilla no valida. Apunta a otra casilla:");
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Función que determina si una casilla ya ha sido disparada o no.
	 * @param x: coordenada X de la casilla a verificar.
	 * @param y: coordenada Y de la casilla a verificar.
	 * @return true: la casilla ya ha sido disparada.
	 * 		   false: la casilla no ha sido disparada.
	 */
	public boolean casillaYaDisparada(int x, int y) {
		if (casillaExistente(x, y)) {
			if ((this.tablero[y][x].getEstado() == 0) || (this.tablero[y][x].getEstado() == 1)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Función que comprueba si un conjunto de casillas está libre o no.
	 * @param b: barco que se quiere colocar.
	 * @param x: coordenada X.
	 * @param y: coordenada Y.
	 * @return true: está libre.
	 * 		   false: está ocupada.
	 */
	public boolean casillasLibres(Barco b, int x, int y) {
		for (int i = 0; i < b.getTamaño(); i++) {
			if (b.getOrientacion()) {
				if(!casillaLibre(x, y + i)) {
					return false;
				}
			} else {
				if (!casillaLibre(x + i, y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Fución que genera los barcos del mapa y los coloca en este.
	 * @param x: coordenada X inicial del barco.
	 * @param y: coordenada Y inicial del barco.
	 * @param orientacionChar: orientación del barco.
	 * @param b: barco a colocar.
	 * @param posicion: posicion del barco en la array de Barcos del mapa.
	 * @return colocadoCorrecto: true si se ha podido colocar el barco, false si no.
	 */
	public boolean colocarBarcos(int x, int y, char orientacionChar, Barco b, int posicion) {
		boolean colocadoCorrecto = b.colocarBarco(this, x, y, orientacionChar);
		this.barcos[posicion] = b;
		Juego.limpiarPantalla();
		return colocadoCorrecto;
	}
	
	/**
	 * Función que oculta la imagen de los barcos para el tablero. 
	 */
	public void ocultarBarcos() {
		for (int i = 0; i < maxBarcos; i++) {
			Casilla[] casillas = barcos[i].getCasillasBarco();
			for (int j = 0; j < casillas.length; j++) {
				this.tablero[casillas[j].getCoordenadaY()][casillas[j].getCoordenadaX()].setImagen("-");
			}			
		}
	}
	
	/**
	 * Función que comprueba si el barco que se ha tocado está hundido o no.
	 * @param x: coordenada X de la posición del barco tocada.
	 * @param y: coordenada Y de la posición del barco tocada.
	 * @return true: el barco está hundido.
	 * 		   false: el barco no está hundido.
	 */
	public boolean comprobarBarcoTocado(int x, int y){
		for (int i = 0; i < maxBarcos; i++) {
			Casilla[] casillas = barcos[i].getCasillasBarco();
				for (int j = 0; j < casillas.length; j++){
					if (x == casillas[j].getCoordenadaX() && y == casillas[j].getCoordenadaY()) {
						casillas[j].setEstado(3);
						barcos[i].setHundido(barcos[i].comprobarHundido());
						return barcos[i].comprobarHundido();
					}
				}	
		}
		return false;
	}
	
	/**
	 * Función que comprueba si el jugador en cuestión ha ganado o no la partida.
	 * @return true: ha ganado la partida.
	 * 		   false: no ha ganado la partida. 
	 */
	public boolean comprobarVictoria(){
		int barcosHundidos = 0;
		for (int i = 0; i < maxBarcos; i++) {
			if (barcos[i].getHundido()) {
				barcosHundidos++;
			}		
		}
		if (barcosHundidos == maxBarcos){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Función que gestiona la colocación de los barcos en el mapa.
	 * @param x: coordenada X que se quiere atacar.
	 * @param y: coordenada Y que se quiere atacar.
	 * @return true: se ha podido atacar la casilla en cuestión.
	 * 		   false: no se ha podido atacar la casilla.
	 */
	public boolean atacarCasilla(int x, int y) {
		try{
			boolean agua;		
			agua = comprobarEstadoCasilla(x, y);
			boolean hundido = comprobarBarcoTocado(x, y);
			if(hundido){
				System.out.println("¡El barco se ha hundido!");
			}
			printTablero();
			return agua;
		}catch(ArrayIndexOutOfBoundsException exception){
			System.out.println("Esos valores no son validos, estan fuera del mapa.");
			return false;
		}
	}
	
}
