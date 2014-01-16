package engine.graphics;

import java.awt.Canvas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.Display;
import org.lwjgl.LWJGLException;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

import engine.graphics.display.DisplayMode;
import engine.graphics.drawing.Drawing;

/**
 * This class is used as a container for static functions and variables, which
 * are used to implement the drawing of objects that implement Drawable, on the
 * screen. It also handles the rendering of GUI elements.
 * 
 * It uses the LWJGL library which provides bindings to the OpenGL API, which is
 * used to interact with graphics devices.
 * 
 * GUI features are provided by the TWL library.
 * 
 * @author mjm540
 * 
 */
public class Drawables {

	/** The list of Drawable objects */
	private static List<Drawable> drawables;

	/** The list of Drawings that were generated from the Drawable objects */
	private static List<Drawing> drawings;

	/** The list of TWL UI widgets */
	private static List<GUI> widgets;

	/** LWJGL rendering object used to draw TWL UI widgets */
	private static LWJGLRenderer renderer;

	/**
	 * The TWL theme manager which will be used to apply a theme to each TWL UI
	 * element
	 */
	private static ThemeManager themeManager;

	/** Static initialisation of drawables list and widgets list */
	static {
		drawables = new ArrayList<Drawable>();
		widgets = new ArrayList<GUI>();
	}

	/**
	 * This function adds d to the list of drawable objects. d will appear on
	 * screen the next time the screen is redrawn after this function is called.
	 * 
	 * @param d
	 *            The Drawable to be added
	 */
	public static void add(Drawable d) {
		drawables.add(d);
	}

	/**
	 * This function removes d from the list of drawable objects. d will not
	 * appear on screen the next time the screen is redrawn after this function
	 * is called.
	 * 
	 * @param d
	 *            The Drawable to be removed
	 */
	public static void remove(Drawable d) {
		for (int i = 0; i < drawables.size(); i++) {
			if (drawables.get(i) == d) {
				drawables.remove(i);
				if (drawings.size() > i)
					drawings.remove(i);
			}
		}
	}

	/**
	 * This function adds w to the list of TWL UI elements. w will appear on
	 * screen the next time the screne is redrawn after this function is called.
	 * 
	 * @param w
	 *            The widget to be added
	 */
	public static void add(Widget w) {
		GUI gui = new GUI(w, renderer);
		gui.applyTheme(themeManager);
		widgets.add(gui);
	}

	/** The width of the virtual display */
	private static int width;

	/** The height of the virtual display */
	private static int height;

	/**
	 * This function performs intialisation required to draw to the screen, and
	 * either creates a window to display the game, or switches to fullscreen
	 * mode.
	 * 
	 * @param d
	 *            The display mode to be used to display the game.
	 * @param width
	 *            The width of the virtual display
	 * @param height
	 *            The height of the virtual display
	 * @param canvas
	 *            Fucking jpanels
	 * @param theme
	 *            A URL pointing to the xml file specifying the theme to be used
	 *            for TWL UI elements.
	 * @throws IOException
	 *             This exception is thrown when an error occurs while trying to
	 *             read theme.
	 */
	public static void initialise(DisplayMode d, int width, int height,
			Canvas canvas, URL theme) throws IOException {

		Drawables.width = width;
		Drawables.height = height;

		

		try {
			/** jpanels i hate you */
			Display.setParent(canvas);

			/** Set the display mode specified by d */
			d.set();
			Display.create();
			Display.setVSyncEnabled(true);
			Display.setResizable(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		/** An initial resize of the window is required */
		resize();

		/** We want smooth shading */
		GL11.glShadeModel(GL11.GL_SMOOTH);

		/** No depth buffer or lighting */
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		/**
		 * This needs to be enabled to use textures that are not a power of 2 in
		 * length or height
		 */
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);
		
		/**
		 * Sets background colour.
		 */
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		/** Setup alpha blending */
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
		try {
			renderer = new LWJGLRenderer();
			themeManager = ThemeManager.createThemeManager(theme, renderer);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}

	public static Vector windowCoords(Vector pos) {
		int dw = Display.getWidth();
		int dh = Display.getHeight();
		double widthr = (double) (dw) / (double) (width);
		double heightr = (double) (dh) / (double) (height);
		if (heightr < widthr) {
			widthr = heightr;
		}
		double x = Display.getX();
		double y = Display.getY();
		Vector ret = new BasicVector(new double[] {
				(pos.get(0) - x) - (dw / 2.0), (pos.get(1) - y) - (dh / 2.0) });

		return ret.divide(widthr);
	}

	private static void resize() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		int dw = Display.getWidth();
		int dh = Display.getHeight();
		GL11.glViewport(0, 0, dw, dh);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		// GL11.glOrtho(-width / 2, width / 2, -height / 2, height / 2, 1, -1);
		GL11.glOrtho(-dw / 2, dw / 2, -dh / 2, dh / 2, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		double widthr = (double) (dw) / (double) (width);
		double heightr = (double) (dh) / (double) (height);
		if (heightr < widthr) {
			widthr = heightr;
		}
		GL11.glScaled(widthr, widthr, 0.0f);
	}

	public static Vector virtualDisplaySize() {
		return new BasicVector(new double[] { width, height });
	}

	public static void deinitialise() {
		Display.destroy();
	}

	public static List<Drawable> collisions(Vector pos) {
		List<Drawable> ret = new ArrayList<Drawable>();
		for (int i = 0; i < drawings.size(); i++) {
			if (drawings.get(i).hit(pos)) {
				ret.add(drawables.get(i));
			}
		}
		return ret;
	}

	public static void logic() {
		if (Display.wasResized()) {
			resize();
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Collections.sort(drawables);
		drawings = new ArrayList<Drawing>();
		for (int i = 0; i < drawables.size(); i++) {
			drawings.add(drawables.get(i).draw());
			drawings.get(i).render();
		}
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();
		GL11.glDisable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		for (int i = 0; i < widgets.size(); i++) {

			widgets.get(i).update();
		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
		Display.update();
	}
}
