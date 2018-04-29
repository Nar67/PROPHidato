import java.util.Scanner;

import com.sun.org.glassfish.gmbal.ManagedAttribute;

public class ControlDomini {
	
	public void llegeixHidato() {
		Partida partida = new Partida();
		Hidato hidato = partida.llegirTaulell();
		while (!hidato.checkHidato()) {
			System.out.println("L'hidato proposat no es resoluble");
			hidato = partida.llegirTaulell();
		}
	}
	
	public void generaHidato() {
		Partida partida = new Partida();
		System.out.println("Escoja dificultad: 1(muy f�cil), 2(f�cil), 3(normal), 4(dif�cil), 5(muy dif�cil), 6(experto).");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		int diff = keyboard.nextInt();
		Hidato hidato = partida.generarTaulell(diff);
		System.out.println("Autogenerant hidato ... ");
		Hidato hidatobuit = hidato;
		while (!hidato.checkHidato()) {
			System.out.println(" ... ");
			hidato = partida.generarTaulell(diff);
			hidatobuit = hidato;
		}
		hidatobuit.getTaulell().printBoard();
	}
	
}
