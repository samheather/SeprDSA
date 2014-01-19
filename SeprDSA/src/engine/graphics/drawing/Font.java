package engine.graphics.drawing;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.lwjgl.opengl.GL11;

import engine.graphics.Drawables;

public class Font extends java.awt.Font {

	private static final long serialVersionUID = 1L;

	public Font(java.awt.Font font) {
		super(font);
		buildCache();
	}

	public Font(
			Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
		super(attributes);
		buildCache();
	}

	public Font(String name, int style, int size) {
		super(name, style, size);
		buildCache();
	}

	private int charOffsets[];
	private Texture cache;

	private Graphics2D setupGraphics(BufferedImage image) {
		Graphics2D graphics = image.createGraphics();
		graphics.setFont(this);

		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setRenderingHint(RenderingHints.KEY_DITHERING,
				RenderingHints.VALUE_DITHER_ENABLE);

		graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);

		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		return graphics;
	}

	private int descent;
	private int ascent;
	private int leading;

	private void buildCache() {
		charOffsets = new int[96];
		charOffsets[0] = 0;

		double x = Math.ceil(Drawables.virtualDisplaySize().get(0));
		double y = Math.ceil(Drawables.virtualDisplaySize().get(1));
		BufferedImage image = new BufferedImage((int) x, (int) y,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = setupGraphics(image);

		FontMetrics metrics = graphics.getFontMetrics();
		descent = metrics.getMaxDescent();
		ascent = metrics.getMaxAscent();
		leading = metrics.getLeading();

		for (int i = 1; i < 96; i++) {
			char c = (char) (i + 31);
			charOffsets[i] = charOffsets[i - 1] + metrics.charWidth(c);
		}

		image = new BufferedImage(charOffsets[95], descent + ascent,
				BufferedImage.TYPE_INT_ARGB);
		graphics = setupGraphics(image);

		for (int i = 0; i < 95; i++) {
			String c = new String(new char[] { (char) (i + 32) });
			graphics.drawString(c, charOffsets[i], ascent);
		}

		cache = new Texture(image);

	}

	public enum Alignment {
		CENTRED, LEFT
	}

	public void drawString(String str, Alignment a) {
		GL11.glPushMatrix();

		cache.bind();
		if (a == Alignment.CENTRED) {
			int length = 0;
			for (int i = 0; i < str.length(); i++) {
				length += charOffsets[str.charAt(i) - 31]
						- charOffsets[str.charAt(i) - 32];
			}
			GL11.glTranslated(-(length / 2), 0, 0);
		}
		for (int i = 0; i < str.length(); i++) {
			GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 32], 0);
			GL11.glVertex2d(0, -descent);

			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 31], 0);
			GL11.glVertex2d(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], -descent);

			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 32], descent + ascent);
			GL11.glVertex2d(0, ascent);

			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 31], descent + ascent);
			GL11.glVertex2d(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], ascent);

			GL11.glEnd();

			GL11.glTranslated(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], 0, 0);

		}
		GL11.glPopMatrix();
	}

}
