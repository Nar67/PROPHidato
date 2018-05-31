package Persistencia;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;


public class UserStorage {
	private static UserStorage us = new UserStorage();
	private UserStorage() {}
	public static UserStorage getInstance() {
		return us;
	}
	
	public void storeUser(String data) {
		
	}
	public String getUser(String nom) throws JsonSyntaxException, IOException {
		File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
		 
		  BufferedReader br = new BufferedReader(new FileReader(file));
		 
		  String st;
		  while ((st = br.readLine()) != null) {
			    JsonElement jelement = new JsonParser().parse(st);
		    	JsonObject  jobject = jelement.getAsJsonObject();
		    	String result = jobject.get("nom").getAsString();
		}
		return st;
	}
	
	public boolean logInUser(String username, String password) { //password és la password hashejada.
		//TODO s'ha de completar de manera que miri si existeix l'usuari del que s'està volent fer logIn
		//si no existeix, retorna false
		//si existeix, es comprova que la password sigui correcte, si no ho és, retorna fals
		//si existeix l'usuari i la password és correcte, retorna true.
		return true;
	}
	
	public boolean signUpUser(String username, String password) { //password és la password hashejada.
		//TODO es comprova que l'usuari no existeix
		//si no existeix, es guarda l'usuari i retorna true
		//si existeix, retorna false.
		//No es el mateix que storeUser perque allà no és necesari comprovar si existeix, potser l'estas sobreescrivint.
		return true;
	}
}
