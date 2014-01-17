package engine.timing;

public class TaskCanceller {
	public boolean cancel = false;

	public void cancel() {
		cancel = true;
	}

	public boolean isCancelled() {
		return cancel;
	}
}
