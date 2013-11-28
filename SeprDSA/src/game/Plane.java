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

	private double x = 400.0;
	private double y = 300.0;

	public Sprite draw() {
		return new Sprite(Images.plane, new BasicVector(
				new double[] { x, y }), 1.0f, 0.0f);
	}

	@Override
	public void handleKeyboard(int key) {
		if (key == Keyboard.KEY_LEFT || key == Keyboard.KEY_A) {
			x -= 10.0;
		} else if (key == Keyboard.KEY_RIGHT || key == Keyboard.KEY_D) {
			x += 10.0;
		} else if (key == Keyboard.KEY_UP || key == Keyboard.KEY_W) {
			y -= 10.0;
		} else if (key == Keyboard.KEY_DOWN || key == Keyboard.KEY_S) {
			y += 10.0;
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
