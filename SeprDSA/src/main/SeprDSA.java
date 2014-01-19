package main;

import engine.graphics.Drawables;
import engine.graphics.display.Fullscreen;
import engine.graphics.display.Window;
import engine.input.Input;
import engine.physics.Physicals;
import game.EntryExitPoint;
import game.FuturePlane;
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
	private static int pixelsFromEdge = 100;
	private static int entryExitPointNumber = 5; // not including runway
	private static int wayPointNumber = 10;
	private static ArrayList<WayPoint> wayPointList = new ArrayList<WayPoint>();
	private static ArrayList<EntryExitPoint> entryExitPointList = new ArrayList<EntryExitPoint>();
	public static double timer;
	private static int score = 0;
	private static Random randomgen = new Random();
	public static Plane selectedPlane;
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
		System.out.println(score);
	}

	public static float getMagnitude(Vector vecA, Vector vecB) {
		Vector vecC = vecA.subtract(vecB);
		return (float) Math.sqrt(Math.pow(vecC.get(0), 2)
				+ Math.pow(vecC.get(1), 2) + Math.pow(vecC.get(2), 2));
	}

	public static Vector getWayPointPos(int counter) {
		boolean isValid = true;
		BasicVector testVector = new BasicVector(
				new double[] {
						((randomgen.nextDouble() - 0.5) * ((Display.getWidth() - pixelsFromEdge) - Sidemenu.width))
								+ Sidemenu.width / 2.0,
						(randomgen.nextDouble() - 0.5)
								* (Display.getHeight() - pixelsFromEdge),
						randomgen.nextDouble() * 20 });

		for (WayPoint waypoint : wayPointList) {
			if (getMagnitude(waypoint.getPos(), testVector) < 200.0f) {
				isValid = false;
			}
		}
		if (isValid || counter > 50) {
			System.out.println(counter);
			return testVector;
		} else {
			return getWayPointPos(counter + 1);
		}
	}

	public static void main(String[] args) throws InterruptedException,
			MalformedURLException, IOException {
		Drawables.initialise(new Window(1024, 640), 1024, 640, new File(
				"default.xml").toURI().toURL());
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);

		Map m = new Map();
		Sidemenu s = new Sidemenu();

		// Audible.playSound("sounds/arribba.wav", true, 0.1f);
		// Audible.playSound("sounds/Booboo.wav", true, 0.5f);
		// Audible.playSound("sounds/Arf.ogg", true, 0.5f);
		
		// EntryExit Points 1..4 are for top, right, bottom, left, 0 is airport
		// Top
		EntryExitPoint newExit1 = new EntryExitPoint(
				new BasicVector(new double[] {
				Sidemenu.width/2,
				Drawables.virtualDisplaySize().get(1) / 2,
				10000 } //this is altitude
				), 0, 360, 1);
		// Right
		EntryExitPoint newExit2 = new EntryExitPoint(
				new BasicVector(new double[] {
				Sidemenu.width/2 + Sidemenu.remainingDisplayWidth()/2,
				0,
				10000 } //this is altitude
				), 0, 360, 2);
		EntryExitPoint newExit3 = new EntryExitPoint(
				new BasicVector(new double[] {
				Sidemenu.width/2,
				-Drawables.virtualDisplaySize().get(1) / 2,
				10000 } //this is altitude
				), 0, 360, 3);
		EntryExitPoint newExit4 = new EntryExitPoint(
				new BasicVector(new double[] {
				Sidemenu.width/2 - Sidemenu.remainingDisplayWidth()/2,
				0,
				10000 } //this is altitude
				), 0, 360, 4);
		entryExitPointList.add(newExit1);
		entryExitPointList.add(newExit2);
		entryExitPointList.add(newExit3);
		entryExitPointList.add(newExit4);

		EntryExitPoint landingStrip = new EntryExitPoint(new BasicVector(
				new double[] { -10, -125, 0 }), 0, 5, 0); // Landing Strip
		entryExitPointList.add(landingStrip);

		for (Integer i = 1; i <= wayPointNumber; i++) { // Random waypoints
			WayPoint newWayPoint = new WayPoint(getWayPointPos(0), i.toString());
			wayPointList.add(newWayPoint);
		}

		// TEST PLANE
		Plane p = new Plane(FuturePlane.generateFlightNumber(), wayPointList,
				landingStrip, landingStrip);
		p.setBearing(0);

		while (true) {
			engine.timing.Timing.logic();
			Input.logic();
			Planes.updateTimer();
			Physicals.logic();
			Drawables.logic();
			s.drawSidemenu();

			if (Display.isCloseRequested()) { // If x is clicked you should
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}
		}
	}
}
