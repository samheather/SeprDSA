package game;

import engine.*;
import org.la4j.vector.dense.*;

public class Map implements Drawable {
    public Map() {
    	Drawables.add(this);
    }
    
    private double x = 0;
    private double y = 0;
    
    public Sprite draw() {
    	return new Sprite(Images.map, new BasicVector(
				new double[] { x, y }), 1.0f, 0.0f);
    }
}
