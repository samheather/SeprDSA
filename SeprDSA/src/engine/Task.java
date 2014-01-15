package engine;



class Task implements Comparable<Task> {
	public double milliseconds;
	public Runnable does;

	public Task(double milliseconds, Runnable does) {
		this.milliseconds = milliseconds;
		this.does = does;
	}

	@Override
	public int compareTo(Task o) {
		return (this.milliseconds < o.milliseconds) ? -1 : 1;
	}

}