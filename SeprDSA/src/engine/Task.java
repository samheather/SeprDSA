package engine;

/**
 * This class represents a task that is to be executed asynchronously by
 * engine.Timing
 * 
 * @author mjm540
 * 
 */
class Task implements Comparable<Task> {
	private double interval;
	private double scheduled;
	private int times;
	private Timing.NRunnable does;
	private TaskCanceller canceller;

	public Task(double interval, double scheduled, int times,
			Timing.NRunnable does, TaskCanceller canceller) {
		this.interval = interval;
		this.scheduled = scheduled;
		this.times = times;
		this.does = does;
		this.canceller = canceller;
	}

	@Override
	public int compareTo(Task o) {
		// TODO Auto-generated method stub
		return this.scheduled > o.scheduled ? 1 : -1;
	}

	public boolean isCancelled() {
		return canceller.isCancelled();
	}

	public void doAgainIfRequired() {
		if (times > 0) {
			Timing.doNTimes(times - 1, interval, does);
		}
	}

	public void doOnce() {
		does.run(times);
	}
	
	public boolean theTimeHasCome() {
		return scheduled < Timing.timeSinceStart();
	}

}