package engine.graphics.drawing.primitives;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;
/**
 * Placeholder for when you want nothing drawn, used in line drawing.
 *
 */
public class Identity extends Drawing {

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
