package engine.graphics.text;

import org.newdawn.slick.TrueTypeFont;

public class Font {
	private TrueTypeFont internal;

	public Font(String name, int size, int settings) {
		internal = new TrueTypeFont(new java.awt.Font(name, settings, size),
				true);
	}

	public void render(String text) {
		internal.drawString(0, 0, text);
	}

}
