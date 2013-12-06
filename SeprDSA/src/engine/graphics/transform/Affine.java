package engine.graphics.transform;

import engine.graphics.Drawing;

public abstract class Affine extends Drawing {
	Affine(Drawing arg) {
		this.arg = arg;
	}

	Drawing arg;

	public abstract void push();

	public abstract void pop();

	public void render() {
		push();
		arg.render();
		pop();
	}
}
