/**
 * 
 */
package game;

import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;

/**
 * @author sbh514
 * 
 */
public class BackgroundGradient implements Drawable {

	/**
	 * 
	 */
	public BackgroundGradient() {
		Drawables.add(this);
	}

	private double z = -1;

	/**
	 * What to draw when instances of Map are drawn.
	 */
	public Drawing draw() {
		return new Sprite(Images.backgroundGradient)
				.scale(new BasicVector(new double[] {
						Display.getWidth()
								/ Images.backgroundGradient.size().get(0), 1 }));
	}

	/**
	 * Z-value may be necessary depending on draw order.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Allow sorting of drawables to support ordering of objects for drawing
	 * (i.e. for layers - make sure instances of BackgroundGradient aren't drawn
	 * on top of anything.
	 */
	public int compareTo(Drawable o) {
		// Compare to other drawables to establsih draw order.
		// TODO Auto-generated method stub
		return (int) (z - o.getZ());
	}

}
