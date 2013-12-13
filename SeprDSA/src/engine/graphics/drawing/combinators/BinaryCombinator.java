package engine.graphics.drawing.combinators;

import engine.graphics.drawing.Drawing;

public class BinaryCombinator extends Drawing {

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

}
