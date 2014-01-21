package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;

/**
 * Represents a combinator of one drawing that returns another. Unary
 * combinators on drawings such as Colour should subclass this class.
 */
public abstract class UnaryCombinator extends Drawing {

	/**
	 * Transform arg with the combinator this class represents.
	 * 
	 * @param arg
	 */
	public UnaryCombinator(Drawing arg) {
		// TODO Auto-generated constructor stub
		this.arg = arg;
	}

	protected Drawing arg;

	@Override
	public void render() {
		arg.render();
	}

	public Drawing red(double amount) {
		arg = arg.red(amount);
		return this;
	}

	public Drawing green(double amount) {
		arg = arg.green(amount);
		return this;
	}

	public Drawing blue(double amount) {
		arg = arg.blue(amount);
		return this;
	}

	public Drawing alpha(double amount) {
		arg = arg.alpha(amount);
		return this;
	}

	@Override
	public boolean hit(Vector pos) {
		return arg.hit(pos);
	}
}
