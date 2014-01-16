package engine;

public class TaskCanceller {
	private boolean cancel = false;

	public void cancel() {
		cancel = true;
	}

	public boolean isCancelled() {
		return cancel;
	}
}
