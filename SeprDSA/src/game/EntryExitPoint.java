package game;


import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.physics.Physical;
import engine.physics.Physicals;

/**
 * A point at which Planes can enter or exit the game
 *
 */
public class EntryExitPoint implements Drawable, Physical {

	private float bearingNeeded;
	private float tolerance = 20;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private double radius = 200;
	private int size = (int)((25.0/640.0)*Drawables.virtualDisplaySize().get(1));
	private int number;

	/**
	 * Constructor for EntryExitPoint
	 * @param pos Position for the EntryExitPoint to be drawn
	 * @param bearing Bearing at which Planes can successfully collide with EntryExitPoint
	 * @param tolerances Tolerance of +- angle(degrees) from bearing to which the plane can still collide
	 * @param pointNumber An assigned number for EntryExitPoint used as a UID.
	 */
	public EntryExitPoint(Vector pos, float bearing, float tolerances,
			int pointNumber) {
		number = pointNumber;
		Drawables.add(this);
		Physicals.add(this);
		position = pos;
		bearingNeeded = bearing;
		tolerance = tolerances;
	}
	
	/**
	 * Returns a readable string for debugging
	 */
	@Override
	public String toString() {
		return "EntryExitPoint" + number;
	}
	
	/**
	 * Getter for EntryExitPoint tolerance
	 * @return tolerance of EntryExitPoint
	 */
	public float getTolerance() {
		return tolerance;
	}
	
	/**
	 * Getter for EntryExitPoint bearing
	 * @return bearing of EntryExitPoint
	 */
	public float getBearingNeeded() {
		return bearingNeeded;
	}
	
	/**
	 * Gets a descriptive name for EntryExitPointfor use in SideMenu
	 * @return A string to use for SideMenu plane list
	 */
	public String sidemenuString() {
		switch (number) {
		case 0:
			return "Airport";
		case 1:
			return "N";
		case 2:
			return "E";
		case 3:
			return "S";
		case 4:
			return "W";
		default:
			return "?";
		}
	}

	/**
	 * Allows for the object to be drawn
	 * @return A Sprite that is used for drawing
	 */
	public Drawing draw() {
		return new Sprite(Images.entryExitPoint).scale(
				size / Images.entryExitPoint.size().get(0)).translate(position);
	}

	/**
	 * Getter for EntryExitPoint position
	 * @return Position of EntryExitPoint
	 */
	public Vector getPos() {
		return position;
	}

	/**
	 * Setter for EntryExitPoint position
	 */
	public void setPos(Vector newPos) {
		position = newPos;
	}

	public Vector getVel() {
		return new BasicVector(new double[] { 0, 0, 0 });
	}

	public void setVel(Vector newVel) {
		System.out.println("No setting velocity");
	}

	/**
	 * Method that detects collisions between this and a Vector point
	 * @param checkPos A Vector point to check collision with
	 * @return A boolean that indicates if an this is colliding with a Vector point
	 */
	public boolean isCollidingPos(Vector checkPos) {
		return position.subtract(checkPos).norm() < radius;
	}

	/**
	 * Method that indicates if this is colliding with another object based on radius variable
	 * @param checkObj A Physical object to check collision with
	 * @return A boolean that indicates if an object is colliding with another Physical
	 */
	public boolean isCollidingObj(Physical checkObj) {
		return isCollidingPos(checkObj.getPos())

				&& ((checkObj.getBearing() > 180 ? (checkObj.getBearing() - tolerance) % 360 > bearingNeeded
						- tolerance
						: checkObj.getBearing() > bearingNeeded - tolerance) && (checkObj
						.getBearing() > 180 ? (checkObj.getBearing() + tolerance) % 360 < bearingNeeded
						+ tolerance
						: checkObj.getBearing() < bearingNeeded + tolerance));
	}

	/**
	 * Getter for z used in ZIndexing of drawables
	 */
	public double getZ() {
		return position.get(2);
	}

	public int compareTo(Drawable o) {
		return (int) (this.getZ() - o.getZ());
	}

	public float getBearing() {
		return 0;
	}

	@Override
	public void setBearing(float newBearing) {
	}

	@Override
	public float targetBearing() {
		return 0;
	}

	@Override
	public float rotVel() {
		return 0;
	}
}
