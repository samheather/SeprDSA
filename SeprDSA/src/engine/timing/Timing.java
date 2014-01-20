package engine.timing;

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

	
	/**
	 * 
	 * @param n Number of times to repeat 'does'
	 * @param milliseconds Interval between iterations of 'does' in milliseconds
	 * @param does Subroutine that performs n times
	 * @return TaskCanceller class that is used internally to check for cancellations of tasks
	 */
	public static TaskCanceller doNTimes(int n, double milliseconds,
			NRunnable does) {
		TaskCanceller c = new TaskCanceller();
		tasks.add(new Task(milliseconds, milliseconds + timeSinceStart, n,
				does, c));
		return c;
	}

	/**
	 * 
	 * @param n Number of times to repeat 'does'
	 * @param milliseconds Time between each iteration of 'does' in milliseconds
	 * @param does Subroutine that performs n times
	 * @param c Class used to cancel the scheduled task
	 */
	public static void doNTimes(int n, double milliseconds, NRunnable does,
			TaskCanceller c) {
		tasks.add(new Task(milliseconds, milliseconds + timeSinceStart, n,
				does, c));
	}

	public static double timeSinceLastFrame() {
		return Math
				.abs((timeSinceLastFrame - lastTimeSinceLastFrame) / 1000000.0);
	}

	public static double timeSinceStart() {
		return timeSinceStart;
	}

	
	/**
	 *  (Needs to run every frame) Will schedule and perform tasks based on a PriorityQueue
	 */
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
