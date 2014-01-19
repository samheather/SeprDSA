package game;

import java.util.ArrayList;
import java.util.Random;

import main.SeprDSA;

import org.lwjgl.input.Keyboard;
import org.la4j.vector.Vector;

import engine.graphics.*;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.primitives.Identity;
import engine.graphics.drawing.primitives.Line;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;
import engine.input.Clickable;
import engine.input.Input;
import engine.input.Keyboardable;
import engine.input.Scrollable;
import engine.physics.Physical;
import engine.physics.Physicals;
import engine.timing.*;

import org.la4j.vector.dense.*;

public class Plane implements Drawable, Physical, Clickable, Scrollable {

	private static Random randomgen = new Random();
	private double x; // Should be pixel values for x,y
	private double y;
	private double z;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private Vector velocity = new BasicVector(new double[] { 0, 0, 0 });
	private float rotation = 0.0f;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private double radius = 50;
	private int randomImage = randomgen.nextInt(Images.planes.length);
	private int size = 60;
	private String number;
	private Text numbertext;
	private Text altitudeText;
	private Text nextWaypointText;
	private int speed = 15 + randomgen.nextInt(15);
	private ArrayList<WayPoint> wayPointList;
	private EntryExitPoint exitPoint;
	private int score;
	private Vector endLine = new BasicVector(new double[] { 0, 0, 0 });
	private boolean lineExists = false;

	public Plane(String fnumber, ArrayList<WayPoint> pointList,
			EntryExitPoint startPoint, EntryExitPoint endPoint) {
		number = fnumber;
		wayPointList = pointList;
		exitPoint = endPoint;
		score = wayPointList.size() * 10;
		// position = enterPoint.getPos();
		setPos(startPoint.getPos());
		numbertext = new Text(fnumber, Fonts.planeFont, Alignment.CENTRED);
		altitudeText = new Text("Altitude: "
				+ String.valueOf(Math.round(z * 1000)) + "m", Fonts.planeFont,
				Alignment.CENTRED);
		nextWaypointText = new Text(getNextWayPointText(), Fonts.planeFont,
				Alignment.CENTRED);
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
			System.out.println(wayPointList.get(0).toString());
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
			return "Next Waypoint: " + getNextWayPoint().getNumber();
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
		if (left) {
			x -= 10.0;
		}
		if (right) {
			x += 10.0;
		}
		if (up) {
			y += 10.0;
		}
		if (down) {
			y -= 10.0;
		}

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
										new BasicVector(new double[] { 0, -40 })))
				.overlay(
						new Text("Altitude: "
								+ String.valueOf(Math.round(z * 1000)),
								Fonts.planeFont, Alignment.CENTRED)
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(0.75)
								.translate(
										new BasicVector(new double[] { 0, -60 })))
				.overlay(
						nextWaypointText
								.red(1.0)
								.blue(1.0)
								.green(1.0)
								.alpha(0.75)
								.translate(
										new BasicVector(new double[] { 0, -80 })))
				.translate(new BasicVector(new double[] { x, y }))
				.overlay(
						(lineExists ? new Line(getPos(), endLine)
								: new Identity()).alpha(1.0).red(1.0));
	}

	public Vector getPos() {
		return new BasicVector(new double[] { x, y, z });
	}

	public void setPos(Vector newPos) {
		x = newPos.get(0);
		y = newPos.get(1);
		z = newPos.get(2);
	}

	public Vector getVel() {
		return velocity;
	}

	public void setVel(Vector newVel) {
		velocity = newVel;
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(x - checkPos.get(0), 2)
				+ Math.pow(y - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return Math.sqrt(Math.pow(x - checkObj.getPos().get(0), 2)
				+ Math.pow(y - checkObj.getPos().get(1), 2)) < radius;
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
		final Plane plane = this;
		Timing.doNTimes(size, 50, new Timing.NRunnable() {
			public void run(int i) {
				size -= 1;
				if (size == 0) {
					SeprDSA.updateScore(score);
					Planes.remove(plane);
					Input.removeScrollable(plane);
					Input.removeClickable(plane);
					Physicals.remove(plane);
					Drawables.remove(plane);
				}
			}
		});
	}

	public double getZ() {
		return z;
	}

	public int compareTo(Drawable o) {
		return (int) (this.getZ() - o.getZ());
	}

	public void clickDown(int button, Vector pos) {
		if (button == 0) {
			endLine = pos;
			lineExists = true;
		}
		SeprDSA.selectedPlane = this;
	}

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

	public void move(Vector newPos) {
		// System.out.println("Move");
		endLine = newPos;
	}

	private float targetBearing = 0.0f;

	@Override
	public float targetBearing() {
		return targetBearing;
	}

	@Override
	public float rotVel() {
		return 1.0f;
	}

	@Override
	public void scroll(int amount) {
		// TODO Auto-generated method stub
		if (SeprDSA.selectedPlane == this) {
			z += amount * 0.005;
		}
		if (z > 14 || z < 0) {
			destroy();
		}
	}
}
