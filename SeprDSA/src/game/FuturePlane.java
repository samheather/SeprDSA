package game;

import java.util.Random;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class FuturePlane {
	private int velocity;
	private int passenger;
	private float rotation;
	private String fnumber;
	private Vector position;
	
	public FuturePlane() {
		velocity = (250 + (int)(Math.random() * (1105)));
		passenger = (20 + (int)(Math.random() * (834)));
		rotation = 349.0f;
		fnumber = generateFlightNumber();
		position = new BasicVector(new double[] { 50, 0, 30 });
		Plane p1 = new Plane(velocity, passenger, rotation, fnumber, position);
	}
	
	public static String generateFlightNumber() {
		String alphabet = new String("1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			result = result + alphabet.charAt(r.nextInt(alphabet.length()));
		}
		return result;
	}

}
