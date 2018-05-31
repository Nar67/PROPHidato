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
}
