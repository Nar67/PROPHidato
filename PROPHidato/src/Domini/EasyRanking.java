package Domini;

import java.io.IOException;

import com.google.gson.Gson;

public class EasyRanking extends Ranking{
	private static EasyRanking er = new EasyRanking();
	public static EasyRanking getInstance() {
		return er;
	}	
	public EasyRanking() {
		super();
	}
	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score);
		Gson gson = new Gson();
		String rts = gson.toJson(rank, Ranking.class);
		ControlDomini.getInstance().storeEasyRanking(rts);
	}	
}
