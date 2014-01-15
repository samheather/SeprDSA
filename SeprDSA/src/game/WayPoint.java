package game;

import java.util.Random;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.graphics.*;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.Texture;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;
import engine.physics.Physical;
import engine.physics.Physicals;

public class WayPoint implements Drawable, Physical {

	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private int radius = 50;
	private int size = 16;
	private String number;
	private int randomImage = new Random().nextInt(Images.waypoints.length);
	private Text numbertext;

	public WayPoint(Vector pos, String pointNumber) {
		position = pos;
		number = pointNumber;
		numbertext = new Text(pointNumber, Fonts.smallFont, Alignment.CENTRED);
		Drawables.add(this);
		Physicals.add(this);
	}

	@Override
	public String toString() {
		return "WayPoint" + number;
	}

	public Drawing draw() {
		return new
				 Sprite(Images.waypoints[randomImage])
				.scale(size / Images.waypoints[randomImage].size().get(0))
				.overlay (
				 numbertext
				.red(0).blue(0).green(0).alpha(0.7)
				.translate(new BasicVector(new double[] {0, 6}))
				)
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
