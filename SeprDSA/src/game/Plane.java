package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.la4j.vector.Vector;

import engine.graphics.*;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.Texture;
import engine.graphics.drawing.primitives.*;
import engine.input.Input;
import engine.input.Keyboardable;
import engine.physics.Physical;
import engine.physics.Physicals;

import org.la4j.vector.dense.*;

public class Plane implements Drawable, Keyboardable, Physical {
	
	private Random randomgen = new Random();
	private double x; // Should be pixel values for x,y
	private double y;
	private double z;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private Vector velocity = new BasicVector(new double[] { 0, 0, 0 });
	private float rotation;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private double radius = 50;
	private int randomImage = randomgen.nextInt(Images.planes.length);
	private int size = 60;
	private String number;
	private Text numbertext;
	private int score;
	private ArrayList <WayPoint> wayPointList;
	private EntryExitPoint exitPoint;
	private EntryExitPoint enterPoint;

	public Plane(String fnumber, ArrayList<WayPoint> pointList, EntryExitPoint startPoint, EntryExitPoint endPoint) {
		Drawables.add(this);
		Physicals.add(this);
		Planes.add(this);
		Input.addKeyboardable(this);
		number = fnumber;
		wayPointList = pointList;
		exitPoint = endPoint;
		enterPoint = startPoint;
		position = enterPoint.getPos();
		numbertext = new Text(fnumber, Fonts.planeFont, Alignment.CENTRED);
	}

	@Override
	public String toString() {
		return "Plane" + number;
	}
	
	public String getFNumber() {
		return number;
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

		return new
			 Sprite(Images.planes[randomImage])
			.scale(size / Images.planes[randomImage].size().get(0))
			.rotate(rotation)
			.overlay (
				 numbertext
				.red(1.0).blue(1.0).green(1.0).alpha(0.75)
				.translate(new BasicVector(new double[] {0, -40}))
			 )
			.translate(new BasicVector(new double[] { x, y }));

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
	
	public void setBearing(float newBearing) throws InterruptedException {
		float oldBearing = this.getBearing();
		for (int i = 1; i < Math.abs(newBearing - oldBearing); i++){
			if (newBearing > oldBearing){
				setVel(new BasicVector(new double[] {Math.sin(newBearing + i),Math.cos(newBearing + i),0}));
			} else{
				setVel(new BasicVector(new double[] {Math.sin(oldBearing + i),Math.cos(oldBearing + i),0}));
			}
			Thread.sleep(50);
		}
	};

	public void destroy() throws InterruptedException {
		for (int i = size; i > 0; i--) {
			size -= 1;
			Thread.sleep(5);
		}
		Planes.remove(this);
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
