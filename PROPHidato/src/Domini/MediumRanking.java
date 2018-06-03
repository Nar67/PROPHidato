package Domini;

import java.io.IOException;

import com.google.gson.Gson;

public class MediumRanking extends Ranking{
	private static MediumRanking mr = new MediumRanking();
	public static MediumRanking getInstance() {
		return mr;
	}
	public MediumRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score+200);
		Gson gson = new Gson();
		String rts = gson.toJson(rank, Ranking.class);
		ControlDomini.getInstance().storeMediumRanking(rts);
	}
}
