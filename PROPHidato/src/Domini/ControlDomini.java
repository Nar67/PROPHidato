package Domini;
import java.util.Scanner;

import Persistencia.ControlPersistencia;



public class ControlDomini {
	
	private static ControlDomini cd = new ControlDomini();
	private ControlDomini() {}
	public static ControlDomini getInstance() {
		return cd;
	}
	
	public Partida llegeixHidato() {
		Partida partida = new Partida();
		Hidato hidato = partida.llegirTaulell();
		while (!hidato.checkHidato()) {
			System.out.println("L'hidato proposat no es resoluble");
			hidato = partida.llegirTaulell();
		}
		partida.setHidato(hidato);
		hidato.printHidatoOriginal();
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
	
	public void jugar(Integer action) {
		if (action == 1) generaHidato().startPlaying();
		else if (action == 2) llegeixHidato().startPlaying();
	}
	
	public void savePartida() {
		ControlPersistencia cp = new ControlPersistencia();
		cp.savePartida(partida);
	}
	
	protected static void storeBoard(String board, Integer boardID) {
		ControlPersistencia cp = ControlPersistencia.getInstance();
	}
	
}
