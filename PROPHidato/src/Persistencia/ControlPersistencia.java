package Persistencia;

import java.io.IOException;

public class ControlPersistencia {

	
	private static ControlPersistencia cp = new ControlPersistencia();
	private ControlPersistencia() {}
	public static ControlPersistencia getInstance() {
		return cp;
	}
	
	public void storeBoard(String board, String boardID) throws IOException {
		BoardStorage bs = BoardStorage.getInstance();
		bs.storeBoard(board, boardID);
	}
}
