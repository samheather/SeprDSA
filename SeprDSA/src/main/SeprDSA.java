package main;
import engine.graphics.Drawables;
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

import java.util.ArrayList;
import java.util.Random;

import org.la4j.vector.dense.BasicVector;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

public class SeprDSA {
	
	public static double timer = System.currentTimeMillis();
	private static int pixelsFromEdge = 100;
	private static int entryExitPointNumber = 5; // not including runway
	private static int wayPointNumber = 10; 
	private static ArrayList<WayPoint> wayPointList = new ArrayList<WayPoint>();
	private static ArrayList<EntryExitPoint> entryExitPointList = new ArrayList<EntryExitPoint>();
	public Plane selectedPlane;
	/**
	 *Initialise leaderboard here so it can be accessed globally
	 */
	public Leaderboard l;
	
	public static ArrayList<WayPoint> getWayPoints(){
		return wayPointList;
	}
	
	public static ArrayList<EntryExitPoint> getEntryExitPoints(){
		return entryExitPointList;
	}

	public static void main(String[] args) throws InterruptedException {
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random randomgen = new Random(System.currentTimeMillis());
		Drawables.initialise(new Window(1024, 640), 1024, 640, menu.canvas);
		Display.setTitle("Dat flying game");
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);

		Map m = new Map();
		l = new Leaderboard();
		//Sidemenu s = new Sidemenu();

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

		for (Integer i = 0; i < wayPointNumber; i++) { // Random waypoints
			WayPoint newWayPoint = new WayPoint(new BasicVector(new double[] {
					(randomgen.nextDouble() - 0.5)
							* (Display.getWidth() - pixelsFromEdge),
					(randomgen.nextDouble() - 0.5)
							* (Display.getHeight() - pixelsFromEdge),
					randomgen.nextDouble() * 20 }),i.toString());
			wayPointList.add(newWayPoint);
		}
		
		while (true) {
			Physicals.logic(System.currentTimeMillis()-timer);
			Input.logic();
			Drawables.logic();
			Planes.updateTimer(System.currentTimeMillis() - timer);

			if (Display.isCloseRequested()) { // If x is clicked you should
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}
		timer = System.currentTimeMillis();
		}
	}

}
