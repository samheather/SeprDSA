package engine.graphics.drawing.primitives;

import org.la4j.vector.Vector;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

import engine.graphics.drawing.Drawing;

/**
 * Will draw a line based on 2 Vectors
 */
public class Line extends Drawing {

	/**
	 * 
	 * @param start Vector for the start of the line
	 * @param end Vector for end of the line
	 */
	public Line(Vector start, Vector end) {
		this.start = start;
		this.end = end;
	}

	Vector start;
	Vector end;
	
	/**
	 * Will draw a line between the 2 Vectors given
	 */
	@Override
	public void render() {
		GL11.glDisable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2d(start.get(0), start.get(1));
		GL11.glVertex2d(end.get(0), end.get(1));
		GL11.glEnd();
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);
	}

	@Override
	public boolean hit(Vector pos) {
		return false;
	}

}
