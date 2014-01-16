package main;
import engine.graphics.Drawables;
import engine.graphics.display.Window;
import engine.input.Input;
import engine.physics.Physicals;
import game.BackgroundGradient;
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
	public Plane selectedPlane;
	/**
	 *Initialise leaderboard here so it can be accessed globally
	 */
	public static Leaderboard l = new Leaderboard();
	
	public static ArrayList<WayPoint> getWayPoints(){
		return wayPointList;
	}
	
	public static ArrayList<EntryExitPoint> getEntryExitPoints(){
		return entryExitPointList;
	}
	
	public static void updateScore(int scoreToAdd){
		score += scoreToAdd;
		System.out.println(score);
	}
	
//	public static Vector getWeightedVector() {
//		
//	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
		Drawables c = new Drawables();
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random randomgen = new Random(System.currentTimeMillis());
		Drawables.initialise(new Window(1024, 640), 824, 640, menu.canvas, new File(
				"default.xml").toURI().toURL());
		Display.setTitle("Dat flying game");
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);

		BackgroundGradient b = new BackgroundGradient();
		Map m = new Map();
		Sidemenu s = new Sidemenu();

		// Audible.playSound("sounds/arribba.wav", true, 0.1f);
		// Audible.playSound("sounds/Booboo.wav", true, 0.5f);
		// Audible.playSound("sounds/Arf.ogg", true, 0.5f);
		for (int i = 1; i <= entryExitPointNumber; i++) { // Random EntryExits
			EntryExitPoint newExit = new EntryExitPoint(new BasicVector(new double[] { 0, 0, 0 }), 0,
					360, i);
			entryExitPointList.add(newExit);
		}

		EntryExitPoint landingStrip = new EntryExitPoint(new BasicVector(new double[] { -170, -48, 0 }), 0,
				10, 0); // Landing Strip
		entryExitPointList.add(landingStrip);

		for (Integer i = 1; i <= wayPointNumber; i++) { // Random waypoints
			WayPoint newWayPoint = new WayPoint(new BasicVector(new double[] {
					(randomgen.nextDouble() - 0.5)
							* (Drawables.virtualDisplaySize().get(0) - pixelsFromEdge),
					(randomgen.nextDouble() - 0.5)
							* (Drawables.virtualDisplaySize().get(1) - pixelsFromEdge),
					randomgen.nextDouble() * 20 }),i.toString());
			wayPointList.add(newWayPoint);
		}
		Plane p = new Plane(FuturePlane.generateFlightNumber(), wayPointList, landingStrip, landingStrip);
		p.setBearing(0);
		
		while (true) {
			timer2 = timer1;
			timer1 = System.nanoTime();
			double dtimer = Math.abs((timer1 - timer2) / 100000000.0);
			dtimer = 0.1;
			timer = dtimer;
			Physicals.logic(dtimer);
			Input.logic();
			engine.Timing.logic();
			Drawables.logic();
			Planes.updateTimer(dtimer);

			if (Display.isCloseRequested()) { // If x is clicked you should
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}
		}
	}
}
