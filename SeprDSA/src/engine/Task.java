package engine;

/**
 * This class represents a task that is to be executed asynchronously by
 * engine.Timing
 * 
 * @author mjm540
 * 
 */
class Task implements Comparable<Task> {
	public double interval;
	public double scheduled;
	public int times;
	public Timing.NRunnable does;

	public Task(double interval, double scheduled, int times,
			Timing.NRunnable does) {
		this.interval = interval;
		this.scheduled = scheduled;
		this.times = times;
		this.does = does;
	}

	@Override
	public int compareTo(Task o) {
		// TODO Auto-generated method stub
		return this.scheduled > o.scheduled ? 1 : -1;
	}

}