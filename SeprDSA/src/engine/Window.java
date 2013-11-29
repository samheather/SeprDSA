package engine;

public class Window extends DisplayMode {
	org.lwjgl.opengl.DisplayMode d;

	public void set() throws org.lwjgl.LWJGLException {
		org.lwjgl.opengl.Display.setDisplayMode(d);
	}

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