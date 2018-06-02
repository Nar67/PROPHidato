package Domini;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Persistencia.ControlPersistencia;



public class ControlDomini {
	private Partida currentpartida;
	private Usuari currentuser;
	private static ControlDomini cd = new ControlDomini();
	private ControlDomini() {}
	public static ControlDomini getInstance() {
		return cd;
	}
	
	public String getCurrentUsername() {
		return ControlDomini.getInstance().currentuser.getNom();
	}
	
	public Integer getBoardNumerator() {
		return ControlDomini.getInstance().getBoardNumerator();
	}
	
	public Partida llegeixHidato() {
		Partida partida = new Partida();
		Hidato hidato = partida.llegirTaulell();
		while (!hidato.checkHidato()) {
			System.out.println("L'hidato proposat no es resoluble");
			hidato = partida.llegirTaulell();
		}
		partida.setHidato(hidato);
		hidato.printHidatoOriginal();
		return partida;
	}
	
	public Partida generaHidato() { 
		//s'ha d'adaptar a la capa de presentacio, li arriben 3 strings:
		//primer parametre -> la difficultat (pot ser: "Easy", "Medium", "Hard")
		//segon parametre -> tipus de cell (pot ser: "Triangle" , "Square", "Hexagon")
		//tercer parametre -> tipus d'adjacencia (pot ser: "Borders" o  "Borders and angles")
		//S'HA DE PENSAR COM ENVIAR LA PARTIDA A LA CAPA DE PRESENTACIO DONAT QUE NO ES PODEN PASSAR OBJECTES NO PRIMITIUS
		Partida partida = new Partida();
		System.out.println("Escoja dificultad: 1(Q,C), 2(Q,CA), 3(H,C), 4(H,CA), 5(T,C), 6(T,CA).");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		int diff = keyboard.nextInt();
		Hidato hidato = partida.generarTaulell(diff);
		System.out.println("Autogenerant hidato ... ");
		Hidato hidatobuit = new Hidato(hidato.getTaulell());
		while (!hidato.checkHidato()) {
			hidato = partida.generarTaulell(diff);
			hidatobuit = hidato;
		}
		partida.setHidato(hidato);
		hidatobuit.getTaulell().printBoard();
		return partida;
	}
	
	public void jugar(Integer action) {
		if (action == 1) this.currentpartida = generaHidato();
		else if (action == 2) this.currentpartida = llegeixHidato();
		this.currentpartida.startPlaying();
	}
	
	public ArrayList<String> getPartides(String user) {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		return cp.getPartides(user);
	}
	
	private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
	}
	
	public String getSHA256Hash(String data) {
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
	
	public void loadPartida(String user, Integer ID) throws IOException {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		String partida = cp.loadPartida(user, ID);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		this.currentpartida = gson.fromJson(partida,  Partida.class);
	}
	
	public void savePartida() throws IOException {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		cp.savePartida(this.currentpartida.partidaToString(), this.currentpartida.getUser().getNom(), this.currentpartida.getUser().getPartidaID());
		this.currentpartida.getUser().setPartidaID(1 + this.currentpartida.getUser().getPartidaID());
	}

	protected void storeBoard(String board) throws IOException {
		ControlPersistencia cpers = ControlPersistencia.getInstance();
		cpers.storeBoard(board);
		ControlDomini cd = ControlDomini.getInstance();
		cd.currentuser.setBoardNumerator(cd.getBoardNumerator() + 1) ;
	}
	/* TODO loadboard
	protected Board loadBoard() {
		ControlPersistencia cpers = ControlPersistencia.getInstance();
		
	}
	*/
	
	public boolean logInUser(String username, String password) {
		return ControlPersistencia.getInstance().logInUser(username, password);
	}
	
	public boolean signUpUser(String username, String password) {
		return ControlPersistencia.getInstance().logInUser(username, password);
	}
	
	public String[] listHidatos() {
		return ControlPersistencia.getInstance().listHidatos();
	}
	
	public String[] listGames() {
		return ControlPersistencia.getInstance().listGames(currentuser.getNom());
	}
	
	public void getEasyRanking(ArrayList<String> users, ArrayList<String> scores) {//TODO ranking
		Ranking r = EasyRanking.getInstance();
	    for (Entry<String,Integer> pair : r.getRanking().entrySet()){
	        users.add(pair.getKey());
	        scores.add(String.valueOf(pair.getValue()));
	    }
		users.add("peneman");
		users.add("hitler");
		users.add("davidogayer");
		scores.add("123");
		scores.add("88");
		scores.add("69");
	}
	
	public void getMediumRanking(ArrayList<String> users, ArrayList<String> scores) {//TODO ranking
		Ranking r = MediumRanking.getInstance();
	    for (Entry<String,Integer> pair : r.getRanking().entrySet()){
	        users.add(pair.getKey());
	        scores.add(String.valueOf(pair.getValue()));
	    }
		users.add("yas");
		users.add("hitler456");
		users.add("nersr");
		scores.add("123");
		scores.add("88");
		scores.add("69");
	}
	
	public void getHardRanking(ArrayList<String> users, ArrayList<String> scores) {//TODO ranking
		Ranking r = HardRanking.getInstance();
	    for (Entry<String,Integer> pair : r.getRanking().entrySet()){
	        users.add(pair.getKey());
	        scores.add(String.valueOf(pair.getValue()));
	    }
		users.add("boi");
		users.add("hitler2");
		users.add("collPuter");
		scores.add("123");
		scores.add("88");
		scores.add("69");
	}
	
}
