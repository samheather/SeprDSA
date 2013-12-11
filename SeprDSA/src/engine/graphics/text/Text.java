package engine.graphics.text;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

public class Text extends engine.graphics.Drawing {

	public Text(String text, engine.graphics.text.Font font) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.font = font;
	}

	private String text;
	private engine.graphics.text.Font font;

	@Override
	public void render() {
		// TODO Auto-generated method stub
		GL11.glDisable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		font.render(text);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);

	}

}
