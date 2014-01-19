package engine.graphics.drawing;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.lwjgl.opengl.GL11;

import engine.graphics.Drawables;

/**
 * Represents a font, used for fast text drawing. Caches a rendering of every
 * printable ascii character on an OpenGL texture so that they can be drawn to
 * screen quickly.
 */
public class Font extends java.awt.Font {

	/* **** REMEMBER TO REGENERATE THIS **** */
	private static final long serialVersionUID = -5891137966186672170L;

	/*
	 * These constructors mimic the constructors of java.awt.Font. They call the
	 * superconstructor with their arguments and then call buildcache, which
	 * creates the render cache of the font
	 */
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

	/* The render cache */
	private Texture cache;

	/*
	 * This stores the x positions of each character in the cache texture.
	 * charOffset[i+1] will give the x position (in the cache texture) of the
	 * rightmost part of the character with the ascii code i + 32. charOffset
	 * [0] is 0.
	 */
	private int charOffsets[];

	/*
	 * This derives a graphics2d object from a bufferedimage object. Its main
	 * purpose is to set the current font of the graphics2d object to this, and
	 * set up rendering hints to give the highest quality rendering possible,
	 * since we do not need to render quickly.
	 */
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

	/* The descent, ascent and leading of the font in pixels. */
	private int descent;
	private int ascent;
	private int leading;

	public int ascent() {
		return ascent;
	}

	public int descent() {
		return descent;
	}

	public int leading() {
		return leading;
	}

	/* Build the render cache */
	private void buildCache() {

		/* There are 95 printable ascii characters */
		charOffsets = new int[96];
		charOffsets[0] = 0;

		BufferedImage image = new BufferedImage(1, 1,
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
