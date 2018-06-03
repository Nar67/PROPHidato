package Presentacio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.security.MessageDigest;

import Domini.ControlDomini;
import Persistencia.ControlPersistencia;
import Persistencia.RankingStorage;

public class ControlPresentacio {

	private static ControlPresentacio cp = new ControlPresentacio();
	private ControlPresentacio() {}
	public static ControlPresentacio getInstance() {
		return cp;
	}
	/*
	public void jugar(Integer action) {
		ControlDomini controlador = ControlDomini.getInstance();
		controlador.jugar(action);
 	}*/
	
	public void displayPartides(String user) {
		ControlDomini controlDomini = ControlDomini.getInstance();
		ArrayList<String> partides = controlDomini.getPartides(user);
		//aqui s'han de printar les partides per a deixar triar a l'usuari quina vol carregar
	}
	public void loadPartida(String user, Integer ID) throws IOException {
		ControlDomini controlDomini = ControlDomini.getInstance();
		controlDomini.loadPartida(user, ID);
	}
	
	public void savePartida() throws IOException {
		ControlDomini controlDomini = ControlDomini.getInstance();
		controlDomini.savePartida();
	}
	
	public boolean logInUser(String username, char[] password) throws IOException {
		return ControlDomini.getInstance().logInUser(username, ControlDomini.getInstance().getSHA256Hash(String.valueOf(password)));
	}
	
	public boolean signUpUser(String username, char[] password) throws IOException {
		return ControlDomini.getInstance().logInUser(username, ControlDomini.getInstance().getSHA256Hash(String.valueOf(password)));
	}
	
	public String[][] generateGame(String diff, String cellType, String adj) {
		return ControlDomini.getInstance().generaHidato(diff, cellType, adj);
	}
	
	public String[] listHidatos() {
		return ControlDomini.getInstance().listHidatos();
	}
	
	public String[] listGames() {
		return ControlDomini.getInstance().listGames();
	}
	
	public HashMap<String, Integer> getEasyRanking() throws IOException {
		return ControlDomini.getInstance().getEasyRanking();
	}
	
	public HashMap<String, Integer> getMediumRanking() throws IOException {
		return ControlDomini.getInstance().getMediumRanking();
	}
	
	public HashMap<String, Integer> getHardRanking() throws IOException {
		return ControlDomini.getInstance().getHardRanking();
	}
	
	public void storeEasyRanking(String rts) throws IOException {//TODO ranking
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void storeMediumRanking(String rts) throws IOException {//TODO ranking
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void storeHardRanking(String rts) throws IOException {//TODO ranking
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void loadHidato(String name, String[] params, String[][] matriu) throws IOException {
		ControlDomini.getInstance().loadHidato(name, params, matriu);
	}
	
	public Integer nextMove(Integer i, Integer j) throws IOException {
		return ControlDomini.getInstance().nextMove(i, j);
	}
}
