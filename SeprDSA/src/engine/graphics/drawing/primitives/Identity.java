package engine.graphics.drawing.primitives;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;

/**
 * The identity of the monoid formed by the binary operation Overlay and the set
 * of all drawings.
 */
public class Identity extends Drawing {

	/**
	 * Constructs the identity drawing.
	 */
	public Identity() {
	}

	@Override
	public void render() {
	}

	@Override
	public boolean hit(Vector pos) {
		return false;
	}

}
