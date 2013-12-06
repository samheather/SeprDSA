package engine.graphics.transform;

import engine.graphics.Drawing;
import org.la4j.vector.*;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.GL11;

public class Scale extends Affine {

	public Scale(Drawing arg, Vector value) {
		super(arg);
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public Scale(Drawing arg, double value) {
		super(arg);
		// TODO Auto-generated constructor stub
		this.value = new BasicVector(new double[] { value, value });
	}

	Vector value;

	@Override
	public void push() {
		GL11.glPushMatrix();
		GL11.glScaled(value.get(0), value.get(1), 0.0);
		// TODO Auto-generated method stub

	}

	@Override
	public void pop() {
		GL11.glPopMatrix();
		// TODO Auto-generated method stub

	}

}
