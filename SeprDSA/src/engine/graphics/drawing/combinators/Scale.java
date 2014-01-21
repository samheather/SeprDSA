package engine.graphics.drawing.combinators;

import engine.graphics.drawing.Drawing;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.GL11;

/**
 * Scales a drawing from the origin.
 */
public class Scale extends Affine {

	/**
	 * Scale arg by value from the origin.
	 * 
	 * @param arg
	 * @param value
	 */
	public Scale(Drawing arg, Vector value) {
		super(arg);
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	private final Vector value;

	@Override
	protected void transform() {
		GL11.glScaled(value.get(0), value.get(1), 0.0);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hit(Vector pos) {
		return super.hit(new BasicVector(new double[] {
				pos.get(0) / value.get(0), pos.get(1) / value.get(1) }));
	}

}
