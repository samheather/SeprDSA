package engine.graphics.display;

/**
 * A fullscreen display mode.
 */
public class Fullscreen extends DisplayMode {
	org.lwjgl.opengl.DisplayMode d;

	public void set() throws org.lwjgl.LWJGLException {
		org.lwjgl.opengl.Display.setDisplayMode(d);
		org.lwjgl.opengl.Display.setFullscreen(true);
	}

	/**
	 * Constructs a fullscreen display mode at the current desktop resolution.
	 */
	public Fullscreen() {
		d = org.lwjgl.opengl.Display.getDisplayMode();
	}

	/**
	 * Constructs a fullscreen display mode at the resolution (width, height).
	 * 
	 * @param width
	 * @param height
	 */
	public Fullscreen(int width, int height) {
		d = new org.lwjgl.opengl.DisplayMode(width, height);
	}

	public int width() {
		return d.getWidth();
	}

	public int height() {
		return d.getHeight();
	}
}