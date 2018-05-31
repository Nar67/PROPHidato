package Presentacio;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Domini.ControlDomini;
import Persistencia.ControlPersistencia;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Per a autogenerar un Hidato premi: 1; per a introduir el seu propi Hidato premi: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (action != 1 && action != 2) {
			System.out.println("No es una opció vàlida, intenti-ho de nou.");
			action = keyboard.nextInt();
		}
		
		ControlPresentacio controlador = ControlPresentacio.getInstance();
		controlador.jugar(action);
		controlador.savePartida();
		//controlador.displayPartides(user);
		//controlador.loadPartida(user, ID);
		keyboard.close();
	}
}

