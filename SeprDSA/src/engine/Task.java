package engine;

import java.util.Random;


class Task implements Comparable<Task> {
	public double milliseconds;
	public Runnable does;
	public long id;
	private static Random rnd = new Random();

	public Task(double milliseconds, Runnable does) {
		this.milliseconds = milliseconds;
		this.does = does;
		this.id = rnd.nextLong();
	}

	@Override
	public int compareTo(Task o) {
		return (this.milliseconds < o.milliseconds) ? -1 : 1;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("comparison");
		return this.id == ((Task)(obj)).id;
	}

}