package Domini;
import java.util.Scanner;



public class ControlDomini {
	
	public Partida llegeixHidato() {
		Partida partida = new Partida();
		Hidato hidato = partida.llegirTaulell();
		while (!hidato.checkHidato()) {
			System.out.println("L'hidato proposat no es resoluble");
			hidato = partida.llegirTaulell();
		}
		partida.setHidato(hidato);
		hidato.printHidatoOriginal();
		//hidato.printHidato();
		return partida;
	}
	
	public Partida generaHidato() {
		Partida partida = new Partida();
		System.out.println("Escoja dificultad: 1(Q,C), 2(Q,CA), 3(H,C), 4(H,CA), 5(T,C), 6(T,CA).");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		int diff = keyboard.nextInt();
		Hidato hidato = partida.generarTaulell(diff);
		System.out.println("Autogenerant hidato ... ");
		Hidato hidatobuit = new Hidato(hidato.getTaulell());
		while (!hidato.checkHidato()) {
			hidato = partida.generarTaulell(diff);
			hidatobuit = hidato;
		}
		partida.setHidato(hidato);
		hidatobuit.getTaulell().printBoard();
		return partida;
	}
	
	public void jugar(Partida partida) {
		partida.startPlaying();
	}
	
}
