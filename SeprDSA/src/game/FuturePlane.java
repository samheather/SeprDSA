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
	private String fnumber;
	private static int pixelsFromEdge = 100;
	static Random randomgen = new Random(System.currentTimeMillis());
	private EntryExitPoint enterPoint = SeprDSA.getEntryExitPoints().get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private EntryExitPoint exitPoint = SeprDSA.getEntryExitPoints().get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private static ArrayList <WayPoint> wayPointList;
	
	public FuturePlane(int delayTime) throws InterruptedException {
		fnumber = generateFlightNumber();
		wayPointList = new ArrayList<WayPoint>();
		int lastUsed = -1;
		for (int i = 0; i < 10; i++){
			int newNumber = getWayPointNumber(lastUsed);
			wayPointList.add( SeprDSA.getWayPoints().get( newNumber ) );
			lastUsed = newNumber;
		}
		System.out.println(wayPointList.toString());
		double localTime = 0;
		//DELAYTIME COULD BE USED IN THE SCHEDULER SOMEHOW
		while (localTime < delayTime) {localTime += SeprDSA.timer;}
		Plane p = new Plane(fnumber, wayPointList, enterPoint, exitPoint);
		p.setBearing(randomgen.nextFloat()*360);
	}
	
	
	public static String generateFlightNumber() {
		String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = new String();
		for (int i = 0; i < 8; i++) {
			result = result + alphabet.charAt(randomgen.nextInt(alphabet.length()));
		}
		return result;
	}
	
	public String getFnumber() {
		return fnumber;
	}
	
private static int getWayPointNumber(int lastUsed){
	int potentialNewNumber = randomgen.nextInt(SeprDSA.getWayPoints().size());
	if (lastUsed == potentialNewNumber){
		return getWayPointNumber(lastUsed);
	} else {
		return potentialNewNumber;
	}
}

}
