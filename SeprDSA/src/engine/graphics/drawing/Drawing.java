package engine.graphics.drawing;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.drawing.combinators.*;

public abstract class Drawing {
	public abstract void render();

	public Drawing scale(Vector amount) {
		return new Scale(this, amount);
	}

	public Drawing scale(double amount) {
		return scale(new BasicVector(new double[] { amount, amount }));
	}

	public Drawing rotate(double amount) {
		return new Rotate(this, amount);
	}

	public Drawing translate(Vector amount) {
		return new Translate(this, amount);
	}

	public Drawing colour(Vector colour) {
		return new Colour(this, colour);
	}

	public Drawing red(double amount) {
		return colour(new BasicVector(new double[] { amount, 0.0, 0.0, 1.0 }));
	}

	public Drawing green(double amount) {
		return colour(new BasicVector(new double[] { 0.0, amount, 0.0, 1.0 }));
	}

	public Drawing blue(double amount) {
		return colour(new BasicVector(new double[] { 0.0, 0.0, amount, 1.0 }));
	}

	public Drawing alpha(double amount) {
		return colour(new BasicVector(new double[] { 0.0, 0.0, 0.0, amount }));
	}

	public Drawing overlay(Drawing top) {
		return new Overlay(this, top);
	}
}
