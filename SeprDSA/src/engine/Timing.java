package engine;

import java.util.PriorityQueue;

public class Timing {

	private static long timeSinceStart = 0;
	private static long startTime = System.currentTimeMillis();
	private static long timeSinceLastFrame = 0;
	private static long lastTimeSinceLastFrame = 0;
	private static PriorityQueue<Task> tasks = new PriorityQueue<Task>();

	public static void doIn(double milliseconds, Runnable does) {
		tasks.add(new Task(milliseconds + timeSinceStart, does));
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

		while(tasks.size() > 0) {
			if(tasks.peek().milliseconds < timeSinceStart) {
				tasks.poll().does.run();
			} else {
				break;
			}
		}
	}

}
