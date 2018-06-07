package Domini;

import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

public class EasyRanking extends Ranking{
	private static EasyRanking er = new EasyRanking();
	public static EasyRanking getInstance() throws IOException {
		er.setRanking(ControlDomini.getInstance().getEasyRanking());
		return er;
	}		
	public EasyRanking(){
		super();
	}
	
	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score);
		Gson gson = new Gson();
		String rts = gson.toJson(this);
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	public void setRanking(HashMap<String, Integer> ranking) {
		this.rank = ranking;
	}
	
}
