package Domini;
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

		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public Usuari(String nomu, String pass) {
			this.nom = nomu;
			this.password = ControlDomini.getInstance().getSHA256Hash(pass);
			this.partidaID = 0;
		}
		public Integer getPartidaID() {
			return partidaID;
		}
		public void setPartidaID(Integer partidaID) {
			this.partidaID = partidaID;
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
			String sha = ControlDomini.getInstance().getSHA256Hash(pass);
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
		
}
