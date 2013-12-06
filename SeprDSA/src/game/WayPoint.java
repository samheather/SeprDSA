package game;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.*;
import engine.graphics.image.Sprite;
import engine.graphics.transform.*;
import engine.physics.Physical;
import engine.physics.Physicals;

public class WayPoint implements Drawable, Physical {

	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private int radius = 50;

	public WayPoint(Vector pos) {
		position = pos;
		Drawables.add(this);
		Physicals.add(this);
	}

	public Drawing draw() {
		return new Translate(new Sprite(Images.waypoint), position);
	}

	public Vector getPos() {
		return position;
	}

	public void setPos(Vector newPos) {
		position = newPos;

	}

	public Vector getVel() {
		return new BasicVector(new double[] { 0, 0, 0 });
	}

	public void setVel(Vector newVel) {
	}

	public boolean isCollidingPos(Vector checkPos) {
		return false;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return false;
	}

	@Override
	public double getZ() {
		return position.get(2);
	}

	@Override
	public int compareTo(Drawable o) {
		// TODO Auto-generated method stub
		return (int) (this.getZ() - o.getZ());
	}
}
