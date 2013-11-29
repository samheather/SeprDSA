package game;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;

import engine.*;
import org.la4j.vector.dense.*;

public class Plane implements Drawable, Keyboardable {
	public Plane() {
		Drawables.add(this);
		Input.addKeyboardable(this);
	}

	private double x = 0.0;
	private double y = 0.0;
	private boolean left = false; 
	private boolean right =  false;
	private boolean up = false;
	private boolean down = false;

	public Sprite draw() {
		if (left ) {
			x -= 10.0;
		}
		if (right) {
			x += 10.0;
		}
		if (up) {
			y += 10.0;
		}
		if (down) {
			y -= 10.0;
		}
		return new Sprite(Images.plane, new BasicVector(
				new double[] { x, y }), 1.0f, 0.0f);
	}

	@Override
	public void handleKeyboard(int key, boolean pressed) {
		
		if (key == Keyboard.KEY_LEFT || key == Keyboard.KEY_A ) {
			left = pressed;
		} else if (key == Keyboard.KEY_RIGHT || key == Keyboard.KEY_D) {
			right = pressed;
		} else if (key == Keyboard.KEY_UP || key == Keyboard.KEY_W) {
			up = pressed;
		} else if (key == Keyboard.KEY_DOWN || key == Keyboard.KEY_S) {
			down = pressed;
		}
	}

	@Override
	public List<Integer> keys() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(Keyboard.KEY_LEFT);
		result.add(Keyboard.KEY_RIGHT);
		result.add(Keyboard.KEY_UP);
		result.add(Keyboard.KEY_DOWN);
		result.add(Keyboard.KEY_W);
		result.add(Keyboard.KEY_A);
		result.add(Keyboard.KEY_S);
		result.add(Keyboard.KEY_D);
		return result;
	}
}
