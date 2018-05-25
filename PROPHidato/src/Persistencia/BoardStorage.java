package Persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import Domini.Board;
import Domini.ControlDomini;
import Domini.HexagonBoard;
import Domini.Partida;
import Domini.SquareBoard;
import Domini.TriangleBoard;

public class BoardStorage {
	public static void main(String[] args) {
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		ControlDomini cDomini = new ControlDomini();
		Partida partida = cDomini.generaHidato();
		String p = gson.toJson(partida);
		Partida part = gson.fromJson(p, Partida.class);
		part.getHidato().printHidato();
		Board b = part.getHidato().getTaulell();
		System.out.println("Board: ");
		b.printBoard();
	}
}
