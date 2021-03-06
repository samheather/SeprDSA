package engine.graphics;

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
 * Used as a container for static functions and variables, which are used to
 * implement the drawing of objects that implement Drawable, on the screen. It
 * also handles the rendering of GUI elements.
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

	/* The list of Drawable objects */
	public static List<Drawable> drawables;

	/* The list of Drawings that were generated from the Drawable objects */
	private static List<Drawing> drawings;

	/* The list of TWL UI widgets */
	private static List<GUI> widgets;

	/* LWJGL rendering object used to draw TWL UI widgets */
	private static LWJGLRenderer renderer;

	/*
	 * The TWL theme manager which will be used to apply a theme to each TWL UI
	 * element
	 */
	private static ThemeManager themeManager;

	/* Static initialisation of drawables list and widgets list */
	static {
		drawables = new ArrayList<Drawable>();
		widgets = new ArrayList<GUI>();
	}

	/**
	 * Adds d to the list of drawable objects. d will appear on screen the next
	 * time the screen is redrawn after this function is called.
	 * 
	 * @param d
	 *            The Drawable to be added
	 */
	public static void add(Drawable d) {
		drawables.add(d);
	}

	/**
	 * Removes d from the list of drawable objects. d will not appear on screen
	 * the next time the screen is redrawn after this function is called.
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
	 * Adds w to the list of TWL UI elements. w will appear on screen the next
	 * time the screen is redrawn after this function is called.
	 * 
	 * @param w
	 *            The widget to be added
	 */
	public static void add(Widget w) {
		GUI gui = new GUI(w, renderer);
		gui.applyTheme(themeManager);
		widgets.add(gui);
	}

	/* The width of the virtual display */
	private static int width;

	/* The height of the virtual display */
	private static int height;

	/**
	 * Performs initialisation required to draw to the screen, and either
	 * creates a window to display the game, or switches to fullscreen mode.
	 * 
	 * @param d
	 *            The display mode to be used to display the game
	 * @param width
	 *            The width of the virtual display
	 * @param height
	 *            The height of the virtual display
	 * @param canvas
	 *            Jpanels
	 * @param theme
	 *            A URL pointing to the xml file specifying the theme to be used
	 *            for TWL UI elements
	 * @throws IOException
	 *             This exception is thrown when an error occurs while trying to
	 *             read theme
	 */
	public static void initialise(DisplayMode d, int width, int height,
			URL theme) throws IOException {

		Drawables.width = width;
		Drawables.height = height;

		try {

			/* Set the display mode specified by d */
			d.set();
			Display.create();
			Display.setVSyncEnabled(false);
			Display.setResizable(false);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		/* An initial resize of the window is required */
		resize();

		/* We want smooth shading */
		GL11.glShadeModel(GL11.GL_SMOOTH);

		/* No depth buffer or lighting */
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		/*
		 * This needs to be enabled to use textures that are not a power of 2 in
		 * length or height
		 */
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);

		/* Sets blanking colour */
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		/* Setup alpha blending */
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		/* Set default rendering colour */
		GL11.glColor4d(1.0, 1.0, 1.0, 1.0);

		/* Try to initialise renderer and theme manager for twl */
		try {
			renderer = new LWJGLRenderer();
			themeManager = ThemeManager.createThemeManager(theme, renderer);
		} catch (LWJGLException e) {
			System.out.println("Failed to create LWJGL renderer.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Failed to create TWL theme manager.");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * @return The scaling factor used to scale the virtual display to the size
	 *         of the real display
	 */
	private static Vector virtualToRealScaleFactor() {

		/* Get display width and height */
		int dw = Display.getWidth();
		int dh = Display.getHeight();

		/*
		 * Use division with virtual display sizes to get appropriate scale
		 * factors
		 */
		double widthr = (double) (dw) / (double) (width);
		double heightr = (double) (dh) / (double) (height);

		/*
		 * We want a scale factor that is the same in both dimensions, so return
		 * the smaller sf of the 2 dimensions
		 */
		double sf = widthr <= heightr ? widthr : heightr;
		return new BasicVector(new double[] { sf, sf });
	}

	/**
	 * Converts real display coordinates (origin at top-left corner) to virtual
	 * display coordinates (origin at centre).
	 * 
	 * @param pos
	 *            The set of coordinates to be converted
	 * @return The converted set of coordinates
	 */
	public static Vector windowCoords(Vector pos) {

		/* Get display width and height */
		int dw = Display.getWidth();
		int dh = Display.getHeight();

		/* Move origin from top-left to centre */
		Vector ret = new BasicVector(new double[] { (pos.get(0)) - (dw / 2.0),
				(pos.get(1)) - (dh / 2.0) });

		/* Divide by real/virtual scale factor to get virtual coords */
		Vector sf = virtualToRealScaleFactor();
		return new BasicVector(new double[] { ret.get(0) / sf.get(0),
				ret.get(1) / sf.get(1) });
	}

	/**
	 * Handles the resizing of the window the game is being displayed in.
	 */
	private static void resize() {

		/* Select the modelview matrix and set it to identity */
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		/* Get real display width and height */
		int dw = Display.getWidth();
		int dh = Display.getHeight();

		/* Set the modelview matrix to a viewport: at (0, 0); size (dw, dh) */
		GL11.glViewport(0, 0, dw, dh);

		/* Select the projection matrix and set it to identity */
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		/*
		 * Set the projection matrix to an orthographic projection: origin at
		 * centre; size (dw, dh)
		 */
		GL11.glOrtho(-dw / 2, dw / 2, -dh / 2, dh / 2, 1, -1);

		/* Load modelview matrix */
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		/* Scale everything by the real/virtual scale factor */
		Vector sf = virtualToRealScaleFactor();
		GL11.glScaled(sf.get(0), sf.get(1), 0.0f);
	}

	/**
	 * @return The size of the virtual display
	 */
	public static Vector virtualDisplaySize() {
		return new BasicVector(new double[] { width, height });
	}

	/**
	 * Called to close the game window and shut down the graphics system.
	 */
	public static void deinitialise() {
		Display.destroy();
	}

	/**
	 * Performs collision detection between a 2d virtual display coordinate and
	 * Drawable objects.
	 * 
	 * @param pos
	 *            The coordinate to perform collision detection against
	 * @return A list of drawables that collide with pos
	 */
	public static List<Drawable> collisions(Vector pos) {
		List<Drawable> ret = new ArrayList<Drawable>();

		/* Iterate through all drawings */
		for (int i = 0; i < drawings.size(); i++) {
			/* If a collision is detected */
			if (drawings.get(i).hit(pos)) {
				/* Add to the list of results */
				ret.add(drawables.get(i));
			}
		}
		return ret;
	}

	/**
	 * Needs to be called each frame in order to perform actual graphical
	 * rendering.
	 */
	public static void logic() {
		if (Display.wasResized())
			resize();

		/* Clear the color buffer */
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

		/* Sort drawables by z order */
		Collections.sort(drawables);
		drawings = new ArrayList<Drawing>();

		/*
		 * Add each drawing to the list of drawings (in Z order). Then render
		 * the drawing
		 */
		for (int i = 0; i < drawables.size(); i++) {
			drawings.add(drawables.get(i).draw());
			drawings.get(i).render();
		}

		/*
		 * Save modelview matrix and opengl attributes in order to restore once
		 * TWL finishes rendering (So that TWL cant mess up our settings)
		 */
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();

		/* Switch to normal textures (TWL doesnt like rectangle textures) */
		GL11.glDisable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		/* Update and render TWL widgets */
		for (int i = 0; i < widgets.size(); i++)
			widgets.get(i).update();

		/*
		 * Switch back to rectangle textures and restore old modelview and
		 * attributes
		 */
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL31.GL_TEXTURE_RECTANGLE);
		GL11.glPopMatrix();
		GL11.glPopAttrib();

		/* Swap the buffers */
		Display.update();
	}
}
