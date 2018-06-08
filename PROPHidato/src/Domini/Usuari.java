package Domini;

import com.google.gson.annotations.Expose;

public class Usuari {
    	@Expose
		private String nom;
		@Expose
		private String password;

		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public Usuari(String nomu, String pass) {
			this.nom = nomu;
			this.password = ControlDomini.getInstance().getSHA256Hash(pass);
		}
		

		public Usuari GetUsuari(String nomu, String pass) {
			//S'implementara en la seguent entrega ja que agafa un usuari de la base de dades
			return this;
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
