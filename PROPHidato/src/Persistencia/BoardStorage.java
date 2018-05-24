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
		//GsonBuilder builder = new GsonBuilder();
		//builder.registerTypeAdapter(SquareBoard.class, new InterfaceAdapter())
		//.registerTypeAdapterFactory(BoardAdapterFactory);
		//Gson gson2 = builder.create();
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory)
				//.registerTypeAdapter(Partida.class, new InterfaceAdapter())
				//.registerTypeAdapter(Hidato.class, new InterfaceAdapter())
				//.registerTypeAdapter(Cell.class, new InterfaceAdapter())
				.create();
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
