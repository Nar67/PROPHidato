package Tests;
import java.util.Scanner;
import java.util.PrimitiveIterator.OfDouble;

import Domini.Board;
import Domini.Hidato;
import Domini.Partida;
import Domini.SquareBoard;


public class PartidaTest {
	
	private static Partida generatepartidaAmbTaulell(){
		String[][] matriu = new String[5][5];
		int count = 0;
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				matriu[i][j] = Integer.toString(count); 
				++count;
			}
		}
		String[] params = {"Q", "CA", "5", "5"};
		Board tauler = new SquareBoard(params, matriu);
		Hidato hidato = new Hidato(tauler);
		Partida partida = new Partida(hidato);
		return partida;
	}
	
	public static void testGetHidato() {
		Partida partida = generatepartidaAmbTaulell();
		partida.getHidato().printHidato();		
	}
	
	public static void testSetHidato() {
		String[][] matriu = new String[5][5];
		int count = 0;
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				matriu[i][j] = Integer.toString(count); 
				++count;
			}
		}
		String[] params = {"Q", "CA", "5", "5"};
		Board tauler = new SquareBoard(params, matriu);
		Hidato hidato = new Hidato(tauler);
		Partida partida = new Partida();
		partida.setHidato(hidato);
		partida.getHidato().printHidato();
	}
	
	public static void testmoveInBoard() {
		Partida partida = generatepartidaAmbTaulell();
		partida.getHidato().printHidato();
		System.out.println("Introdueixi posicio (i,j) a provar");
		Scanner keyboard = new Scanner(System.in);
		int i = keyboard.nextInt();
		int j = keyboard.nextInt();
		if (!partida.moveInBoard(i, j)) System.out.println("El moviment no es valid");
		else System.out.println("El moviment es valid");
	}

	public static void testLlegirTaulell() {
		System.out.println("Hidato introduit");
		Partida partida = new Partida();
		//Hidato hidato = partida.llegirTaulell();
		//partida.setHidato(hidato);
		//partida.getHidato().getTaulell().printBoard();
	}
	
	public static void testGenerarTaulell() {
		Partida partida = new Partida();
		//Hidato hidato = partida.generarTaulell(4);
		//hidato.getTaulell().printBoard();
	}
	
	public static void testSetUltim() {
		System.out.println("Introdueixi el numero a posar en la variable 'ultim'");
		Partida partida = new Partida();
		Scanner keyboard = new Scanner(System.in);
		int num = keyboard.nextInt();
		partida.setUltim(num);
		System.out.print(partida.getUltim());
	}
	
	public static void testGetUltim() {
		Partida partida = new Partida();
		partida.setUltim(45);
		System.out.print(partida.getUltim());
	}
	
	public static void main(String[] args) {
		System.out.println("Per a autogenerar un Hidato premi: 1; per a introduir el seu propi Hidato premi: 2; per a provar la funcio moveInBoard premi: 3.");
		System.out.println("Per a provar la funcio setHidato premi 4; Per a provar la funcio getHidato premi 5.");
		System.out.println("Per a provar la funcio setUltim premi 6; Per a provar la funcio getUltim premi 7.");
		
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (true) {
			if (action == 1) testGenerarTaulell();
			else if (action == 2) testLlegirTaulell();
			else if (action == 3) testmoveInBoard();
			else if (action == 4) testSetHidato();
			else if (action == 5) testGetHidato();
			else if (action == 6) testSetUltim();
			else if (action == 7) testGetUltim();
			else System.out.println("No es una opció vàlida, intenti-ho de nou.");
			System.out.println();
			action = keyboard.nextInt();
		}
		
	}
	
}
