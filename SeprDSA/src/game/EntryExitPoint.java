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
	private int size = 25; // Size in pixels (Square)
	private int number;

	@Override
	public String toString() {
		return "EntryExitPoint" + number;
	}
	
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

	public EntryExitPoint(Vector pos, float bearing, float tolerances,
			int pointNumber) {
		number = pointNumber;
		Drawables.add(this);
		Physicals.add(this);
		position = pos;
//		if (!(pos.equals(new BasicVector(new double[] { 0, 0, 0 })))) {
//			position = pos;
//		} else {
//			switch (randomgen.nextInt(4)) {
//			case 0: // Right side
//				position = new BasicVector(new double[] {
//						Drawables.virtualDisplaySize().get(0) / 2,
//						(randomgen.nextDouble() - 0.5)
//								* Drawables.virtualDisplaySize().get(1) / 2,
//						randomgen.nextDouble() * 20 });
//				break;
//			case 1: // Left side
//				position = new BasicVector(new double[] {
//						-Drawables.virtualDisplaySize().get(0) / 2,
//						(randomgen.nextDouble() - 0.5)
//								* Drawables.virtualDisplaySize().get(1) / 2,
//						randomgen.nextDouble() * 20 });
//				break;
//			case 2: // Top side
//				position = new BasicVector(new double[] {
//						(randomgen.nextDouble() - 0.5)
//								* Drawables.virtualDisplaySize().get(0) / 2,
//						Drawables.virtualDisplaySize().get(1) / 2,
//						randomgen.nextDouble() * 20 });
//				break;
//			case 3: // Bottom side
//				position = new BasicVector(new double[] {
//						(randomgen.nextDouble() - 0.5)
//								* Drawables.virtualDisplaySize().get(0) / 2,
//						-Drawables.virtualDisplaySize().get(1) / 2,
//						randomgen.nextDouble() * 20 });
//				break;
//			default:
//				System.out.println("Random is broken.");
//			}
//		}
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
