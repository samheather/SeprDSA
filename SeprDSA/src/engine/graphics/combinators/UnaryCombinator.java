package engine.graphics.combinators;

import engine.graphics.Drawing;

public abstract class UnaryCombinator extends Drawing {

	public UnaryCombinator(Drawing arg) {
		// TODO Auto-generated constructor stub
		this.arg = arg;
	}
	
	protected final Drawing arg;

	@Override
	public void render() {
		arg.render();
	}

}
