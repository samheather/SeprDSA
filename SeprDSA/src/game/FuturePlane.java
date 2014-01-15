package game;

import java.util.ArrayList;
import java.util.Random;

import main.SeprDSA;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

/** FuturePlane creates planes but does not draw them, which are then stored in the Planes class*/
public class FuturePlane {
	private int velocity;
	private float rotation;
	private String fnumber;
	private static int pixelsFromEdge = 100;
	static Random randomgen = new Random(System.currentTimeMillis());
	private EntryExitPoint enterPoint = SeprDSA.getEntryExitPoints().get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private EntryExitPoint exitPoint = SeprDSA.getEntryExitPoints().get(randomgen.nextInt(SeprDSA.getEntryExitPoints().size()));
	private static ArrayList <WayPoint> wayPointList = new ArrayList<WayPoint>();
	
	public FuturePlane(int delayTime) throws InterruptedException {
		fnumber = generateFlightNumber();
		//DELAYTIME COULD BE USED IN THE SCHEDULER SOMEHOW
		Thread.sleep(delayTime);
		new Plane(fnumber, wayPointList, enterPoint, exitPoint);
	}
	
	/** generateFlightNumber is used to create an eight digit string of letters and numbers (between 0-9)*/
	public static String generateFlightNumber() {
		String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = new String();
		for (int i = 0; i < 8; i++) {
			result = result + alphabet.charAt(randomgen.nextInt(alphabet.length()));
		}
		return result;
	}
	
	/** getFnumber is used to access the private variable fnumber */
	public String getFnumber() {
		return fnumber;
	}
	
	
	private static void main() {
		for (int i = 0; i < 10; i++){
			wayPointList.add( SeprDSA.getWayPoints().get( randomgen.nextInt( SeprDSA.getWayPoints().size() ) ) );
		}
	}

}
