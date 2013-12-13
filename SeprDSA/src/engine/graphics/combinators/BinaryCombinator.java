package engine.graphics.combinators;

import engine.graphics.Drawing;

public class BinaryCombinator extends Drawing {

	public BinaryCombinator(Drawing arg1, Drawing arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		// TODO Auto-generated constructor stub
	}
	protected final Drawing arg1;
	protected final Drawing arg2;
	@Override
	public void render() {
		arg1.render();
		arg2.render();

	}

}
