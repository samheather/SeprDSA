package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.la4j.vector.Vector;

import engine.*;
import org.la4j.vector.dense.*;

public class Plane implements Drawable, Keyboardable, Physical {
	public Plane() {
		Drawables.add(this);
		Physicals.add(this);
		Input.addKeyboardable(this);
	}

	private double x = 0.0; // Should be pixel values for x,y
	private double y = 0.0;
	private double z = 10.0;
	private float rotation = 0;
	private boolean left = false; 
	private boolean right =  false;
	private boolean up = false;
	private boolean down = false;
	private double speed = 100;
	private double radius = 200;
	private Image[] planeImages = {Images.plane1,Images.plane2};
	private int randomImage = new Random().nextInt(planeImages.length);

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
		return new Sprite(planeImages[randomImage], new BasicVector(
				new double[] { x, y }), 1.0f, rotation);
	}
	
	public Vector getPos(){
		return new BasicVector(new double[] {x, y, z}); 
	}
	
	public void setPos(Vector newPos){
		x = newPos.get(0);
		y = newPos.get(1);
		z = newPos.get(2);
	}
	
	public Vector getVel(){
		/*TODO
		 * Implement z stuff, need more attributes.
		*/
		return new BasicVector(new double[] {Math.cos(rotation), Math.sin(rotation), 0}).multiply(speed); 
	}
	
	public void setVel(Vector newVel){
		rotation = (float) Math.atan(y/x);
		speed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public boolean isCollidingPos(Vector checkPos){
		return Math.sqrt(Math.pow(x-checkPos.get(0), 2) + Math.pow(y-checkPos.get(1), 2)) < radius;
	}
	
	public boolean isCollidingObj(Physical checkObj){ // This function isn't actually used anywhere.
		return Math.sqrt(Math.pow(x-checkObj.getPos().get(0), 2) + Math.pow(y-checkObj.getPos().get(1), 2)) < radius;
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
