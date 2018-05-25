package Presentacio;

import java.io.IOException;

import Domini.ControlDomini;

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
	
	public void savePartida() throws IOException {
		ControlDomini controlDomini = ControlDomini.getInstance();
		controlDomini.savePartida();
	}
}
