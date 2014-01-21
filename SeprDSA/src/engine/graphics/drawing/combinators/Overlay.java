package engine.graphics.drawing.combinators;

import engine.graphics.drawing.Drawing;

/**
 * Overlays one drawing on top of another.
 */
public class Overlay extends BinaryCombinator {

	/**
	 * Overlays top over bottom.
	 * 
	 * @param bottom
	 * @param top
	 */
	public Overlay(Drawing bottom, Drawing top) {
		// TODO Auto-generated constructor stub
		super(bottom, top);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

}
