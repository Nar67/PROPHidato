package Presentacio;

import java.io.IOException;
import java.util.ArrayList;
import java.security.MessageDigest;

import Domini.ControlDomini;
import Persistencia.ControlPersistencia;

public class ControlPresentacio {

	private static ControlPresentacio cp = new ControlPresentacio();
	private ControlPresentacio() {}
	public static ControlPresentacio getInstance() {
		return cp;
	}
	
	public void jugar(Integer action) {
		ControlDomini controlador = ControlDomini.getInstance();
		controlador.jugar(action);
 	}
	
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
	
	public boolean logInUser(String username, char[] password) {
		return ControlDomini.getInstance().logInUser(username, ControlDomini.getInstance().getSHA256Hash(String.valueOf(password)));
	}
	
	public boolean signUpUser(String username, char[] password) {
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
	
	public void getEasyRanking(ArrayList<String> users, ArrayList<String> scores) {
		ControlDomini.getInstance().getEasyRanking(users, scores);
	}
	
	public void getMediumRanking(ArrayList<String> users, ArrayList<String> scores) {
		ControlDomini.getInstance().getMediumRanking(users, scores);
	}
	
	public void getHardRanking(ArrayList<String> users, ArrayList<String> scores) {
		ControlDomini.getInstance().getHardRanking(users, scores);
	}
	
	public void loadHidato(String name, String[] params, String[][] matriu) {
		ControlDomini.getInstance().loadHidato(name, params, matriu);
	}
}
