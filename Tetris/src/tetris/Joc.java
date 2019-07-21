package tetris;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Joc extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static boolean esPotMoure;
	private static Figura figura;
	private static Tauler tauler = new Tauler();
	private static GeneradorFiguraAleatoria generador = new GeneradorFiguraAleatoria();
	private static int puntuacio = 0;
	private static boolean perdut = false;
	
	public static void main(String args[]) {
		JFrame marc = new JFrame("Tetris");
		marc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marc.setSize(326, 680);
		marc.setVisible(true);
		
		final Joc tetris = new Joc();
		marc.add(tetris);
		
		figura = new Figura(generador.novaFigura(), new Posicio(5, 0));
		
		Thread thread = new Thread(new Runnable() {
			@Override public void run() {
				while (true) {
					try {
						while (!perdut) {
							Thread.sleep(1000);
							esPotMoure = figura.caure(tauler);
							if (!esPotMoure) {
								tauler.encaixarFigura(figura);
								puntuacio += tauler.comprovarFilesCompletades();
								tetris.repaint();
								figura = new Figura(generador.novaFigura(), new Posicio(5, 0));
								if (tauler.solapa(figura, 0, 0)) {									
									perdut = true;
								}
							}
							tetris.repaint();
						}
					} catch (InterruptedException e) {}
				}
			}
		});
		
		marc.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					figura.rotar(tauler);
					tetris.repaint();
					break;
					
				case KeyEvent.VK_DOWN:
					esPotMoure = figura.caure(tauler);
					if (!esPotMoure) {
						tauler.encaixarFigura(figura);
						puntuacio += tauler.comprovarFilesCompletades();
						tetris.repaint();
						figura = new Figura(generador.novaFigura(), new Posicio(5, 0));
						if (tauler.solapa(figura, 0, 0)) {
							System.out.println("tecla");
							perdut = true;
						}
					}
					tetris.repaint();
					break;
					
				case KeyEvent.VK_LEFT:
					figura.moure(-1, tauler);
					tetris.repaint();
					break;
				
				case KeyEvent.VK_RIGHT:
					figura.moure(1, tauler);
					tetris.repaint();
					break;
				}
			}
			
			public void keyReleased(KeyEvent e) {
			}
		});
		
		thread.start();
		
		while (thread.isAlive() && !perdut) {
			
		}
		
		if (perdut) {
			thread.interrupt();
			marc.dispose();
			System.exit(0);
		}
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		graphics.fillRect(0, 0, 26*12, 26*23);
		tauler.pintarTauler(graphics);
		figura.pintarFigura(graphics);
		tauler.pintarPuntuacio(graphics, puntuacio);
	}

}
