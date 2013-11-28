package engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;

import static org.lwjgl.opengl.GL11.*;
import org.la4j.vector.*;
import org.la4j.vector.dense.*;

public class Image {
	private int internal;
	private double xs = 0.0;
	private double ys = 0.0;

	public Image(String filePath) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		xs = image.getWidth();
		ys = image.getHeight();
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0,
				image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth()
				* image.getHeight() * 4);

		for (int y = 0; y < image.getHeight(); y++) {
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
				//GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL31.GL_TEXTURE_RECTANGLE, GL_TEXTURE_MIN_FILTER,
				GL_LINEAR);
				//GL_LINEAR_MIPMAP_LINEAR);
		//glTexImage2D(GL31.GL_TEXTURE_RECTANGLE, 8, GL_RGBA8, image.getWidth(),
		//		image.getHeight(), 0, GL12.GL_BGRA, GL_UNSIGNED_BYTE, buffer);
		//GL30.glGenerateMipmap(GL31.GL_TEXTURE_RECTANGLE);
		glTexImage2D(GL31.GL_TEXTURE_RECTANGLE, 0, GL_RGBA8, image.getWidth(),
				image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

	}
	public void bind() {
		glBindTexture(GL31.GL_TEXTURE_RECTANGLE, internal);

	}

	public Vector size() {
		return new BasicVector(new double[] { xs, ys });
	}
}
