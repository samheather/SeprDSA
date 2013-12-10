package engine.graphics.transform;

import engine.graphics.Drawing;
import org.la4j.vector.*;
import org.lwjgl.opengl.GL11;

public class Scale extends Affine {

	public Scale(Drawing arg, Vector value) {
		super(arg);
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	private Vector value;

	@Override
	public void transform() {
		GL11.glScaled(value.get(0), value.get(1), 0.0);
		// TODO Auto-generated method stub

	}



}
