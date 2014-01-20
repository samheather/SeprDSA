package game;

import java.util.Random;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;
import engine.physics.Physical;
import engine.physics.Physicals;

public class WayPoint implements Drawable, Physical {

	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private int size = (int)((25.0/640.0)*Drawables.virtualDisplaySize().get(1));
	private String number;
	private int randomImage = new Random().nextInt(Images.waypoints.length);
	private Text numbertext;

	/**
	 * Adds a way point to the list of drawables and physicals to be placed
	 * 
	 * @param pos
	 * @param pointNumber
	 */
	public WayPoint(Vector pos, String pointNumber) {
		position = pos;
		number = pointNumber;
		numbertext = new Text(pointNumber, Fonts.wayPointFont, Alignment.CENTRED);
		Drawables.add(this);
		Physicals.add(this);
	}

	@Override
	public String toString() {
		return "WayPoint: " + number;
	}
	
	public String getNumber() {
		return number;
	}

	/**
	 * Adds the way point to the GUI
	 * 
	 */
	public Drawing draw() {
		return new Sprite(Images.waypoints[randomImage])
				.scale(size / Images.waypoints[randomImage].size().get(0))
				.overlay(
						numbertext
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(1.0)
								.translate(
										new BasicVector(new double[] { 0, -28 })))
				.translate(position);
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
		return (int) (this.getZ() - o.getZ());
	}

	public float getBearing() {
		return 0;
	}

	@Override
	public void setBearing(float newBearing) {
		// TODO Auto-generated method stub

	}

	@Override
	public float targetBearing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float rotVel() {
		// TODO Auto-generated method stub
		return 0;
	}

}
