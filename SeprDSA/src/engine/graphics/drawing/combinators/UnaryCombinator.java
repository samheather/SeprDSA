package engine.graphics.drawing.combinators;

import org.la4j.vector.Vector;

import engine.graphics.drawing.Drawing;

public abstract class UnaryCombinator extends Drawing {

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
