package game;

import engine.graphics.*;

public class Map implements Drawable {
	public Map() {
		Drawables.add(this);
	}

	private double x = 0;
	private double y = 0;
	private double z = -1;

	public Drawing draw() {
		return new Sprite(Images.map);
	}

	public double getZ() {
		return z;
	}

	@Override
	public int compareTo(Drawable o) {
		// TODO Auto-generated method stub
		return (int) (z - o.getZ());
	}
}
