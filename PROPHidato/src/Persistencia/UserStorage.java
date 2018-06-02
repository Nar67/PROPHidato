package Persistencia;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import Domini.ControlDomini;


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

	public boolean logInUser(String username, String password)  throws IOException{ //password és la password hashejada.
		//TODO s'ha de completar de manera que miri si existeix l'usuari del que s'està volent fer logIn
		//si no existeix, retorna false
		//si existeix, es comprova que la password sigui correcte, si no ho és, retorna fals
		//si existeix l'usuari i la password és correcte, retorna true.
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
	
	public boolean signUpUser(String username, String password) throws IOException { //password és la password hashejada.
		//TODO es comprova que l'usuari no existeix
		//si no existeix, es guarda l'usuari i retorna true
		//si existeix, retorna false.
		//No es el mateix que storeUser perque allà no és necesari comprovar si existeix, potser l'estas sobreescrivint.
		System.out.println("fdasfdsae");

		if(this.logInUser(username, password)){
			System.out.println("falsetat");
			return false;
		}else {
			System.out.println("hola");
			this.storeUser(password,username);
			return true;
			
		}
		
	}
}
