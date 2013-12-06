package game;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.*;
import engine.graphics.image.Sprite;
import engine.graphics.transform.*;
import engine.physics.Physical;
import engine.physics.Physicals;

public class EntryExitPoint implements Drawable, Physical {

	private float bearingNeeded;
	private float tolerance = 20;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private double radius = 10;

	public EntryExitPoint(Vector pos, float bearing) {
		position = pos;
		bearingNeeded = bearing;
		Drawables.add(this);
		Physicals.add(this);
	}

	public Drawing draw() {
		return new Translate(new Sprite(Images.entryExitPoint), position);
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
		System.out.println("No setting velocity");
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(position.get(0) - checkPos.get(0), 2)
				+ Math.pow(position.get(1) - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return Math
				.sqrt(Math.pow(position.get(0) - checkObj.getPos().get(0), 2)
						+ Math.pow(position.get(1) - checkObj.getPos().get(1),
								2)) < radius;
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
