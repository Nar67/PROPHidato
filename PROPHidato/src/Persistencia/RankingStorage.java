package Persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RankingStorage {
	private static RankingStorage rs = new RankingStorage();
	private RankingStorage() {}
	public static RankingStorage getInstance() {
		return rs;
	}
	
	public String getEasyRanking() throws IOException {
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Rankings" + File.separator + "EasyRanking.txt"));
		
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return ptl;	
	}
	
	public String getMediumRanking() throws IOException {
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Rankings" + File.separator + "MediumRanking.txt"));
		
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return ptl;	
	}

	public String getHardRanking() throws IOException {
		String path = System.getProperty("user.dir");
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "Rankings" + File.separator + "HardRanking.txt"));
		
		String ptl = new String(btl, Charset.forName("UTF-8"));
		return ptl;	
	}
	
	public void storeEasyRanking(String rts) throws IOException {//TODO ranking
		String path = System.getProperty("user.dir");
		File f = new File(path + File.separator + "Rankings" + File.separator + "EasyRanking.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter pfts = new PrintWriter(f);
		pfts.write(rts);
		pfts.close();
	}
	
	public void storeMediumRanking(String rts) throws IOException {//TODO ranking
		String path = System.getProperty("user.dir");
		File f = new File(path + File.separator + "Rankings" + File.separator + "MediumRanking.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter pfts = new PrintWriter(f);
		pfts.write(rts);
		pfts.close();
	}
	
	public void storeHardRanking(String rts) throws IOException {//TODO ranking
		String path = System.getProperty("user.dir");
		File f = new File(path + File.separator + "Rankings" + File.separator + "HardRanking.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter pfts = new PrintWriter(f);
		pfts.write(rts);
		pfts.close();
	}
}
