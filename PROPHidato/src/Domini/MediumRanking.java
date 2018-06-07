package Domini;

import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

public class MediumRanking extends Ranking{
	private static MediumRanking mr = new MediumRanking();
	public static MediumRanking getInstance() throws IOException {
		mr.setRanking(ControlDomini.getInstance().getMediumRanking());
		return mr;
	}
	public MediumRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score+200);
		Gson gson = new Gson();
		String rts = gson.toJson(this);
		ControlDomini.getInstance().storeMediumRanking(rts);
	}
	public void setRanking(HashMap<String, Integer> ranking) {
		this.rank = ranking;
	}
}
