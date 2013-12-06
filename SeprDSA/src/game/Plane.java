package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.la4j.vector.Vector;

import engine.graphics.*;
import engine.graphics.image.*;
import engine.graphics.transform.*;
import engine.input.Input;
import engine.input.Keyboardable;
import engine.physics.Physical;
import engine.physics.Physicals;

import org.la4j.vector.dense.*;

public class Plane implements Drawable, Keyboardable, Physical {

	private double x; // Should be pixel values for x,y
	private double y;
	private double z;
	private Vector velocity = new BasicVector(new double[] { 0, 0, 0 });
	private float rotation;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private double radius = 50;
	private int randomImage = new Random().nextInt(Images.planes.length);
	private int size = 50;
	private String number;

	public Plane(Vector pos, float rotate, String flightNumber) {
		rotation = rotate;
		x = pos.get(0);
		y = pos.get(1);
		z = pos.get(2);
		number = flightNumber;
		Drawables.add(this);
		Physicals.add(this);
		// Planes.add(this);
		Input.addKeyboardable(this);
	}

	@Override
	public String toString() {
		return "Plane" + number;
	}

	public Drawing draw() {
		if (left) {
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

		return new Translate(new Scale(new Rotate(new Sprite(
				Images.planes[randomImage]), rotation), size
				/ Images.planes[randomImage].size().get(0)), new BasicVector(
				new double[] { x, y }));

	}

	public Vector getPos() {
		return new BasicVector(new double[] { x, y, z });
	}

	public void setPos(Vector newPos) {
		x = newPos.get(0);
		y = newPos.get(1);
		z = newPos.get(2);
	}

	public Vector getVel() {
		/*
		 * TODO Implement z stuff, need more attributes.
		 */
		return velocity;
	}

	public void setVel(Vector newVel) {
		rotation = (float) Math.atan(newVel.get(1) / newVel.get(0));
		velocity = newVel;
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(x - checkPos.get(0), 2)
				+ Math.pow(y - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return Math.sqrt(Math.pow(x - checkObj.getPos().get(0), 2)
				+ Math.pow(y - checkObj.getPos().get(1), 2)) < radius;
	}

	public float getBearing() {
		return rotation;
	};

	public void destroy() {
		for (int i = size; i > 0; i--) {
			size -= 1;
		}
		Input.removeKeyboardable(this);
		Physicals.remove(this);
		Drawables.remove(this);
	}

	@Override
	public void handleKeyboard(int key, boolean pressed) {

		if (key == Keyboard.KEY_LEFT || key == Keyboard.KEY_A) {
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

	public double getZ() {
		return z;
	}

	public int compareTo(Drawable o) {
		return (int) (this.getZ() - o.getZ());
	}
}
