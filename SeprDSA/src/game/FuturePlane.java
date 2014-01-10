package game;

import java.util.Random;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

public class FuturePlane {
	private int velocity;
	private int passenger;
	private float rotation;
	private String fnumber;
	private Vector position;
	private String planename = "p" + (Planes.size()+1);
	private static int maxplanenumb = 1;
	private static int pixelsFromEdge = 100;
	Random randomgen = new Random(System.currentTimeMillis());
	
	public FuturePlane() {
		velocity = (250 + randomgen.nextInt(1105));
		passenger = (20 + randomgen.nextInt(834));
		rotation = 349.0f;
		fnumber = generateFlightNumber();
		position = new BasicVector(new double[] {
				(randomgen.nextDouble() - 0.5)
				* (Display.getWidth() - pixelsFromEdge),
		(randomgen.nextDouble() - 0.5)
				* (Display.getHeight() - pixelsFromEdge),
		randomgen.nextDouble() * 20 });
		Plane planename = new Plane(velocity, passenger, rotation, fnumber, position);
	}
	
	public static void CreatePlanes() {
		while (Planes.size() < maxplanenumb)
		{
				FuturePlane fp = new FuturePlane();
		}		
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
