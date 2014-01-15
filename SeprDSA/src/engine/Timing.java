package engine;

import java.util.PriorityQueue;

public class Timing {

	private static long timeSinceStart = 0;
	private static long startTime = System.currentTimeMillis();
	private static long timeSinceLastFrame = 0;
	private static long lastTimeSinceLastFrame = 0;
	private static PriorityQueue<Task> tasks = new PriorityQueue<Task>();
	
	public interface NRunnable {
		public void run (int n);
	}

	public static void doIn(double milliseconds, NRunnable does) {
		doNTimes(1, milliseconds, does);
	}

	public static void doNTimes(int n, double milliseconds, NRunnable does) {
		tasks.add(new Task(milliseconds, milliseconds + timeSinceStart, n, does));
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
		while(tasks.size() > 0) {
			if(tasks.peek().scheduled < timeSinceStart) {
				next = tasks.poll();
				next.does.run(next.times);
				if(next.times > 0) {
					doNTimes(next.times - 1, next.interval, next.does);
				}
			} else {
				break;
			}
		}
	}

}
