package game;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

import java.util.Random;

import engine.graphics.*;
import engine.physics.Physical;
import engine.physics.Physicals;

public class EntryExitPoint implements Drawable, Physical {

	private Random randomgen = new Random();
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

	public EntryExitPoint(Vector pos, float bearing, float tolerances,
			int pointNumber) {

		Drawables.add(this);
		Physicals.add(this);
		if (!(pos.equals(new BasicVector(new double[] { 0, 0, 0 })))) {
			position = pos;
		} else {
			switch (randomgen.nextInt(4)) {
			case 0: // Right side
				position = new BasicVector(new double[] {
						Display.getWidth() / 2,
						(randomgen.nextDouble() - 0.5) * Display.getHeight()
								/ 2, randomgen.nextDouble() * 20 });
				break;
			case 1: // Left side
				position = new BasicVector(new double[] {
						-Display.getWidth() / 2,
						(randomgen.nextDouble() - 0.5) * Display.getHeight()
								/ 2, randomgen.nextDouble() * 20 });
				break;
			case 2: // Top side
				position = new BasicVector(
						new double[] {
								(randomgen.nextDouble() - 0.5)
										* Display.getWidth() / 2,
								Display.getHeight() / 2,
								randomgen.nextDouble() * 20 });
				break;
			case 3: // Bottom side
				position = new BasicVector(
						new double[] {
								(randomgen.nextDouble() - 0.5)
										* Display.getWidth() / 2,
								-Display.getHeight() / 2,
								randomgen.nextDouble() * 20 });
				break;
			default:
				System.out.println("Lolwat");
			}
		}
		tolerance = tolerances;
	}

	public Drawing draw() {
		return new
				 Sprite(Images.entryExitPoint)
				.scale(size / Images.entryExitPoint.size().get(0))
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
		System.out.println("No setting velocity");
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(position.get(0) - checkPos.get(0), 2)
				+ Math.pow(position.get(1) - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		/*
		 * System.out.println("Distance: " + (Math.sqrt(Math.pow(position.get(0)
		 * - checkObj.getPos().get(0), 2) + Math.pow(position.get(1) -
		 * checkObj.getPos().get(1), 2)) < radius));
		 * System.out.println("Within negative Tolerance: " +
		 * (checkObj.getBearing() > 180 ? (checkObj.getBearing() - tolerance)%
		 * 360 > bearingNeeded - tolerance : checkObj.getBearing() >
		 * bearingNeeded - tolerance ));
		 * System.out.println("Within positive Tolerance: " +
		 * (checkObj.getBearing() > 180 ? (checkObj.getBearing() + tolerance)%
		 * 360 < bearingNeeded + tolerance : checkObj.getBearing() <
		 * bearingNeeded + tolerance ));
		 */
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

}
