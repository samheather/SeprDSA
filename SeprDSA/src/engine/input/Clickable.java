package engine.input;

import org.la4j.vector.Vector;

/**
 * Implemented by objects which need to receive mouse input.
 */
public interface Clickable {

	/**
	 * Called when the user clicks one of the mouse buttons down.
	 * 
	 * @param button
	 *            The button that was clicked (0 is the left mouse button, 1 is
	 *            the right mouse button)
	 * @param pos
	 *            The position of the mouse when the button was clicked
	 */
	void clickDown(int button, Vector pos);

	/**
	 * Called when a button which was previously clicked down is released.
	 * 
	 * @param button
	 *            The button that was released.
	 */
	void clickUp(int button);

	/**
	 * Called after a clickUp event when a clickDown event is given to another
	 * object
	 */
	void clickAway();

	/**
	 * Called multiple times between a clickDown event and a clickUp event when
	 * the mouse is moved with the button clicked down
	 * 
	 * @param newPos
	 */
	void move(Vector newPos);

}
