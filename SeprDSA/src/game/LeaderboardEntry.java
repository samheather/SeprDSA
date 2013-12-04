package game;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
	
	private String name;
	private double score;
	
	public LeaderboardEntry() {
		// Do nothing.
	}

	public LeaderboardEntry(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(LeaderboardEntry other) {
		if (this.score > other.score) { return -1; }
		if (this.score < other.score) { return 1; }
	    return 0;
	}
}
