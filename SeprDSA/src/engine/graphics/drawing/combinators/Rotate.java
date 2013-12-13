package engine.graphics.drawing.combinators;

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


}
