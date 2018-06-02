package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
		ArrayList<String> lhidatos = new ArrayList<>();
		String path = System.getProperty("user.dir");		
		File f = new File(path + File.separator + "Hidatos");
		for (File fileEntry : f.listFiles()) {	 
			String aux = fileEntry.getName().substring(0, fileEntry.getName().length()-4);
            lhidatos.add(aux);
		}
		String[] example = new String[lhidatos.size()];
		example = lhidatos.toArray(example);
		return example;
	}
	
	public void storeHidato(String hidato, String name) throws IOException {
		//TODO s'ha de guardar l'hidato al directori "Hidatos" amb el format "hidato" + name + ".txt" o alguna cosa similar. si va millor sense el "hidato" per fer el llistat, es pot fer
		String path = System.getProperty("user.dir");	
		path = path + File.separator + "Hidatos";
		File directory= new File(path);
		if(! directory.exists()) {
			directory.mkdir();
		}
		File f = new File(path + File.separator + name + ".txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		writer.write(hidato);
		writer.close();
	}
	
	public String loadHidato(String name) throws IOException {
		//s'ha de poder carregar un hidato pel nom.
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Hidatos" + File.separator + name + ".txt"));
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return pt1;
	}

}
