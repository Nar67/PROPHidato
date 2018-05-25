package Presentacio;
import java.util.Scanner;
import Domini.ControlDomini;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Per a autogenerar un Hidato premi: 1; per a introduir el seu propi Hidato premi: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		ControlDomini controlador = new ControlDomini();
		while (action != 1 && action != 2) {
			System.out.println("No es una opció vàlida, intenti-ho de nou.");
			action = keyboard.nextInt();
		}
		if (action == 1) controlador.jugar(controlador.generaHidato());
		else if (action == 2) controlador.jugar(controlador.llegeixHidato());
		keyboard.close();
	}
}

