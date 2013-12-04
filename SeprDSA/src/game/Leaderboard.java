package game;
import engine.Drawable;
import engine.Sprite;
import game.LeaderboardEntry;

import java.awt.Font;
import java.io.*;
import java.util.Arrays;

import org.la4j.vector.dense.BasicVector;
import org.newdawn.slick.TrueTypeFont;

public class Leaderboard /*implements Drawable*/ {
	
	LeaderboardEntry[] leaderboardEntries = new LeaderboardEntry[5];
	String leaderboardFile = "thisIsNotTheLeaderboard.googleIsCool867";
	
	// From playing about with lwjgl text rendering.
	//public TrueTypeFont font;
	//public void writeStuff() {
	//	Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	//	font = new TrueTypeFont(awtFont, false);
	//}
	

	//TODO(Jamaal) make leaderboard drawable, then re-enable and adjust this
	//plus re-enable 'implements Drawable' in Class title at top.
	
//	public Sprite draw() {
//		return new Sprite(Images.map, new BasicVector(
//				new double[] { x, y }), 1.0f, 0.0f);
//	}
	
	public Leaderboard() {
		// Initialise
		for (int i = 0; i < leaderboardEntries.length; i++) {
			leaderboardEntries[i] = new LeaderboardEntry();
		}
		
		// Initialise values if first run.
		
			readLeaderboard();
			sortLeaderboard();
			saveLeaderboard();
		//}
		printLeaderboard();
	}
	
	public void sortLeaderboard() {
		Arrays.sort(leaderboardEntries);
	}
	
	public void saveLeaderboard() {
		try {
	        // create a new file with an ObjectOutputStream
	        FileOutputStream out = new FileOutputStream(leaderboardFile);
	        ObjectOutputStream oout = new ObjectOutputStream(out);
	
	        // write something in the file
	        for (int i = 0; i<leaderboardEntries.length; i++) {
	        	oout.writeObject(leaderboardEntries[i].getName());
	        	oout.writeObject(leaderboardEntries[i].getScore());
	        }
	        
	        oout.close();
		}
		catch (Exception ex) {
			System.out.println("Saving leaderboard raised exception.");
		}
	}
	
	public void readLeaderboard() {
		try {
			ObjectInputStream ois =
	                new ObjectInputStream(new FileInputStream(leaderboardFile));
			for (int i = 0; i<leaderboardEntries.length; i++) {
				leaderboardEntries[i].setName((String)ois.readObject());
				leaderboardEntries[i].setScore((Integer)ois.readObject());
			}
		}
		catch (Exception ex) {
			System.out.println("Saving leaderboard raised exception.");
		}
	}
	
	private void printLeaderboard() {
		for (int i = 0; i<leaderboardEntries.length; i++) {
			System.out.println(leaderboardEntries[i].getName() + " scored: " +
					leaderboardEntries[i].getScore());
		}
	}

}


