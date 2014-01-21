package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.GL11;

import engine.graphics.drawing.Drawing;

/**
 * Changes the colour of a drawing.
 */
public class Colour extends UnaryCombinator {

	private final Vector colour;

	/**
	 * Sets the colour of arg to colour.
	 * 
	 * @param arg
	 * @param colour
	 */
	public Colour(Drawing arg, Vector colour) {
		// TODO Auto-generated constructor stub
		super(arg);
		this.colour = colour;
	}

	@Override
	public void render() {
		GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
		GL11.glColor4d(colour.get(0), colour.get(1), colour.get(2),
				colour.get(3));
		super.render();
		GL11.glPopAttrib();
		// TODO Auto-generated method stub

	}

	public Drawing colour(Vector colour) {
		return new Colour(arg, new BasicVector(new double[] { colour.get(0),
				colour.get(1), colour.get(2), colour.get(3) }));
	}

	public Drawing red(double amount) {
		return new Colour(arg, new BasicVector(new double[] { amount,
				colour.get(1), colour.get(2), colour.get(3) }));
	}

	public Drawing green(double amount) {
		return new Colour(arg, new BasicVector(new double[] { colour.get(0),
				amount, colour.get(2), colour.get(3) }));
	}

	public Drawing blue(double amount) {
		return new Colour(arg, new BasicVector(new double[] { colour.get(0),
				colour.get(1), amount, colour.get(3) }));
	}

	public Drawing alpha(double amount) {
		return new Colour(arg, new BasicVector(new double[] { colour.get(0),
				colour.get(1), colour.get(2), amount }));
	}

}
