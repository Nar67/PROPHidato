import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Para autogenerar un Hidato pulse: 1; para generar su propio Hidato pulse: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		ControlDomini controlador = new ControlDomini();
		while (action != 1 && action != 2) {
			System.out.println("No es una opci� v�lida, intenti-ho de nou.");
			action = keyboard.nextInt();
		}
		if (action == 1) controlador.jugar(controlador.generaHidato());
		else if (action == 2) controlador.jugar(controlador.llegeixHidato());
	}
}
