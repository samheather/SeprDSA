package engine;

public class TaskCanceller {
	public class Holder {
		Holder() {
			value = false;
		}
	    public boolean value;
	}
	public Holder cancel = new Holder();

	public void cancel() {
		cancel.value = true;
	}

	public boolean isCancelled() {
		return cancel.value;
	}
}
