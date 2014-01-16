package engine;

import java.util.PriorityQueue;

public class Timing {

	private static long timeSinceStart = 0;
	private static long startTime = System.currentTimeMillis();
	private static long timeSinceLastFrame = 0;
	private static long lastTimeSinceLastFrame = 0;
	private static PriorityQueue<Task> tasks = new PriorityQueue<Task>();

	public interface NRunnable {
		public void run(int n);
	}

	public static TaskCanceller doIn(double milliseconds, NRunnable does) {
		return doNTimes(1, milliseconds, does);
	}

	public static TaskCanceller doNTimes(int n, double milliseconds,
			NRunnable does) {
		TaskCanceller c = new TaskCanceller();
		tasks.add(new Task(milliseconds, milliseconds + timeSinceStart, n,
				does, c));
		return c;
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
			if (tasks.peek().theTimeHasCome()) {
				next = tasks.poll();
				if (!next.isCancelled()) {
					next.doOnce();
					next.doAgainIfRequired();
				}
			} else {
				break;
			}
		}
	}

}
