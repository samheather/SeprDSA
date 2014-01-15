package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;

public class Rotate extends Affine {

	public Rotate(Drawing arg, double degrees) {
		super(arg);
		this.degrees = degrees;
		// TODO Auto-generated constructor stub
	}

	private final double degrees;

	@Override
	protected void transform() {
		GL11.glRotated(degrees, 0.0, 0.0, 1.0);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hit(Vector pos) {
		double s = Math.sin(-degrees);
		double c = Math.cos(-degrees);
		double x = pos.get(0);
		double y = pos.get(1);
		return super.hit(new BasicVector(new double[] { x * c - y * s,
				x * s - y * c }));
	}

}
