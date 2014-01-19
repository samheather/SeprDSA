package engine.graphics.drawing.combinators;

import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;

public abstract class Affine extends UnaryCombinator {
	Affine(Drawing arg) {
		super(arg);
	}

	protected abstract void transform();

	public void render() {
		GL11.glPushMatrix();
		transform();
		super.render();
		GL11.glPopMatrix();
	}
}
