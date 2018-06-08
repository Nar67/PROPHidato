package Tests;

import java.util.Scanner;

import Domini.ControlDomini;
import Domini.Partida;

public class ControlDominiTest {

	public static void testLlegeixHidato() {
		ControlDomini controlador = ControlDomini.getInstance();
		//Partida partida = controlador.llegeixHidato();
	}
	
	public static void testGeneraHidato() {
		ControlDomini controlador = ControlDomini.getInstance();
		//Partida partida = controlador.generaHidato();
	}
	
	public static void main(String[] args) {
		System.out.println("Per a provar la funcio llegeixHidato premi: 1; per a provar la funcio generaHidato premi: 2.");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (true) {
			if (action == 1) testLlegeixHidato();
			else if (action == 2) testGeneraHidato();
			else System.out.println("No es una opció vàlida, intenti-ho de nou.");
			System.out.println();
			action = keyboard.nextInt();
		}
	}
	
}
