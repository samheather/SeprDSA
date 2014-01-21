package game;

import java.util.ArrayList;
import java.util.Random;

import main.SeprDSA;

import org.la4j.vector.Vector;


import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.primitives.Identity;
import engine.graphics.drawing.primitives.Line;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;
import engine.input.Clickable;
import engine.input.Input;
import engine.input.Scrollable;
import engine.physics.Physical;
import engine.physics.Physicals;
import engine.timing.*;

import org.la4j.vector.dense.*;

public class Plane implements Drawable, Physical, Clickable, Scrollable {

	private static Random randomgen = new Random();
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private Vector velocity = new BasicVector(new double[] { 0, 0, 0 });
	private float rotation = 0.0f;
	private double radius = 175;
	private int randomImage = randomgen.nextInt(Images.planes.length);
	private int size = (int)((60.0/640.0)*Drawables.virtualDisplaySize().get(1));
	private String number;
	private Text numbertext;
	private double baseSpeed = ((15.0/640.0) * Drawables.virtualDisplaySize().get(1));
	private int speed = (int)(baseSpeed + randomgen.nextDouble() * baseSpeed);
	private ArrayList<WayPoint> wayPointList;
	private EntryExitPoint exitPoint;
	private int score;
	private Vector endLine = new BasicVector(new double[] { 0, 0, 0 });
	private boolean lineExists = false;
	private float targetBearing = 0.0f;

	public Plane(String fnumber, ArrayList<WayPoint> pointList,
			EntryExitPoint startPoint, EntryExitPoint endPoint, float bearing) {
		number = fnumber;
		wayPointList = pointList;
		exitPoint = endPoint;
		score = wayPointList.size() * 10;
		rotation = bearing;
		targetBearing = bearing;
		// position = enterPoint.getPos();
		setPos(startPoint.getPos());
		// Add line here using setPos to randomise initial altitude.
		numbertext = new Text(fnumber, Fonts.planeFont, Alignment.CENTRED);
		Drawables.add(this);
		Physicals.add(this);
		Planes.add(this);
		// Input.addKeyboardable(this);
		Input.addClickable(this);
		Input.addScrollable(this);
	}

	public String toString() {
		return "Plane" + number;
	}

	public void updateWaypoints() {
		if (wayPointList.size() > 0) {
			//System.out.println(wayPointList.get(0).toString());
			wayPointList.remove(0);
			// update flight plan stuff too at some point
		}
	}

	public WayPoint getNextWayPoint() {
		if (wayPointList.size() > 0) {
			return wayPointList.get(0);
		} else {
			return null;
		}
	}
	
	public String getNextWayPointText() {
		if (getNextWayPoint() != null) {
			return "Next " + getNextWayPoint().toString();
		}
		else {
			return "Now Exit!";
		}
	}

	public EntryExitPoint getExitPoint() {
		if (exitPoint != null) {
			return exitPoint;
		} else {
			return null;
		}
	}

	public ArrayList<WayPoint> getWayPoints() {
		return wayPointList;
	}

	public String getFNumber() {
		return number;
	}

	public Drawing draw() {
		return new Sprite(Images.planes[randomImage])
				.red(1.0)
				.blue(SeprDSA.selectedPlane == this ? 0.0 : 1.0)
				.green(SeprDSA.selectedPlane == this ? 0.0 : 1.0)
				.scale(size / Images.planes[randomImage].size().get(0))
				.rotate(rotation)
				.overlay(
						numbertext
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(0.75)
								.translate(
										new BasicVector(new double[] { 0, -160 })))
				.overlay(
						new Text("Altitude: "
								+ String.valueOf(Math.round(position.get(2) * 1000)),
								Fonts.planeFont, Alignment.CENTRED)
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(0.75)
								.translate(
										new BasicVector(new double[] { 0, -240 })))
				.overlay(
						new Text(getNextWayPointText(), Fonts.planeFont,
								Alignment.CENTRED)
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(0.75)
								.translate(
										new BasicVector(new double[] { 0, -320 })))
				.translate(new BasicVector(new double[] { position.get(0), position.get(1) }))
				.overlay(
						(lineExists ? new Line(getPos(), endLine)
								: new Identity()).alpha(1.0).red(1.0));
	}

	public Vector getPos() {
		return position;
	}

	public void setPos(Vector newPos) {
		position = newPos;
	}

	public Vector getVel() {
		return velocity;
	}

	public void setVel(Vector newVel) {
		velocity = newVel;
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(position.get(0) - checkPos.get(0), 2)
				+ Math.pow(position.get(1) - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return Math.sqrt(Math.pow(position.get(0) - checkObj.getPos().get(0), 2)
				+ Math.pow(position.get(1) - checkObj.getPos().get(1), 2)) < radius;
	}

	public float getBearing() {
		return rotation;
	};

	public void setBearing(final float newBearing) {
		rotation = newBearing % 360;
		setVel(new BasicVector(new double[] {
				Math.cos(Math.toRadians(rotation)),
				Math.sin(Math.toRadians(rotation)), 0 }).multiply(speed));
	};

	public void destroy() {
		Timing.doNTimes(size, 50, new Timing.NRunnable() {
			public void run(int i) {
				size -= 1;
				if (size == 0) {
					SeprDSA.updateScore(score);
					quickRemove();
				}
			}
		});
	}
	
	/**
	 * Removes all pointers from planes
	 */
	public void quickRemove() {
		//Planes.remove(this);
		Input.removeScrollable(this);
		Input.removeClickable(this);
		Physicals.remove(this);
		Drawables.remove(this);
	}

	/**
	 * get Z value for drawing
	 */
	public double getZ() {
		return getPos().get(2);
	}

	/**
	 * Gets a comparison used in painter's algorithm
	 */
	public int compareTo(Drawable o) {
		return (int) (this.getZ() - o.getZ());
	}

	/**
	 * Fired when mouse button 1 is pressed down
	 * <p>
	 * Will update the selected plane and start line drawing
	 */
	public void clickDown(int button, Vector pos) {
		if (button == 0) {
			endLine = pos;
			lineExists = true;
		}
		SeprDSA.selectedPlane = this;
	}

	/**
	 * Fired when mouse button 1 is pressed up
	 */
	public void clickUp(int button) {
		if (button != 0)
			return;
		lineExists = false;
		Vector planePos = new BasicVector(new double[] { getPos().get(0),
				getPos().get(1) });
		Vector temp = planePos.subtract(endLine);
		float angle = (float) Math.toDegrees(Math.atan(temp.get(1)
				/ temp.get(0)));
		angle = planePos.get(0) < endLine.get(0) ? angle : angle + 180;
		targetBearing = angle;
	}

	public void clickAway() {
		// System.out.println("Click away");
	}

	/**
	 * Fired when the mouse is moved
	 */
	public void move(Vector newPos) {
		// System.out.println("Move");
		endLine = newPos;
	}

	/**
	 * Getter for the bearing that the plane wishes to reach
	 * @return Desired bearing of the plane
	 */
	@Override
	public float targetBearing() {
		return targetBearing;
	}

	/**
	 * @return returns the rotational velocity of the Plane
	 */
	@Override
	public float rotVel() {
		return 1.0f;
	}

	/**
	 * Called when scroll performed on this item - adjust the altitude of plane.
	 */
	@Override
	public void scroll(int amount) {
		if (SeprDSA.selectedPlane == this) {
			position.set(2, position.get(2) + amount * 0.001);
		}
		if (position.get(2) > 14.0) {
			position.set(2, 14.0);
		}
		if (position.get(2) < 0.1) {
			position.set(2, 0.1);
		}
	}
}
