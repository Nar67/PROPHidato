package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Domini.Board;
import Domini.ControlDomini;
import Domini.HexagonBoard;
import Domini.Partida;
import Domini.SquareBoard;
import Domini.TriangleBoard;

public class PartidaStorage {
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
		
		String path = System.getProperty("user.dir");	
		System.out.println(path);
		File f = new File(path + File.separator + "hitler.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter bts = new PrintWriter(f);
		bts.write(p);
		bts.close();
		
			
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "hitler.txt"));
		
		String pene = new String(btl, Charset.forName("UTF-8"));
		Partida partida2 = gson.fromJson(pene, Partida.class);
		partida2.startPlaying();
	}
}
