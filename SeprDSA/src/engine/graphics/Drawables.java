package engine.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.Display;

import org.lwjgl.LWJGLException;

import engine.graphics.display.DisplayMode;

public class Drawables {
	private static List<Drawable> drawables;
	static {
		drawables = new ArrayList<Drawable>();
	}

	public static void add(Drawable d) {
		drawables.add(d);
	}

	public static void remove(Drawable d) {
		drawables.remove(d);
	}

	private static int width;
	private static int height;

	public static void initialise(DisplayMode d, int width, int height) {
		Drawables.width = width;
		Drawables.height = height;

		try {
			d.set();
			Display.create();
			Display.setVSyncEnabled(true);
			Display.setResizable(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		resize();
	}

	private static void resize() {
		int dw = Display.getWidth();
		int dh = Display.getHeight();
		GL11.glViewport(0, 0, dw, dh);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-width / 2, width / 2, -height / 2, height / 2, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		double widthr = (double) (dw) / (double) (width);
		double heightr = (double) (dh) / (double) (height);
		if (heightr < widthr) {
			widthr = heightr;
		}
		// GL11.glScaled(widthr, widthr, 0.0f);
	}

	public static void deinitialise() {
		Display.destroy();
	}

	public static void logic() {
		if (Display.wasResized()) {
			GL11.glPopAttrib();
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			resize();
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Collections.sort(drawables);
		for (int i = 0; i < drawables.size(); i++) {
			GL11.glPushMatrix();
			drawables.get(i).draw().render();

		}
		Display.update();
	}
}
