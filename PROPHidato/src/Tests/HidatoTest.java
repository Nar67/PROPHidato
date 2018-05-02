package Tests;
import java.util.ArrayList;
import java.util.Scanner;

import Domini.Board;
import Domini.Hidato;
import Domini.SquareBoard;

public class HidatoTest {
	
	public static void testGetTaulell() {
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
		hidato.getTaulell().printBoard();
	}
	
	public static void testGetStart() {
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
		hidato.checkHidato();
		int start[] = hidato.getStart();
		System.out.println(start[0] + " " + start[1]);
	}
	
	public static void testCheckHidato() {
		String[][] matriu = new String[5][5];
		int count = 0;
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				matriu[i][j] = Integer.toString(count); 
				count += 12;
			}
		}
		String[] params = {"Q", "CA", "5", "5"};
		Board tauler = new SquareBoard(params, matriu);
		Hidato hidato = new Hidato(tauler);
		Boolean correcte = hidato.checkHidato();
		System.out.println("Hauria de printar incorrecte(false): " + correcte);
		
		String[][] matriuu = {{"#","#","1","#","#"},{"#","?","*","?","#"},{"8","?","?","?","3"},{"#","?","11","*","#"},{"#","#","?","#","#"}};
		String[] paramss = {"Q", "CA", "5", "5"};
		Board tauleer = new SquareBoard(paramss, matriuu);
		Hidato hidatoo = new Hidato(tauleer);
		Boolean correctee = hidatoo.checkHidato();
		System.out.println("Hauria de printar correcte(true): " + correctee);
	}
	
	public static void testResoldreHidato() {
		System.out.println("L'Hidato que li passem sabem segur que té solució");
		String[][] matriuu = {{"#","#","1","#","#"},{"#","?","*","?","#"},{"8","?","?","?","3"},{"#","?","11","*","#"},{"#","#","?","#","#"}};
		String[] paramss = {"Q", "CA", "5", "5"};
		Board tauleer = new SquareBoard(paramss, matriuu);
		Hidato hidatoo = new Hidato(tauleer);
		hidatoo.resoldreHidato();
	}
	
	public static void testPrintsdeHidato() {
		String[][] matriuu = {{"#","#","1","#","#"},{"#","?","*","?","#"},{"8","?","?","?","3"},{"#","?","11","*","#"},{"#","#","?","#","#"}};
		String[] paramss = {"Q", "CA", "5", "5"};
		Board tauleer = new SquareBoard(paramss, matriuu);
		Hidato hidatoo = new Hidato(tauleer);
		hidatoo.checkHidato();
		System.out.println("Original:");
		hidatoo.printHidatoOriginal();
		System.out.println("Solució:");
		hidatoo.printHidato();
	}
	
	public static void testIsMoveValid() {
		String[][] matriuu = {{"#","#","1","#","#"},{"#","?","*","?","#"},{"8","?","?","?","3"},{"#","?","11","*","#"},{"#","#","?","#","#"}};
		String[] paramss = {"Q", "CA", "5", "5"};
		Board tauleer = new SquareBoard(paramss, matriuu);
		Hidato hidatoo = new Hidato(tauleer);
		boolean aux = hidatoo.isMoveValid(0, 2, 1, 3);
		System.out.println(aux);
	}
	
	public static void testNextMove() {
		String[][] matriuu = {{"#","#","1","#","#"},{"#","?","*","?","#"},{"8","?","?","?","3"},{"#","?","11","*","#"},{"#","#","?","#","#"}};
		String[] paramss = {"Q", "CA", "5", "5"};
		Board tauleer = new SquareBoard(paramss, matriuu);
		Hidato hidatoo = new Hidato(tauleer);
		ArrayList<Integer> aux = hidatoo.nextMove(0, 2);
		System.out.println(aux.get(0) + " " + aux.get(1));
	}
	
	public static void main(String[] args) {
		System.out.println("Per a obtenir el taulell original d'un Hidato premi: 1; per a obtenir les posicions del nombre 1 premi: 2; per a provar si un Hidato té solució premi: 3.");
		System.out.println("Per a resoldre un Hidato 4; Per a printar un Hidato solució i l'original premi 5.");
		System.out.println("Per a provar si el seguent moviment d'un Hidato és vàlid premi 6; Per a provar si la ajuda per el següent moviment funciona premi 7.");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (true) {
			if (action == 1) testGetTaulell();
			else if (action == 2) testGetStart();
			else if (action == 3) testCheckHidato();
			else if (action == 4) testResoldreHidato();
			else if (action == 5) testPrintsdeHidato();
			else if (action == 6) testIsMoveValid();
			else if (action == 7) testNextMove();
			else System.out.println("No es una opció vàlida, intenti-ho de nou.");
			System.out.println();
			action = keyboard.nextInt();
		}
	}
}
