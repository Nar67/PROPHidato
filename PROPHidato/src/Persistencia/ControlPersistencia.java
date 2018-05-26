package Persistencia;

import java.io.IOException;
import java.util.ArrayList;


public class ControlPersistencia {

	
	private static ControlPersistencia cp = new ControlPersistencia();
	private ControlPersistencia() {}
	public static ControlPersistencia getInstance() {
		return cp;
	}
	
	
	public ArrayList<String> getPartides(String user) {
		PartidaStorage ps = PartidaStorage.getInstance();
		return ps.getPartides(user); 
	}
	
	public String loadPartida(String user, Integer ID) throws IOException {
		PartidaStorage ps = PartidaStorage.getInstance();
		return ps.loadPartida(user, ID);
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
