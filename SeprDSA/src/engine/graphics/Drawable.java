package engine.graphics;

import java.lang.Comparable;

import engine.graphics.drawing.Drawing;

/**
 * Interface for drawables (objects that are drawable).
 * <p>
 * Objects that are drawable implement this interface. They have a method that
 * is called when we draw them, and a method that returns their Z value (to make
 * sure we are drawing objects in the correct order).
 * 
 * @author sbh514
 * 
 */

public interface Drawable extends Comparable<Drawable> {
	/**
	 * Method called on an Object when we want to draw it.
	 * 
	 * @return A Drawing class which can incorporate many transformations
	 */
	Drawing draw();

	/**
	 * Get Z value for drawing objects in correct order.
	 * 
	 * @return a double used for the painter's algorithm for zIndexing
	 */
	public double getZ();

	/**
	 * For comparing drawable objects, so they can be sorted by drawing order.
	 */
	@Override
	public int compareTo(Drawable o);

}
