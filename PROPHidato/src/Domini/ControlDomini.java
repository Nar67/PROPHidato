package Domini;
import java.io.IOException;
import java.util.Scanner;
import Persistencia.ControlPersistencia;



public class ControlDomini {
	private Partida currentpartida;
	private Usuari currentuser;
	private static ControlDomini cd = new ControlDomini();
	private ControlDomini() {}
	public static ControlDomini getInstance() {
		return cd;
	}
	
	public String getCurrentUsername() {
		return ControlDomini.getInstance().currentuser.getNom();
	}
	
	public Integer getBoardNumerator() {
		return ControlDomini.getInstance().getBoardNumerator();
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
		if (action == 1) this.currentpartida = generaHidato();
		else if (action == 2) this.currentpartida = llegeixHidato();
		this.currentpartida.startPlaying();
	}
	
	public void savePartida() throws IOException {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		cp.savePartida(this.currentpartida.partidaToString(), this.currentpartida.getUser().getNom(), this.currentpartida.getUser().getPartidaID());
		this.currentpartida.getUser().setPartidaID(1 + this.currentpartida.getUser().getPartidaID());
	}

	protected void storeBoard(String board) throws IOException {
		ControlPersistencia cpers = ControlPersistencia.getInstance();
		cpers.storeBoard(board);
		ControlDomini cd = ControlDomini.getInstance();
		cd.currentuser.setBoardNumerator(cd.getBoardNumerator() + 1) ;
	}
	
	protected Board loadBoard() {
		ControlPersistencia cpers = ControlPersistencia.getInstance();
		
	}
	
}
