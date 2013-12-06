package engine.graphics.display;

public class Fullscreen extends DisplayMode {
	org.lwjgl.opengl.DisplayMode d;

	public void set() throws org.lwjgl.LWJGLException {
		org.lwjgl.opengl.Display.setDisplayMode(d);
		org.lwjgl.opengl.Display.setFullscreen(true);
	}

	public Fullscreen() {
		d = org.lwjgl.opengl.Display.getDisplayMode();
	}

	public int width() {
		return d.getWidth();
	}

	public int height() {
		return d.getHeight();
	}
}