package game;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;

import engine.*;
import org.la4j.vector.dense.*;

public class Plane implements Drawable , Keyboardable {
    public Plane() {
    	Drawables.add(this);
    	Input.addKeyboardable(this);
    }
    private double x = 400.0;
    public Sprite draw() {
    	return new Sprite(Images.plane, new BasicVector(new double[]{x, 300.0}), 1.0f, 0.0f);
    }
	@Override
	public void handleKeyboard(int key) {
		if ( key == Keyboard.KEY_LEFT){
			x -= 10.0;
		}
		if ( key == Keyboard.KEY_RIGHT){
			x += 10.0;
		}
		
		
	}
	@Override
	public List<Integer> keys() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(Keyboard.KEY_LEFT);
		result.add(Keyboard.KEY_RIGHT);
		return result;
	}
}
