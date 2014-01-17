package game;

import java.util.ArrayList;
import java.util.Random;

import engine.Timing;

public class Planes {
	
	/** 
	 * A limit to how many planes can be created at one time
	 */
	private static int maxplanenumb = 4;
	
	/**
	 * Creates a random number generator
	 */
	static Random randomgen = new Random();
	
	/**
	 * Creates a random time for plane spawning in milliseconds
	 */
	private static double spawnThreshhold = 3000 + randomgen.nextInt(6000);
	private static double currTime = 0;
	
	/** 
	 * This method holds all of the FuturePlanes in a list
	 */
	public static ArrayList<Plane> planes;
	static {
		planes = new ArrayList<Plane>();
	}

	/**
	 * This method adds a plane to the list 
	 * @param p
	 */
	public static void add(Plane p) {
		planes.add(p);
	}

	/** 
	 * This method removes a plane from the list
	 * @param p
	 */
	public static void remove(Plane p) {
		planes.remove(p);
	}
	
	/** 
	 * This method returns the number of planes in the list
	 * @return
	 */
	public static int size(){
		return planes.size();
	}
	
	/** 
	 * This method spawns planes on screen based on the time the game has already been running
	 * and the number of planes already on screen 
	 * @param time
	 * @throws InterruptedException
	 */
	public static void updateTimer(){
		if (Timing.timeSinceStart() >= spawnThreshhold && planes.size() < maxplanenumb){
			System.out.println("Added plane");
			FuturePlane tempFuturePlane = 
					new FuturePlane(randomgen.nextInt(2000));
			FuturePlanes.add(tempFuturePlane);
			spawnThreshhold = 2000 + randomgen.nextInt(6000);
		}
	}
	
}
