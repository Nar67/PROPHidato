package Persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Domini.Board;
import Domini.ControlDomini;
import Domini.Partida;
import Domini.SquareBoard;

public class BoardStorage {
	public static void main(String[] args) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(SquareBoard.class, new InterfaceAdapter());
		Gson gson = builder.create();
		ControlDomini cDomini = new ControlDomini();
		Partida partida = cDomini.generaHidato();
		String p = gson.toJson(partida);
		Partida part = gson.fromJson(p, Partida.class);
		Board b = part.getHidato().getTaulell();
		System.out.println("hitler");
		System.out.println(b.getParams());
	}
}
