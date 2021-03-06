package game;

import java.util.ArrayList;
import java.util.Random;

import engine.timing.Timing;
import engine.timing.Timing.NRunnable;

import main.SeprDSA;

public class FuturePlane {

	public int delayTillFuturePlanesBecomePlanes = 5;

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
	 * @param delayTime Time in milliseconds before a plane is created
	 * @throws InterruptedException
	 */
	public FuturePlane(int delayTime) {
		fnumber = generateFlightNumber();
		wayPointList = new ArrayList<WayPoint>();
		int lastUsed = -1;
		for (int i = 0; i < 2 + randomgen.nextInt(2); i++) {
			int newNumber = getWayPointNumber(lastUsed);
			wayPointList.add(SeprDSA.getWayPoints().get(newNumber));
			lastUsed = newNumber;
		}
		//System.out.println(wayPointList.toString());
		timeTillAppears = delayTillFuturePlanesBecomePlanes;
		if (Planes.planes.size() == 0) {
			Timing.doIn(100, new NRunnable() {
				@Override
				public void run(int n) {
				Plane p = new Plane(fnumber, wayPointList,
						enterPoint, exitPoint, (float) ((randomgen
								.nextDouble() - 0.5)
								* 2.0
								* (Math.min(enterPoint.getTolerance()+88,randomgen.nextInt(176))-88)
								+ enterPoint.getBearingNeeded()));
				FuturePlanes.pop();
				}
			});
		}else{
			Timing.doNTimes(delayTillFuturePlanesBecomePlanes, 1000,
					new NRunnable() {
						@Override
						public void run(int n) {
							if (n == 1) {
								Plane p = new Plane(fnumber, wayPointList,
										enterPoint, exitPoint, (float) ((randomgen
												.nextDouble() - 0.5)
												* 2.0
												* (Math.min(enterPoint.getTolerance()+88,randomgen.nextInt(176))-88)
												+ enterPoint.getBearingNeeded()));
								FuturePlanes.pop();
							} else {
								timeTillAppears -= 1;
							}
	
						}
					});
		}
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

	/**
	 * Getter for the entry point of the FuturePlane
	 * @return EntryExitPoint that the FuturePlane is scheduled to come in on
	 */
	public EntryExitPoint getEntryPoint() {
		return enterPoint;
	}

}
