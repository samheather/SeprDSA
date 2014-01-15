package engine;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Timing {

	private static long timeSinceStart = 0;
	private static long startTime = System.currentTimeMillis();
	private static long timeSinceLastFrame = 0;
	private static long lastTimeSinceLastFrame = 0;
	private static SortedSet<Task> tasks = new TreeSet<Task>();

	public static void doIn(double milliseconds, Runnable does) {
		tasks.add(new Task(milliseconds + timeSinceStart, does));
	}

	public static double timeSinceLastFrame() {
		return Math
				.abs((timeSinceLastFrame - lastTimeSinceLastFrame) / 1000000.0);
	}

	public static double timeSinceStart() {
		return timeSinceStart;
	}

	public static void logic() {
		timeSinceStart = System.currentTimeMillis() - startTime;
		lastTimeSinceLastFrame = timeSinceLastFrame;
		timeSinceLastFrame = System.nanoTime();
		

		Task next = null;
		while (tasks.size() > 0) {
			next = tasks.first();
			if(next.milliseconds <= timeSinceStart) {
				next.does.run();
				tasks.remove(next);
			}
			else {
				break;
			}
		}
	}

}
