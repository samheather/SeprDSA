package game;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
private int score;
private String name;

public int getScore() {
	return score;
}

public String getName() {
	return name;
}

public void setScore(int score) {
	this.score = score;
}

public void setName(String name) {
	this.name = name;
}

public int compareTo(LeaderboardEntry other) {
    return this.score - other.score;
}

}
