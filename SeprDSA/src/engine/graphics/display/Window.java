package engine.graphics.display;

/**
 * A windowed display mode.
 */
public class Window extends DisplayMode {
	org.lwjgl.opengl.DisplayMode d;

	public void set() throws org.lwjgl.LWJGLException {
		org.lwjgl.opengl.Display.setDisplayMode(d);
	}

	/**
	 * @param width
	 *            Width of the window
	 * @param height
	 *            Height of the window
	 */
	public Window(int width, int height) {
		d = new org.lwjgl.opengl.DisplayMode(width, height);
	}

	public int width() {
		return d.getWidth();
	}

	public int height() {
		return d.getHeight();
	}
}