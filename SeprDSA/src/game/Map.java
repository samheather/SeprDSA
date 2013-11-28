package game;

import org.la4j.vector.dense.BasicVector;

import engine.Drawable;
import engine.Drawables;
import engine.Images;
import engine.Sprite;

public class Map implements Drawable {
    public Map() {
    	Drawables.add(this);
    }
    public Sprite draw() {
    	return new Sprite(Images.map, new BasicVector(new double[]{512.0, 320.0}), 1.0f, 0.0f); 	// {??}, zoom, rotate
    }
}
