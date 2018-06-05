package Persistencia;

import java.io.IOException;
import java.util.ArrayList;


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
	public void storeBoard(String board, String name) throws IOException {
		BoardStorage bs = BoardStorage.getInstance();
		bs.storeBoard(board, name);
	}
	
	public boolean logInUser(String username, String password) throws IOException {
		return UserStorage.getInstance().logInUser(username, password);
	}
	
	
	public boolean signUpUser(String username, String password) throws IOException {
		return UserStorage.getInstance().signUpUser(username, password);
	}
	
	public String[] listHidatos() {
		return HidatoStorage.getInstance().listHidatos();
	}
	
	public String[] listBoards() {
		return BoardStorage.getInstance().listBoards();
	}
	
	public String[] listGames(String user) {
		return PartidaStorage.getInstance().getPartides(user).toArray(new String[0]);
	}
	
	public String loadHidato(String name) throws IOException {
		return HidatoStorage.getInstance().loadHidato(name);
	}
	
	public void storeHidato(String hidato, String name) throws IOException {
		HidatoStorage.getInstance().storeHidato(hidato, name);
	}
	
	public String loadBoard(String name) throws IOException {
		return BoardStorage.getInstance().loadBoard(name);
	}
	
	public String getEasyRanking() throws IOException {
		return RankingStorage.getInstance().getEasyRanking();
	}
	
	public String getMediumRanking() throws IOException{
		return RankingStorage.getInstance().getMediumRanking();
	}

	public String getHardRanking() throws IOException{
		return RankingStorage.getInstance().getHardRanking();
	}
	
	public void storeEasyRanking(String rts) throws IOException {//TODO ranking
		RankingStorage.getInstance().storeEasyRanking(rts);
	}
	
	public void storeMediumRanking(String rts) throws IOException {//TODO ranking
		RankingStorage.getInstance().storeMediumRanking(rts);
	}
	
	public void storeHardRanking(String rts) throws IOException {//TODO ranking
		RankingStorage.getInstance().storeHardRanking(rts);
	}
}
