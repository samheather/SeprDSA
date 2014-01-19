package game;

import main.SeprDSA;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

import java.util.Random;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.physics.Physical;
import engine.physics.Physicals;

public class EntryExitPoint implements Drawable, Physical {

	private static Random randomgen = new Random();
	private float bearingNeeded;
	private float tolerance = 20;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private double radius = 200;
	private int size = 16; // Size in pixels (Square)
	private int number;

	@Override
	public String toString() {
		return "EntryExitPoint" + number;
	}

	public static Vector getEntryExitPointPos(int counter) {
		boolean isValid = true;
		Vector testVector = new BasicVector(new double[] { 0, 0, 0 });
		switch (randomgen.nextInt(4)) {
		case 0: // Right side
			testVector = new BasicVector(new double[] { Display.getWidth() / 2,
					(randomgen.nextDouble() - 0.5) * Display.getHeight() / 2,
					randomgen.nextDouble() * 20 });
			break;
		case 1: // Left side
			testVector = new BasicVector(new double[] {
					-Display.getWidth() / 2,
					(randomgen.nextDouble() - 0.5) * Display.getHeight() / 2,
					randomgen.nextDouble() * 20 });
			break;
		case 2: // Top side
			testVector = new BasicVector(new double[] {
					(randomgen.nextDouble() - 0.5) * Display.getWidth() / 2,
					Display.getHeight() / 2, randomgen.nextDouble() * 20 });
			break;
		case 3: // Bottom side
			testVector = new BasicVector(new double[] {
					(randomgen.nextDouble() - 0.5) * Display.getWidth() / 2,
					-Display.getHeight() / 2, randomgen.nextDouble() * 20 });
			break;
		default:
			System.out.println("Random is brokened");
		}

		for (EntryExitPoint entryExitPoint : SeprDSA.getEntryExitPoints()) {
			if (SeprDSA.getMagnitude(entryExitPoint.getPos(), testVector) < 200.0f) {
				isValid = false;
			}
		}
		if (isValid || counter > 50) {
			System.out.println(counter);
			return testVector;
		} else {
			return getEntryExitPointPos(counter + 1);
		}
	}

	public EntryExitPoint(Vector pos, float bearing, float tolerances,
			int pointNumber) {
		number = pointNumber;
		Drawables.add(this);
		Physicals.add(this);
		if (!(pos.equals(new BasicVector(new double[] { 0, 0, 0 })))) {
			position = pos;
		} else {
			switch (randomgen.nextInt(4)) {
			case 0: // Right side
				position = new BasicVector(new double[] {
						Drawables.virtualDisplaySize().get(0) / 2,
						(randomgen.nextDouble() - 0.5)
								* Drawables.virtualDisplaySize().get(1) / 2,
						randomgen.nextDouble() * 20 });
				break;
			case 1: // Left side
				position = new BasicVector(new double[] {
						-Drawables.virtualDisplaySize().get(0) / 2,
						(randomgen.nextDouble() - 0.5)
								* Drawables.virtualDisplaySize().get(1) / 2,
						randomgen.nextDouble() * 20 });
				break;
			case 2: // Top side
				position = new BasicVector(new double[] {
						(randomgen.nextDouble() - 0.5)
								* Drawables.virtualDisplaySize().get(0) / 2,
						Drawables.virtualDisplaySize().get(1) / 2,
						randomgen.nextDouble() * 20 });
				break;
			case 3: // Bottom side
				position = new BasicVector(new double[] {
						(randomgen.nextDouble() - 0.5)
								* Drawables.virtualDisplaySize().get(0) / 2,
						-Drawables.virtualDisplaySize().get(1) / 2,
						randomgen.nextDouble() * 20 });
				break;
			default:
				System.out.println("Random is brokened");
			}
		}
		tolerance = tolerances;
	}

	public Drawing draw() {
		return new Sprite(Images.entryExitPoint).scale(
				size / Images.entryExitPoint.size().get(0)).translate(position);
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
		return (Math.sqrt(Math.pow(position.get(0) - checkObj.getPos().get(0),
				2) + Math.pow(position.get(1) - checkObj.getPos().get(1), 2)) < radius)

				&& ((checkObj.getBearing() > 180 ? (checkObj.getBearing() - tolerance) % 360 > bearingNeeded
						- tolerance
						: checkObj.getBearing() > bearingNeeded - tolerance) && (checkObj
						.getBearing() > 180 ? (checkObj.getBearing() + tolerance) % 360 < bearingNeeded
						+ tolerance
						: checkObj.getBearing() < bearingNeeded + tolerance));
	}

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
