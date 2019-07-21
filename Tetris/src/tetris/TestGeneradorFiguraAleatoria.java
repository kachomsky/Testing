package tetris;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestGeneradorFiguraAleatoria {

	@Test
	public void testNovaFigura() {
		GeneradorFiguraAleatoria generador = new GeneradorFiguraAleatoria();
		
		for (int i = 0; i < 40; i++) {
			boolean[] figuresEscollides = new boolean[7];
			Arrays.fill(figuresEscollides, false);
			
			for (int index = 0; index < 7; index++) {
				figuresEscollides[generador.novaFigura()] = true;
			}
			
			for (int index = 0; index < 7; index++) {
				assertTrue(figuresEscollides[index]);
			}
		}

	}

}
