package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BoardStorage {
	
	private static BoardStorage bs = new BoardStorage();
	private BoardStorage() {}
	public static BoardStorage getInstance() {
		return bs;
	}

	public String[] listBoards() {
		ArrayList<String> lhidatos = new ArrayList<>();
		String path = System.getProperty("user.dir");		
		File f = new File(path + File.separator + "Boards");
		for (File fileEntry : f.listFiles()) {	 
			String board = fileEntry.getName().substring(0, fileEntry.getName().length()-4);
            lhidatos.add(board);
		}
		String[] boardList = new String[lhidatos.size()];
		boardList = lhidatos.toArray(new String[0]);
		return boardList;
	}
	
	public void storeBoard(String board, String name) throws IOException {
		String path = System.getProperty("user.dir");	
		path = path + File.separator + "Boards";
		File directory= new File(path);
		if(! directory.exists()) {
			directory.mkdir();
		}
		File f = new File(path + File.separator + name + ".txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		writer.write(board);
		writer.close();
	}
	
	public String loadBoard(String name) throws IOException {
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Boards" + File.separator + name + ".txt"));
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return ptl;
	}
}