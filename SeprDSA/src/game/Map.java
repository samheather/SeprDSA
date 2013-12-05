package game;

import engine.*;

import org.la4j.vector.dense.*;

public class Map implements Drawable {
    public Map() {
    	Drawables.add(this);
    }
    
    private double x = 0;
    private double y = 0;
    private	double z = -1;
    public Sprite draw() {
    	return new Sprite(Images.map, new BasicVector(
				new double[] { x, y }), 1.0f, 0.0f);
    }
    
    public double getZ() {
		return z;
	}
    
    @Override
    public int compareTo(Drawable o) {
    	// TODO Auto-generated method stub
    	return (int)(z - o.getZ());
    }
}
