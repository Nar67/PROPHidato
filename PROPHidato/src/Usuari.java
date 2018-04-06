
public class Usuari {
		private String nom;
		private String password;
	
		public Usuari() {
			
		}
		
		public void CreateUsuari(String nomu, String pass) {
			this.nom = nomu;
			this.password = pass;
		}
		
		public Usuari GetUsuari() {
			return this;
		}
		public void ChangePassword(String nomu, String pass) {
			if(this.nom == nomu) {
				this.password = pass;
			}
		}
}
