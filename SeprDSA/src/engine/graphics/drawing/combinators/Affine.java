package engine.graphics.drawing.combinators;

import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;

/**
 * Represents an affine transformation that can be applied to a drawing.
 */
public abstract class Affine extends UnaryCombinator {

	/**
	 * Transform arg
	 * 
	 * @param arg
	 */
	Affine(Drawing arg) {
		super(arg);
	}

	/**
	 * Perform the transformation by modifying the state of the render engine.
	 */
	protected abstract void transform();

	public void render() {
		GL11.glPushMatrix();
		transform();
		super.render();
		GL11.glPopMatrix();
	}
}
