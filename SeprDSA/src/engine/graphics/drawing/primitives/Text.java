package engine.graphics.drawing.primitives;

import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font;
import engine.graphics.drawing.Font.Alignment;

public class Text extends Drawing {
	String text;
	Font font;
	Alignment alignment;

	public Text(String text, Font font, Alignment alignment) {
		this.text = text;
		this.font = font;
		this.alignment = alignment;
	}

	@Override
	public void render() {
		font.drawString(text, alignment);

	}

}
