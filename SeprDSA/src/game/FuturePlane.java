package game;

import java.util.ArrayList;
import java.util.Random;

import main.SeprDSA;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

public class FuturePlane {
	private int velocity;
	private float rotation;
	
	/**
	 * Stores the flight number of the plane
	 */
	private String fnumber;
	private static int pixelsFromEdge = 100;
	
	/**
	 * Creates a random number generator based on the current System Time
	 */
	static Random randomgen = new Random(System.currentTimeMillis());
	
	/**
	 * Selects an EntryExitPoint to become the entry point for a created plane
	 */
	private EntryExitPoint enterPoint = SeprDSA.getEntryExitPoints()
			.get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	
	/**
	 * Selects an EntryExitPoint to become the exit point for a created plane
	 */
	private EntryExitPoint exitPoint = SeprDSA.getEntryExitPoints()
			.get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private static ArrayList <WayPoint> wayPointList = new ArrayList<WayPoint>();
	
	/** Constructor for FuturePlane
	 * 
	 * @param delayTime
	 * @throws InterruptedException
	 */
	public FuturePlane(int delayTime) throws InterruptedException {
		fnumber = generateFlightNumber();
		double localTime = 0;
		//DELAYTIME COULD BE USED IN THE SCHEDULER SOMEHOW
		while (localTime < delayTime) {localTime += SeprDSA.timer;}
		Plane p = new Plane(fnumber, wayPointList, enterPoint, exitPoint);
		p.setBearing(randomgen.nextFloat()*360);
	}
	
	/** 
	 * Create an eight digit string of letters and numbers 
	 */
	public static String generateFlightNumber() {
		String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = new String();
		for (int i = 0; i < 8; i++) {
			result = result + alphabet.charAt(randomgen.nextInt(alphabet.length()));
		}
		return result;
	}
	
	/** 
	 * getFnumber is used to retrieve the flight number
	 */
	public String getFnumber() {
		return fnumber;
	}
	
	/**
	 * Creates a list of waypoints that will be used for the current plane
	 */
	private static void main() {
		for (int i = 0; i < 10; i++){
			wayPointList.add( SeprDSA.getWayPoints()
					.get( randomgen.nextInt( SeprDSA.getWayPoints().size() ) ) );
		}
	}

}
