package engine.graphics.transform;

import org.lwjgl.opengl.GL11;

import engine.graphics.Drawing;

public class Rotate extends Affine {

	public Rotate(Drawing arg, double degrees) {
		super(arg);
		this.degrees = degrees;
		// TODO Auto-generated constructor stub
	}

	double degrees;

	@Override
	public void push() {
		GL11.glPushMatrix();
		GL11.glRotated(degrees, 0.0, 0.0, 1.0);
		// TODO Auto-generated method stub

	}

	@Override
	public void pop() {
		GL11.glPopMatrix();
		// TODO Auto-generated method stub

	}

}
