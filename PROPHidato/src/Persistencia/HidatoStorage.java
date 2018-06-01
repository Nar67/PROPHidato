package Persistencia;

public class HidatoStorage {
	
	
	private static HidatoStorage hs = new HidatoStorage();
	private HidatoStorage() {}
	public static HidatoStorage getInstance() {
		return hs;
	}
	
	public String[] listHidatos() {
		//TODO ha de retornar un array de String (String[]) amb el nom de tots el hidatos
		//hidato ha de tindre un atribut nom per a poder llistarlos
		//el nom es tria al guardar l'hidato, pot ser null fins que no es guardi
		
		String[] example = new String[4];
		for(int i = 0; i < example.length; i++) {
			example[i] = String.valueOf(i);
		}
		return example;
	}
	
	public void storeHidato(String hidato, String name) {
		//TODO s'ha de guardar l'hidato al directori "Hidatos" amb el format "hidato" + name + ".txt" o alguna cosa similar. si va millor sense el "hidato" per fer el llistat, es pot fer
	}
	
	public String loadHidato(String name) {
		//s'ha de poder carregar un hidato pel nom.
	}

}
