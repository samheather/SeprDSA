package game;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.*;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.physics.Physical;
import engine.physics.Physicals;

public class WayPoint implements Drawable, Physical {

	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private int radius = 50;
	private int size = 16;
	private int number;

	public WayPoint(Vector pos, int pointNumber) {
		position = pos;
		number = pointNumber;
		Drawables.add(this);
		Physicals.add(this);
	}

	@Override
	public String toString() {
		return "WayPoint" + number;
	}

	public Drawing draw() {
		return new
				 Sprite(Images.waypoint)
				.scale(size / Images.waypoint.size().get(0))
				.translate( position);
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

	public float getBearing() {
		return 0;
	}
}
