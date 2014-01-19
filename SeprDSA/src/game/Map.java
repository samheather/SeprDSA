package game;

import org.la4j.vector.dense.BasicVector;
import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;

/**
 * Class for the map image to be drawn.
 * 
 * @author sbh514
 */

public class Map implements Drawable {
	/**
	 * Constructor - add this class to Drawables so it's drawn.
	 */
	public Map() {
		Drawables.add(this);
	}

	/**
	 * Necessary for layering of drawable objects.
	 */
	private double z = -1;

	/**
	 * What to draw when instances of Map are drawn.
	 */
	public Drawing draw() {
		return new Sprite(Images.map);
	}

	/**
	 * Z-value may be necessary depending on draw order.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Allow sorting of drawables to support ordering of objects for drawing
	 * (i.e. for layers - make sure instances of Map aren't drawn on top of
	 * planes).
	 */
	@Override
	public int compareTo(Drawable o) {
		// Compare to other drawables to establsih draw order.
		// TODO Auto-generated method stub
		return (int) (z - o.getZ());
	}
}
