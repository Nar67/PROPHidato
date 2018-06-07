package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



public class PartidaStorage {
	
	private static PartidaStorage ps = new PartidaStorage();
	private PartidaStorage() {}
	public static PartidaStorage getInstance() {
		return ps;
	}
	
	public ArrayList<String> getPartides(String user) {
		ArrayList<String> partides = new ArrayList<>();
		String path = System.getProperty("user.dir");		
		File f = new File(path + File.separator + "Users" + File.separator + user);
		for (File fileEntry : f.listFiles()) {
			if(!fileEntry.getName().equals("shadow.txt"))
				partides.add(fileEntry.getName().substring(0, fileEntry.getName().length()-4));
		}
		return partides;
	}
	
	public String loadPartida(String user, String name) throws IOException {
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Users" + File.separator + user + File.separator + name + ".txt"));
		
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return ptl;		
	}
	
	public void savePartida(String pts, String user, String name) throws IOException {
		String path = System.getProperty("user.dir");
		File f = new File(path + File.separator + "Users" + File.separator + user + File.separator + name + ".txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter pfts = new PrintWriter(f);
		pfts.write(pts);
		pfts.close();		
	}
}