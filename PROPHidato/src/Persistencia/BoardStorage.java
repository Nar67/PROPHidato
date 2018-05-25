package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Domini.Board;
import Domini.ControlDomini;
import Domini.HexagonBoard;
import Domini.Partida;
import Domini.SquareBoard;
import Domini.TriangleBoard;

public class BoardStorage {
	
	public void storeBoard(String board, Integer boardID) {
		String path = System.getProperty("user.dir");	
		path = path + File.separator + "Boards";
		File f = new File(path + File.separator + "board" + boardID + ".txt");
		f.getParentFile().mkdirs();
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(board);
		writer.close();
		
	}
	public static void main(String[] args) throws IOException {
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		ControlDomini cDomini = new ControlDomini();
		Partida partida = cDomini.generaHidato();
		String p = gson.toJson(partida);
		
		
			
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "hitler.txt"));
		
		String pene = new String(btl, Charset.forName("UTF-8"));
		Partida partida2 = gson.fromJson(pene, Partida.class);
		partida2.startPlaying();
	}
}
