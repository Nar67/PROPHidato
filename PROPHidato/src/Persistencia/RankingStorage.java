package Persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Domini.ControlDomini;
import Domini.Partida;

public class RankingStorage {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ControlDomini cd = new ControlDomini();
		Partida part = cd.generaHidato();
		System.out.println(gson.toJson(part));
		String pene = gson.toJson(part);
		Partida partida = gson.fromJson(pene,  Partida.class);
		partida.getHidato().printHidato();
	}
}
