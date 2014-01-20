package game;

import java.util.ArrayList;
import java.util.Random;

import engine.timing.Timing;
import engine.timing.Timing.NRunnable;

import main.SeprDSA;

public class FuturePlane {
	
	public int delayTillFuturePlanesBecomePlanes = 15;
	public int timeTillAppears;

	/**
	 * Stores the flight number of the plane
	 */
	private String fnumber;

	/**
	 * Creates a random number generator based on the current System Time
	 */
	static Random randomgen = new Random(System.currentTimeMillis());
	/**
	 * Selects an EntryExitPoint to become the entry point for a created plane
	 */
	private EntryExitPoint enterPoint = SeprDSA.getEntryExitPoints().get(
			randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));

	/**
	 * Selects an EntryExitPoint to become the exit point for a created plane
	 */
	private EntryExitPoint exitPoint = SeprDSA.getEntryExitPoints().get(
			randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private ArrayList<WayPoint> wayPointList = new ArrayList<WayPoint>();

	/**
	 * Constructor for FuturePlane
	 * 
	 * @param delayTime
	 * @throws InterruptedException
	 */
	public FuturePlane(int delayTime) {
		fnumber = generateFlightNumber();
		wayPointList = new ArrayList<WayPoint>();
		int lastUsed = -1;
		for (int i = 0; i < 10; i++) {
			int newNumber = getWayPointNumber(lastUsed);
			wayPointList.add(SeprDSA.getWayPoints().get(newNumber));
			lastUsed = newNumber;
		}
		System.out.println(wayPointList.toString());
		// DELAYTIME COULD BE USED IN THE SCHEDULER SOMEHOW
		// while (localTime < localTime + delayTime) {localTime +=
		// SeprDSA.timer;}
		timeTillAppears = delayTillFuturePlanesBecomePlanes;
		Timing.doNTimes(delayTillFuturePlanesBecomePlanes, 1000, new NRunnable() {
			@Override
			public void run(int n) {
				if (n == 1) {
					Plane p = new Plane(fnumber, wayPointList, enterPoint, exitPoint);
					p.setBearing(randomgen.nextFloat() * 360);
					FuturePlanes.pop();
				}
				else {
					timeTillAppears -= 1;
				}
				
			}
		});
	}
	
	public void createPlaneFromFuturePlane() {
		
	}

	/**
	 * Create an eight digit string of letters and numbers
	 */
	public static String generateFlightNumber() {
		String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = new String();
		for (int i = 0; i < 8; i++) {
			result = result
					+ alphabet.charAt(randomgen.nextInt(alphabet.length()));
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
	private static int getWayPointNumber(int lastUsed) {
		int potentialNewNumber = randomgen.nextInt(SeprDSA.getWayPoints()
				.size());
		if (lastUsed == potentialNewNumber) {
			return getWayPointNumber(lastUsed);
		} else {
			return potentialNewNumber;
		}
	}
	
	public EntryExitPoint getEntryPoint() {
		return enterPoint;
	}

}
