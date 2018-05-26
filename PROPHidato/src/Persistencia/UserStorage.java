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
}
