package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;
import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;

/**
 * Translates a drawing.
 */
public class Translate extends Affine {

	/**
	 * Translates arg by value.
	 * 
	 * @param arg
	 * @param value
	 */
	public Translate(Drawing arg, Vector value) {
		super(arg);
		this.value = value;
	}

	private final Vector value;

	@Override
	protected void transform() {
		GL11.glTranslated(value.get(0), value.get(1), 0.0);
	}

	@Override
	public boolean hit(Vector pos) {
		return super.hit(pos.subtract(value.slice(0, 2)));
	}

}
