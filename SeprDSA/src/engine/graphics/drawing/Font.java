package engine.graphics.drawing;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.lwjgl.opengl.GL11;

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

	/**
	 * @return The ascent of this font
	 */
	public int ascent() {
		return ascent;
	}

	/**
	 * @return The descent of this font
	 */
	public int descent() {
		return descent;
	}

	/**
	 * @return The leading of this font
	 */
	public int leading() {
		return leading;
	}

	/* Build the render cache */
	private void buildCache() {

		/* There are 95 printable ascii characters */
		charOffsets = new int[96];
		charOffsets[0] = 0;

		/*
		 * We create this image to get the statistics of rendered characters for
		 * this font
		 */
		BufferedImage image = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = setupGraphics(image);

		/* Get descent, ascent and leading */
		FontMetrics metrics = graphics.getFontMetrics();
		descent = metrics.getMaxDescent();
		ascent = metrics.getMaxAscent();
		leading = metrics.getLeading();

		/* Set charOffsets by getting rendered character widths in pixels */
		for (int i = 1; i < 96; i++) {
			char c = (char) (i + 31);
			charOffsets[i] = charOffsets[i - 1] + metrics.charWidth(c);
		}

		/*
		 * Create the actual image that will be used for the cache. Use
		 * charOffsets[95] to get the position of the rightmost part of the last
		 * character, and thus the required width of the image
		 */
		image = new BufferedImage(charOffsets[95], descent + ascent,
				BufferedImage.TYPE_INT_ARGB);
		graphics = setupGraphics(image);

		/* Draw each printable ascii character onto the image */
		for (int i = 0; i < 95; i++) {
			String c = new String(new char[] { (char) (i + 32) });
			graphics.drawString(c, charOffsets[i], ascent);
		}

		/* Create the texture */
		cache = new Texture(image);
	}

	/**
	 * Represents a text alignment. If text is drawn at some position (x, y),
	 * and the alignment is CENTRED, the middle of the text will be at (x, y).
	 * If the alignment is LEFT, the leftmost part of the text will be at (x,
	 * y).
	 */
	public enum Alignment {
		CENTRED, LEFT
	}

	/**
	 * Renders str onto the centre of the screen with this font, and alignment
	 * a.
	 * 
	 * @param str
	 *            The string to render
	 * @param a
	 *            The alignment that str is to be drawn with
	 */
	public void drawString(String str, Alignment a) {

		/* Store the current viewmodel matrix because we are going to mess it up */
		GL11.glPushMatrix();

		/* We need to use the cache texture */
		cache.bind();

		/*
		 * If the alignment is centred, calculate how long the string will be,
		 * and translate us to where the leftmost part of the text is. If the
		 * alignment is left, we are already at the place where the text will
		 * start, so we do nothing.
		 */
		if (a == Alignment.CENTRED) {
			int length = 0;
			for (int i = 0; i < str.length(); i++) {
				length += charOffsets[str.charAt(i) - 31]
						- charOffsets[str.charAt(i) - 32];
			}
			GL11.glTranslated(-(length / 2), 0, 0);
		}

		/* For each character in str */
		for (int i = 0; i < str.length(); i++) {

			/*
			 * Draw the character, using charOffsets, descent and ascent to get
			 * its dimensions
			 */
			GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

			/* The bottom left vertex */
			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 32], 0);
			GL11.glVertex2d(0, -descent);

			/* The bottom right vertex */
			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 31], 0);
			GL11.glVertex2d(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], -descent);

			/* The top left vertex */
			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 32], descent + ascent);
			GL11.glVertex2d(0, ascent);

			/* The top right vertex */
			GL11.glTexCoord2d(charOffsets[str.charAt(i) - 31], descent + ascent);
			GL11.glVertex2d(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], ascent);

			GL11.glEnd();

			/* Shift to the position of the next character */
			GL11.glTranslated(
					charOffsets[str.charAt(i) - 31]
							- charOffsets[str.charAt(i) - 32], 0, 0);

		}

		/* Restore the old matrix */
		GL11.glPopMatrix();
	}

}
