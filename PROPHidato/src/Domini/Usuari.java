package Domini;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Usuari {
    	@Expose
		private String nom;
		@Expose
		private String password;
		private Integer partidaID;
		private Integer boardNumerator;
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public Usuari(String nomu, String pass) {
			this.nom = nomu;
			this.password = getSHA256Hash(pass);
			this.partidaID = 0;
			this.boardID = 0;
			if(true) {
				//si l'usuari no existeix a la base de dades guardel
				saveUsuari();
			}
		}
		public Integer getPartidaID() {
			return partidaID;
		}
		public void setPartidaID(Integer partidaID) {
			this.partidaID = partidaID;
		}
		public Integer getBoardNumerator() {
			return boardNumerator;
		}
		public void setBoardNumerator(Integer boardNumerator) {
			this.boardNumerator = boardNumerator;
		}
		public Integer getBoardID() {
			return boardID;
		}
		public void setBoardID(Integer boardID) {
			this.boardID = boardID;
		}
		public int LogIn() {
			//return 1 if correct login 0 otherwise(user does not exists)
			if(true) {
				return 1;
			}
			return 0;
		}
		
		public int RegisterUser() {
			//retrona 1 si s'ha registrat correctament 0 otherwise
			if(true) {
				//si l'usuari no existeix a la base de dades guardel
				saveUsuari();
				return 1;
			}
			return 0;
		}
		public void setAnotherPassword(String pass) {
			this.password = pass;
		}
		private void saveUsuari() {
		    Gson gson = null;
		    gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		    String json = gson.toJson(this);
		    System.out.println(json);
		}
		private String  bytesToHex(byte[] hash) {
			        return DatatypeConverter.printHexBinary(hash);
		}
		private String getSHA256Hash(String data) {
			        String result = null;
			        try {
			            MessageDigest digest = MessageDigest.getInstance("SHA-256");
			            byte[] hash = digest.digest(data.getBytes("UTF-8"));
			            return bytesToHex(hash); // make it printable
			        }catch(Exception ex) {
			            ex.printStackTrace();
			        }
			        return result;
			    }

		public Usuari GetUsuari(String nomu, String pass) {
			//S'implementara en la seguent entrega ja que agafa un usuari de la base de dades
			return this;
		}
		public void printUsuari() {
			System.out.println(this.nom);
			System.out.println(this.password);
		}
		public void printNomUsuari() {
			System.out.println(this.nom);
		}
		public void ChangePassword(String pass) {
			String sha = getSHA256Hash(pass);
			if(this.password.equals(sha)) {
				System.out.println("Perdoni, ha introduit la mateixa contrasenya");
			}
			else this.password = sha;
		}
		public int MillorTemps(String dificultat) {
			//retornarà el millor temps de l'usuari depenent de la dificultat
			return 0;
		}
		public void SetRanking(String dificultat, Integer temps) {
			//aqui introduirà els resultats d'un usuari en en ranking
		}
		public void EsborrarUsuari() {
			//borrarà l'usuari de la base de Dades
		}
		public static void main(String[] args) {
			Usuari ursula = new Usuari("sole", "moco");
		}
		
}
