package engine.graphics.drawing.primitives;

import org.la4j.vector.Vector;
import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Texture;

/**
 * 
 * The generic image loading class for rendering almost everything
 *
 */
public class Sprite extends Drawing {
	private final Texture texture;

	public Sprite(Texture texture) {
		this.texture = texture;
	}

	public void render() {
		Vector size = texture.size();
		texture.bind();
		double x = size.get(0);
		double y = size.get(1);
		GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(-x / 2.0, -y / 2.0);

		GL11.glTexCoord2d(x, 0);
		GL11.glVertex2d(x / 2.0, -y / 2.0);

		GL11.glTexCoord2d(0, y);
		GL11.glVertex2d(-x / 2.0, y / 2.0);

		GL11.glTexCoord2d(x, y);
		GL11.glVertex2d(x / 2.0, y / 2.0);

		GL11.glEnd();

	}

	@Override
	public boolean hit(Vector pos) {
		Vector size = texture.size();
		return (pos.get(0) > (-size.get(0) / 2))
				&& (pos.get(0) < (size.get(0) / 2))
				&& (pos.get(1) > (-size.get(1) / 2))
				&& (pos.get(1) < (size.get(1) / 2));
	}
}
