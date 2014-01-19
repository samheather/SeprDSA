package engine.graphics.display;

import org.lwjgl.LWJGLException;

/**
 * Represents display modes (i.e. windowed or fullscreen) for displaying the
 * game.
 */
public abstract class DisplayMode {

	/**
	 * Applies this display mode.
	 * 
	 * @throws LWJGLException
	 */
	public abstract void set() throws LWJGLException;

	/**
	 * @return Width of the on-screen game display in pixels when this mode is
	 *         applied
	 */
	public abstract int width();

	/**
	 * @return Height of the on-screen game display in pixels when this mode is
	 *         applied
	 */
	public abstract int height();
}