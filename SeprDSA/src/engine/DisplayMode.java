package engine;

import org.lwjgl.LWJGLException;

public abstract class DisplayMode {
	public abstract void set() throws LWJGLException;
	public abstract int width();
	public abstract int height();
}