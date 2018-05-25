package Persistencia;

import java.io.IOException;

import Domini.ControlDomini;

public class ControlPersistencia {

	
	private static ControlPersistencia cp = new ControlPersistencia();
	private ControlPersistencia() {}
	public static ControlPersistencia getInstance() {
		return cp;
	}
	
	public String getCurrentUsername() {
		ControlDomini cd = ControlDomini.getInstance();
		return cd.getCurrentUsername();
	}
	
	
	
	public void savePartida(String pts, String user, Integer ID) throws IOException {
		PartidaStorage ps = PartidaStorage.getInstance();
		ps.savePartida(pts, user, ID);
	}
	public void storeBoard(String board, String boardID) throws IOException {
		BoardStorage bs = BoardStorage.getInstance();
		bs.storeBoard(board, boardID);
	}
}
