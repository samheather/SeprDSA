package game;
import engine.Drawable;
import engine.Sprite;
import game.LeaderboardEntry;
import java.awt.Font;

import org.la4j.vector.dense.BasicVector;
import org.newdawn.slick.TrueTypeFont;

public class Leaderboard implements Drawable {
LeaderboardEntry[] leaderboardentries = new LeaderboardEntry[5];

public TrueTypeFont font;

public Sprite draw() {
	return new Sprite(Images.map, new BasicVector(
			new double[] { x, y }), 1.0f, 0.0f);
}

private Leaderboard() {
	for (int i = 0;i < 5; i++){
		leaderboardentries[i].setName("Sam");
		leaderboardentries[i].setScore(5*i);
	}
}

public void getLeaderboardEntry() {
	Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	font = new TrueTypeFont(awtFont, false);
}

}


