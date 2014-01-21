package engine.graphics.drawing.primitives;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font;
import engine.graphics.drawing.Font.Alignment;

/**
 * 
 * Creates a renderable object that displays text
 *
 */
public class Text extends Drawing {
	String text;
	Font font;
	Alignment alignment;
	/**
	 * 
	 * @param text Text you wish to display
	 * @param font The font which you wish the text to display with
	 * @param alignment The side you want 
	 */
	public Text(String text, Font font, Alignment alignment) {
		this.text = text;
		this.font = font;
		this.alignment = alignment;
	}

	@Override
	public void render() {
		font.drawString(text, alignment);

	}

	@Override
	public boolean hit(Vector pos) {
		return false;
	}

}
