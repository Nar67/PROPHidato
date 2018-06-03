package Domini;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private void createCurrentUser(String nom, String pass) {
		this.currentuser = new Usuari(nom,pass);
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
	
	public String[][] generaHidato(String diff, String cellType, String adj) { 
		//s'ha d'adaptar a la capa de presentacio, li arriben 3 strings:
		//primer parametre -> la difficultat (pot ser: "Easy", "Medium", "Hard")
		//segon parametre -> tipus de cell (pot ser: "Triangle" , "Square", "Hexagon")
		//tercer parametre -> tipus d'adjacencia (pot ser: "Borders" o  "Borders and angles")
		//S'HA DE PENSAR COM ENVIAR LA PARTIDA A LA CAPA DE PRESENTACIO DONAT QUE NO ES PODEN PASSAR OBJECTES NO PRIMITIUS
		Partida partida = new Partida();
		Hidato hidato = partida.generarTaulell(diff, cellType, adj);
		System.out.println("Autogenerant hidato ... ");
		Hidato hidatobuit = new Hidato(hidato.getTaulell());
		while (!hidato.checkHidato()) {
			hidato = partida.generarTaulell(diff, cellType, adj);
			hidatobuit = hidato;
		}
		partida.setHidato(hidato);
		hidato.getTaulell().printBoard();
		return hidato.getTaulell().getMatriu();
	}
	
	/*public void jugar(Integer action) {
		if (action == 1) this.currentpartida = generaHidato();
		else if (action == 2) this.currentpartida = llegeixHidato();
		this.currentpartida.startPlaying();
	}*/
	
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
	
	public boolean logInUser(String username, String password) throws IOException {
		if(ControlPersistencia.getInstance().logInUser(username, password)) {
			createCurrentUser(username,password);
		}
		return false;
	}
	
	public boolean signUpUser(String username, String password) throws IOException {
		if(ControlPersistencia.getInstance().signUpUser(username, password)) {
			createCurrentUser(username,password);
		}
		return false;
	}
	
	public String[] listHidatos() {
		return ControlPersistencia.getInstance().listHidatos();
	}
	
	public String[] listGames() {
		return ControlPersistencia.getInstance().listGames(currentuser.getNom());
	}
	
	public HashMap<String, Integer> getEasyRanking() throws IOException {//TODO ranking
		String rank = ControlPersistencia.getInstance().getEasyRanking();
		Gson gson = new Gson();
		Ranking ranking = gson.fromJson(rank,  Ranking.class);
		return ranking.getRanking();
		/*
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
		scores.add("69");*/
	}
	
	public HashMap<String, Integer> getMediumRanking() throws IOException {//TODO ranking
		String rank = ControlPersistencia.getInstance().getMediumRanking();
		Gson gson = new Gson();
		Ranking ranking = gson.fromJson(rank,  Ranking.class);
		return ranking.getRanking();
		
		/*Ranking r = MediumRanking.getInstance();
	    for (Entry<String,Integer> pair : r.getRanking().entrySet()){
	        users.add(pair.getKey());
	        scores.add(String.valueOf(pair.getValue()));
	    }
		users.add("yas");
		users.add("hitler456");
		users.add("nersr");
		scores.add("123");
		scores.add("88");
		scores.add("69");*/
	}
	
	public HashMap<String, Integer> getHardRanking() throws IOException {//TODO ranking
		String rank = ControlPersistencia.getInstance().getHardRanking();
		Gson gson = new Gson();
		Ranking ranking = gson.fromJson(rank,  Ranking.class);
		return ranking.getRanking();
		
		/*Ranking r = HardRanking.getInstance();
	    for (Entry<String,Integer> pair : r.getRanking().entrySet()){
	        users.add(pair.getKey());
	        scores.add(String.valueOf(pair.getValue()));
	    }
		users.add("boi");
		users.add("hitler2");
		users.add("collPuter");
		scores.add("123");
		scores.add("88");
		scores.add("69");*/
	}
	
	public HashMap<String, Integer> getRanking() throws IOException {//TODO ranking
		
	}
	
	public HashMap<String, Integer> getMediumRanking() throws IOException {//TODO ranking
		
	}
	
	public HashMap<String, Integer> storeHardRanking() throws IOException {//TODO ranking
		
	}
	
	public void loadHidato(String name, String[] params, String[][] matriu) throws IOException {
		String hidato = ControlPersistencia.getInstance().loadHidato(name);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		Hidato h = gson.fromJson(hidato,  Hidato.class);
		params = h.getTaulell().getParams();
		matriu = h.getTaulell().getMatriu();
	}
	
}