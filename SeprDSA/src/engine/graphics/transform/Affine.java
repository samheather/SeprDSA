package engine.graphics.transform;

import org.lwjgl.opengl.GL11;

import engine.graphics.Drawing;

public abstract class Affine extends Drawing {
	Affine(Drawing arg) {
		this.arg = arg;
	}

	private Drawing arg;

	public abstract void transform();

	public void render() {
		GL11.glPushMatrix();
		transform();
		arg.render();
		GL11.glPopMatrix();
	}
}
