package engine.graphics;

import org.la4j.vector.Vector;
import org.lwjgl.opengl.GL11;

public class Colour extends Drawing {

	private Drawing arg;
	private Vector colour;

	public Colour(Drawing arg, Vector colour) {
		// TODO Auto-generated constructor stub
		this.arg = arg;
		this.colour = colour;
	}

	@Override
	public void render() {
		GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
		GL11.glColor4d(colour.get(0), colour.get(1), colour.get(2),
				colour.get(3));
		arg.render();
		GL11.glPopAttrib();
		// TODO Auto-generated method stub

	}

}
