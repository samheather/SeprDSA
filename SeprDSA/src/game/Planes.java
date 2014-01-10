package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Planes {
	private static int maxplanenumb = 10;
	static Random randomgen = new Random();
	private static double spawnThreshhold = 300 + randomgen.nextInt(600);
	private static double currTime = 0;
	private static List<FuturePlane> planes;
	static {
		planes = new ArrayList<FuturePlane>();
	}

	public static void add(FuturePlane p) {
		planes.add(p);
	}

	public static void remove(FuturePlane p) {
		planes.remove(p);
	}
	
	public static int size(){
		return planes.size();
	}
	
	public static void updateTimer(double time){
		currTime += time;
		if (currTime >= spawnThreshhold && planes.size() < maxplanenumb){
			FuturePlane newPlane = new FuturePlane();
			Planes.add(newPlane);
			spawnThreshhold = 300 + randomgen.nextInt(600);
			currTime = 0;
		}
	}
	
}
