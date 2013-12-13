package engine.graphics.drawing;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL31;

import engine.graphics.Drawables;

public class Texture {

	private final int internal;
	private final Vector size;

	public Texture(String text, Font font) {
		this(drawText(text, font));
	}

	private static BufferedImage drawText(String text, Font font) {
		double x = Math.round(Drawables.virtualDisplaySize().get(0));
		double y = Math.round(Drawables.virtualDisplaySize().get(1));
		BufferedImage image = new BufferedImage((int) x, (int) y,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setFont(font);

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

		double sizex = graphics.getFontMetrics().stringWidth(text);
		double sizey = graphics.getFontMetrics().getHeight();
		graphics.drawString(text, (int) ((x / 2.0) - (sizex / 2.0)),
				(int) ((y / 2.0) + (sizey / 2.0)));
		return image;
	}

	public Texture(String filePath) throws IOException {
		/*
		 * BufferedImage image = null; try { image = ImageIO.read(new
		 * File(filePath)); } catch (IOException e) { e.printStackTrace();
		 * System.exit(1); }
		 */
		this(ImageIO.read(new File(filePath)));
	}

	public Texture(BufferedImage image) {
		double xs = image.getWidth();
		double ys = image.getHeight();
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0,
				image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth()
				* image.getHeight() * 4);

		for (int y = image.getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = image.getRGB(x, y);
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		buffer.flip();

		internal = glGenTextures();
		glBindTexture(GL31.GL_TEXTURE_RECTANGLE, internal);
		glTexParameteri(GL31.GL_TEXTURE_RECTANGLE, GL_TEXTURE_WRAP_S,
				GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL31.GL_TEXTURE_RECTANGLE, GL_TEXTURE_WRAP_T,
				GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL31.GL_TEXTURE_RECTANGLE, GL_TEXTURE_MAG_FILTER,
				GL_LINEAR);
		// GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL31.GL_TEXTURE_RECTANGLE, GL_TEXTURE_MIN_FILTER,
				GL_LINEAR);
		// GL_LINEAR_MIPMAP_LINEAR);
		// glTexImage2D(GL31.GL_TEXTURE_RECTANGLE, 8, GL_RGBA8,
		// image.getWidth(),
		// image.getHeight(), 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, buffer);
		// GL30.glGenerateMipmap(GL31.GL_TEXTURE_RECTANGLE);
		glTexImage2D(GL31.GL_TEXTURE_RECTANGLE, 0, GL_RGBA8, image.getWidth(),
				image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

		size = new BasicVector(new double[] { xs, ys });

	}

	public void bind() {
		glBindTexture(GL31.GL_TEXTURE_RECTANGLE, internal);

	}

	public Vector size() {
		return size;
	}

}
