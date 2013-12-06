package engine.graphics.text;

import engine.graphics.Drawing;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Text extends Drawing {

	public Text(String text, Font font, int red, int green, int blue, int alpha) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.font = font;
		c = new Color(red, green, blue, alpha);
	}

	String text;
	Font font;
	Color c;

	@Override
	public void render() {
		// TODO Auto-generated method stub
		GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
		font.internal.drawString(0, 0, text, c);
		GL11.glPopAttrib();

	}

}
