package game;

import game.LeaderboardEntry;

import java.io.*;
import java.util.Arrays;

public class Leaderboard /* implements Drawable */{

	LeaderboardEntry[] leaderboardEntries = new LeaderboardEntry[5];
	String leaderboardFile = "thisIsNotTheLeaderboard.googleIsCool";

	// From playing about with lwjgl text rendering.
	// public TrueTypeFont font;
	// public void writeStuff() {
	// Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	// font = new TrueTypeFont(awtFont, false);
	// }

	// TODO(Jamaal) make leaderboard drawable, then re-enable and adjust this
	// plus re-enable 'implements Drawable' in Class title at top.

	// public Sprite draw() {
	// return new Sprite(Images.map, new BasicVector(
	// new double[] { x, y }), 1.0f, 0.0f);
	// }

	public Leaderboard() {
		// Initialise
		for (int i = 0; i < leaderboardEntries.length; i++) {
			leaderboardEntries[i] = new LeaderboardEntry();
		}

		// Initialise values if first run.
		File leaderboardFileCheckForFile = new File(leaderboardFile);
		if (!leaderboardFileCheckForFile.exists()) {
			System.out
					.println("Is first load, initialising leaderboard values");
			for (int i = 0; i < leaderboardEntries.length; i++) {
				leaderboardEntries[i].setName("Sam");
				leaderboardEntries[i].setScore((double) 5 + i);
			}
			sortLeaderboard(leaderboardEntries);
			saveLeaderboard();
		} else {
			readLeaderboard();
			sortLeaderboard(leaderboardEntries);
			saveLeaderboard();
		}

		// This is a test of the leaderboard
		// TODO(Dan) Turn this block into a test, so we can add these values and
		// check we get the expected output array of LeaderboardEntries.
		// printLeaderboard();
		// addLeaderboardEntry("a", 17);
		// addLeaderboardEntry("b", 1);
		// addLeaderboardEntry("c", 5.5);
		// addLeaderboardEntry("d", 8.3);
		// addLeaderboardEntry("e", 6.2);
		// printLeaderboard(); // Expect a 17, s 9, d 8.3, s 8, s 7
	}

	public void addLeaderboardEntry(String name, double score) {
		LeaderboardEntry[] tempLeaderboardEntries = new LeaderboardEntry[6];
		System.arraycopy(leaderboardEntries, 0, tempLeaderboardEntries, 0,
				leaderboardEntries.length);
		tempLeaderboardEntries[5] = new LeaderboardEntry(name, score);
		sortLeaderboard(tempLeaderboardEntries);
		System.arraycopy(tempLeaderboardEntries, 0, leaderboardEntries, 0,
				leaderboardEntries.length);
		saveLeaderboard();
	}

	private void sortLeaderboard(LeaderboardEntry[] leaderboardToSort) {
		Arrays.sort(leaderboardToSort);
	}

	private void saveLeaderboard() {
		try {
			// create a new file with an ObjectOutputStream
			FileOutputStream out = new FileOutputStream(leaderboardFile);
			ObjectOutputStream oout = new ObjectOutputStream(out);

			// write something in the file
			for (int i = 0; i < leaderboardEntries.length; i++) {
				oout.writeObject(leaderboardEntries[i].getName());
				oout.writeObject(leaderboardEntries[i].getScore());
			}

			oout.close();
		} catch (Exception ex) {
			System.out.println("Saving leaderboard raised exception.");
		}
	}

	private void readLeaderboard() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					leaderboardFile));
			for (int i = 0; i < leaderboardEntries.length; i++) {
				leaderboardEntries[i].setName((String) ois.readObject());
				leaderboardEntries[i].setScore((Double) ois.readObject());
			}

			ois.close();
		} catch (Exception ex) {
			System.out.println("Saving leaderboard raised exception.");
		}
	}

}
