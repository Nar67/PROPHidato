package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;



public class PartidaStorage {
	
	private static PartidaStorage ps = new PartidaStorage();
	private PartidaStorage() {}
	public static PartidaStorage getInstance() {
		return ps;
	}
	
	
	public void savePartida(String pts, String user, Integer ID) throws IOException {
		String path = System.getProperty("user.dir");
		File f = new File(path + File.separator + "Users" + File.separator + user + File.separator + "Partida_" + user + "_" + ID.toString() + ".txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter bts = new PrintWriter(f);
		bts.write(pts);
		bts.close();		
		/*	
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "hitler.txt"));
		
		String pene = new String(btl, Charset.forName("UTF-8"));
		
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();*/
	}
}
