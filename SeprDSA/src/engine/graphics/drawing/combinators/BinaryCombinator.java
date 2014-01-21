package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;

/**
 * Represents a combinator of two drawings that returns another. Binary
 * combinators on drawings such as overlay should subclass this class.
 */
public abstract class BinaryCombinator extends Drawing {

	/**
	 * Combine arg1 and arg2 with the combinator this class represents.
	 * 
	 * @param arg1
	 * @param arg2
	 */
	public BinaryCombinator(Drawing arg1, Drawing arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		// TODO Auto-generated constructor stub
	}

	protected Drawing arg1;
	protected Drawing arg2;

	@Override
	public void render() {
		arg1.render();
		arg2.render();

	}

	public Drawing red(double amount) {
		arg1 = arg1.red(amount);
		arg2 = arg2.red(amount);
		return this;
	}

	public Drawing green(double amount) {
		arg1 = arg1.green(amount);
		arg2 = arg2.green(amount);
		return this;
	}

	public Drawing blue(double amount) {
		arg1 = arg1.blue(amount);
		arg2 = arg2.blue(amount);
		return this;
	}

	public Drawing alpha(double amount) {
		arg1 = arg1.alpha(amount);
		arg2 = arg2.alpha(amount);
		return this;
	}

	@Override
	public boolean hit(Vector pos) {
		return arg1.hit(pos) || arg2.hit(pos);
	}

}
