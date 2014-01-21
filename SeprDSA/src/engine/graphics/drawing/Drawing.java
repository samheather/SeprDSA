package engine.graphics.drawing;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.drawing.combinators.Colour;
import engine.graphics.drawing.combinators.Overlay;
import engine.graphics.drawing.combinators.Rotate;
import engine.graphics.drawing.combinators.Scale;
import engine.graphics.drawing.combinators.Translate;

/**
 * Drawings are used by drawable objects to specify how they should be rendered
 * on screen. There are some primitive drawings
 * (engine.graphics.drawing.primitives.*) and some combinators
 * (engine.graphics.drawing.combinators.*) which can be used to modify or
 * combine drawings. This class contains convenience methods for using the
 * combinators and you probably shouldn't be using any of the combinators
 * directly, if you are just trying to draw things.
 */

public abstract class Drawing {

	/**
	 * Render this drawing onto the screen.
	 */
	public abstract void render();

	/**
	 * Scales a drawing by a Vector amount.
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing scale(Vector amount) {
		return new Scale(this, amount);
	}

	/**
	 * Scales a drawing by a scalar amount.
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing scale(double amount) {
		return scale(new BasicVector(new double[] { amount, amount }));
	}

	/**
	 * Rotates a drawing anti-clockwise by amount in degrees.
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing rotate(double amount) {
		return new Rotate(this, amount);
	}

	/**
	 * Translates a drawing by vector amount.
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing translate(Vector amount) {
		return new Translate(this, amount);
	}

	/**
	 * Changes the colour of a drawing by an RGBA Vector.
	 * 
	 * @param colour
	 * @return
	 */
	public Drawing colour(Vector colour) {
		return new Colour(this, colour);
	}

	/**
	 * Sets the red amount of a drawing (range: 0<=x<=1).
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing red(double amount) {
		return colour(new BasicVector(new double[] { amount, 0.0, 0.0, 1.0 }));
	}

	/**
	 * Sets the green amount of a drawing (range: 0<=x<=1).
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing green(double amount) {
		return colour(new BasicVector(new double[] { 0.0, amount, 0.0, 1.0 }));
	}

	/**
	 * Sets the blue amount of a drawing (range: 0<=x<=1).
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing blue(double amount) {
		return colour(new BasicVector(new double[] { 0.0, 0.0, amount, 1.0 }));
	}

	/**
	 * Sets the alpha component (transparency, 1 = opaque, 0 = transparent) of a
	 * drawing.
	 * 
	 * @param amount
	 * @return
	 */
	public Drawing alpha(double amount) {
		return colour(new BasicVector(new double[] { 0.0, 0.0, 0.0, amount }));
	}

	/**
	 * Overlays a drawing over the top of another drawing.
	 * 
	 * @param top
	 * @return
	 */
	public Drawing overlay(Drawing top) {
		return new Overlay(this, top);
	}

	/**
	 * Tells you whether Vector pos collides with this drawing.
	 * 
	 * @param pos
	 * @return
	 */
	public abstract boolean hit(Vector pos);
}
