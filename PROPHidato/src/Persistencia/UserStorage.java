package Persistencia;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;



public class UserStorage {
	private static UserStorage us = new UserStorage();
	private UserStorage() {}
	public static UserStorage getInstance() {
		return us;
	}
	
	public void storeUser(String data, String nom)  throws IOException{
		String path = System.getProperty("user.dir");	
		path = path + File.separator + "Users" + File.separator + nom;
		File directory= new File(path);
		if(! directory.exists()) {
			directory.mkdirs();
		}
		File f = new File(path + File.separator + "shadow.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		writer.write(data);
		writer.close();
	}

	public boolean logInUser(String username, String password)  throws IOException{
		String path = System.getProperty("user.dir");
		byte[] btl;

		try {
			btl = Files.readAllBytes(Paths.get(path + File.separator + "Users" + File.separator + username + File.separator  + "shadow.txt"));
			String ptl = new String(btl, Charset.forName("UTF-8"));
			if(ptl.equals(password)) {
				return true;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("gas");
		}
		System.out.println("gaaas");

		return false;
	}
	
	public boolean signUpUser(String username, String password) throws IOException { 

		if(this.logInUser(username, password)){
			return false;
		}else {
			this.storeUser(password,username);
			System.out.println("hola");
			return true;
		}
		
	}
}
