package engine.input;

/**
 * Implemented by objects which need to handle input from the mouse scroll
 * wheel.
 */
public interface Scrollable {

	/**
	 * Called when the scroll wheel is moved.
	 * 
	 * @param amount
	 *            The number of clicks the scroll wheel was moved by. A positive
	 *            number means it was pushed up, a negative number means it was
	 *            pushed down
	 */
	public void scroll(int amount);
}
