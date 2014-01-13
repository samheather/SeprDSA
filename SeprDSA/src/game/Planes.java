package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Planes {
	private static int maxplanenumb = 1;
	static Random randomgen = new Random();
	private static double spawnThreshhold = 3000 + randomgen.nextInt(6000);
	private static double currTime = 0;
	public static ArrayList<Plane> planes;
	static {
		planes = new ArrayList<Plane>();
	}

	public static void add(Plane p) {
		planes.add(p);
	}

	public static void remove(Plane p) {
		planes.remove(p);
	}
	
	public static int size(){
		return planes.size();
	}
	
	public static void updateTimer(double time) throws InterruptedException{
		currTime += time;
		if (currTime >= spawnThreshhold && planes.size() < maxplanenumb){
			new FuturePlane(randomgen.nextInt(60000));
			spawnThreshhold = 3000 + randomgen.nextInt(6000);
			currTime = 0;
		}
	}
	
}
