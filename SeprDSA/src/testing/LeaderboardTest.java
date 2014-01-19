package testing;

import static org.junit.Assert.*;

import org.junit.Test;
import game.Leaderboard;
import game.LeaderboardEntry;

public class LeaderboardTest {

	@Test
	public void testAddLeaderBoardEntries() {
		LeaderboardEntry[] leaderboardEntries = new LeaderboardEntry[5];
		for (int i = 0; i < leaderboardEntries.length; i++) {
			leaderboardEntries[i] = new LeaderboardEntry();
		}
		
		
	}

	@Test
	public void testAddLeaderboardEntry() {
		fail("Not yet implemented");
	}

}
