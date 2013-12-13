package engine.graphics;

import org.la4j.vector.*;
import org.lwjgl.opengl.GL11;

import engine.graphics.sprite.Texture;

public class Sprite extends Drawing {
	private final Texture texture;

	public Sprite(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
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
}
