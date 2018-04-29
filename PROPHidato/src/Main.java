import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
	private static void manualHidato() {
		System.out.println("Introduzca un hidato válido\n");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		String dades = keyboard.nextLine();
		String params[] = dades.split(",");
		String matriu[][] = new String[Integer.parseInt(params[2])][Integer.parseInt(params[3])];
		for (int i = 0; i < matriu.length; ++i) {
			String aux = keyboard.nextLine();
			String aux2[] = aux.split(",");
			for (int j = 0; j < aux2.length; ++j) {
				matriu[i][j] = aux2[j]; 
				System.out.print(matriu[i][j]);
			}				
			System.out.println();
		}
		ControlDomini controlador = new ControlDomini();
		controlador.jugaManualHidato(matriu, params);
	}
	
	private static void generarHidato() {
		System.out.println("Escoja dificultad: 1(muy fácil), 2(fácil), 3(normal), 4(difícil), 5(muy difícil), 6(experto).");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		int diff = keyboard.nextInt();
		System.out.println();
		System.out.println("Autogenerando Hidato ...");
		
		int randomi = ThreadLocalRandom.current().nextInt(3, 8);
		int randomj = ThreadLocalRandom.current().nextInt(3, 8);
		String matriu[][] = new String[randomi][randomj];
		System.out.println(randomi);
		System.out.println(randomj);
		int randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
		int randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		matriu[randomstarti][randomstartj] = "1";
		
		int hashtags = (randomi*randomj)/3;
		for (int i = 0; i < hashtags; ++i) {
			randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
			randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
			while (matriu[randomstarti][randomstartj] != null) {
				randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
				randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
			}
			matriu[randomstarti][randomstartj]= "#"; 
		}
		
		randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
		randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		while (matriu[randomstarti][randomstartj] != null) {
			randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
			randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		}
		matriu[randomstarti][randomstartj] = Integer.toString((randomi*randomj)-hashtags);

		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				if ((i != randomstarti || j != randomstartj) && matriu[i][j] == null) matriu[i][j] = "?";					
			}
		}
		
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				System.out.print(matriu[i][j]);
				System.out.print(",");
			}
			System.out.println();
		}
		ControlDomini controlador = new ControlDomini();
		controlador.jugaAutoHidato(matriu, diff, randomi, randomj);
	}
	
	public static void main(String[] args) {
		System.out.println("Para autogenerar un Hidato pulse: 1; para generar su propio Hidato pulse: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		if (action == 1) generarHidato();
		else if (action == 2) manualHidato();
	}
}
