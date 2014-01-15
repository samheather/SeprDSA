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
		System.out.println("Added async task");
		System.out.println("nop");
	}

	public static void doNTimesIn(int n, double milliseconds, Runnable does) {
		for (int i = 0; i < n; i++) {
			doIn(i * milliseconds, does);
		}
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
		while (!tasks.isEmpty()) {
			next = tasks.first();
			System.out.println(next == null);
			if (next.milliseconds <= timeSinceStart) {
				System.out.println(tasks.remove(tasks.first()));
				next.does.run();
			} else {
				break;
			}
		}
	}

}
