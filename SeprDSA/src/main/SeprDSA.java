package main;

import engine.audio.Audio;
import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.display.Fullscreen;
import engine.graphics.display.Window;
import engine.input.Input;
import engine.physics.Physicals;
import engine.timing.Timing;
import game.EntryExitPoint;
import game.FuturePlane;
import game.FuturePlanes;
import game.Leaderboard;
import game.MainMenu;
import game.Map;
import game.Plane;
import game.Planes;
import game.Sidemenu;
import game.WayPoint;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

public class SeprDSA {

	public static long timer1 = System.nanoTime();
	public static long timer2 = System.nanoTime();
	private static int pixelsFromEdge = 400;
	private static int wayPointNumber = 10;
	private static ArrayList<WayPoint> wayPointList = new ArrayList<WayPoint>();
	private static ArrayList<EntryExitPoint> entryExitPointList = new ArrayList<EntryExitPoint>();
	public static double timer;
	private static int score = 0;
	private static Random randomgen = new Random();
	public static Plane selectedPlane;
	public static boolean gameCurrentlyPlaying = false;
	public static boolean resetGame = false;
	/**
	 * Use to increase the size of the game window and to scale everything as
	 * such.
	 */
	public static double resizeWindowScaleFactor = 1.25;
	/**
	 * Initialise leaderboard here so it can be accessed globally
	 */
	public static Leaderboard l = new Leaderboard();

	public static ArrayList<WayPoint> getWayPoints() {
		return wayPointList;
	}

	public static ArrayList<EntryExitPoint> getEntryExitPoints() {
		return entryExitPointList;
	}

	public static void updateScore(int scoreToAdd) {
		score += scoreToAdd;
	}

	public static float getMagnitude(Vector vecA, Vector vecB) {
		Vector vecC = vecA.subtract(vecB);
		return (float) Math.sqrt(Math.pow(vecC.get(0), 2)
				+ Math.pow(vecC.get(1), 2));
	}

	public static Vector getWayPointPos(int counter) {
		boolean isValid = true;
		BasicVector testVector = new BasicVector(
				new double[] {
						((randomgen.nextDouble() - 0.5) * ((Drawables
								.virtualDisplaySize().get(0) - pixelsFromEdge) - Sidemenu.width))
								+ Sidemenu.width / 2.0,
						(randomgen.nextDouble() - 0.5)
								* (Drawables.virtualDisplaySize().get(1) - pixelsFromEdge),
						randomgen.nextDouble() * 13 });

		for (WayPoint waypoint : wayPointList) {
			if (getMagnitude(waypoint.getPos(), testVector) < 400.0f) {
				isValid = false;
			}
		}
		if (isValid) {
			return testVector;
		} else {
			return getWayPointPos(counter + 1);
		}
	}

	public static void main(String[] args) throws InterruptedException,
			MalformedURLException, IOException {
//		System.setProperty("org.lwjgl.librarypath", new File("lib/native/").getAbsolutePath());
		Drawables.initialise(new Window((int) (1024 * resizeWindowScaleFactor),
				(int) (640 * resizeWindowScaleFactor)), 4096, 2560, new File(
				"default.xml").toURI().toURL());
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);
		Display.setTitle("Dat Flying Game");

		Map m = new Map();
		MainMenu mm = new MainMenu();
		Sidemenu s = new Sidemenu();
		Audio.playSound("res/sounds/BGM.wav", true, 0.3f);

		// EntryExit Points 1..4 are for top, right, bottom, left, 0 is airport
		// Top
		EntryExitPoint newExit1 = new EntryExitPoint(new BasicVector(
				new double[] { Sidemenu.width / 2,
						Drawables.virtualDisplaySize().get(1) / 2, 11 } // this
																		// is
																		// altitude
				), -90, 85, 1);
		// Right
		EntryExitPoint newExit2 = new EntryExitPoint(new BasicVector(
				new double[] {
						Sidemenu.width / 2 + Sidemenu.remainingDisplayWidth()
								/ 2, 0, 8 } // this is altitude
				), -180, 85, 2);
		// Bottom
		EntryExitPoint newExit3 = new EntryExitPoint(new BasicVector(
				new double[] { Sidemenu.width / 2,
						-Drawables.virtualDisplaySize().get(1) / 2, 2 } // this
																		// is
																		// altitude
				), 90, 85, 3);
		// Left
		EntryExitPoint newExit4 = new EntryExitPoint(new BasicVector(
				new double[] {
						Sidemenu.width / 2 - Sidemenu.remainingDisplayWidth()
								/ 2, 0, 5 } // this is altitude
				), 0, 85, 4);
		entryExitPointList.add(newExit1);
		entryExitPointList.add(newExit2);
		entryExitPointList.add(newExit3);
		entryExitPointList.add(newExit4);

		EntryExitPoint landingStripRight = new EntryExitPoint(new BasicVector(
				new double[] { -40, -500, 0.1 }), 0, 15, 0); // Landing Strip
		entryExitPointList.add(landingStripRight);

		EntryExitPoint landingStripLeft = new EntryExitPoint(new BasicVector(
				new double[] { -40, -500, 0.1 }), 180, 15, 0); // Landing Strip
		entryExitPointList.add(landingStripLeft);

		for (Integer i = 1; i <= wayPointNumber; i++) { // Random waypoints
			WayPoint newWayPoint = new WayPoint(getWayPointPos(0), i.toString());
			wayPointList.add(newWayPoint);
		}

		while (true) {
			if (gameCurrentlyPlaying) {
				engine.timing.Timing.logic();
				Planes.updateTimer();
				Physicals.logic();
			}
			Input.logic();
			Drawables.logic();
			s.drawSidemenu();

			if (Display.isCloseRequested()) { // If x is clicked you should
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}

			if (resetGame) {
				resetGame = false;
				gameCurrentlyPlaying = false;
				FuturePlanes.futurePlanes.clear();
				for (Plane p : Planes.planes) {
					p.quickRemove();
				}
				Planes.planes.clear();
				Timing.clearAllTasks();
				mm.show();
			}
		}
	}

	public static void resetGame() {
		resetGame = true;
	}
}
