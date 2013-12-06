package engine.graphics.text;

import org.newdawn.slick.TrueTypeFont;

public class Font {
	TrueTypeFont internal;

	public Font(String name, int size, int settings) {
		internal = new TrueTypeFont(new java.awt.Font(name, settings, size),
				true);
	}

}
