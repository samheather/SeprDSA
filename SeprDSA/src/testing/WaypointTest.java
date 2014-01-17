package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.display.Window;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.physics.Physical;
import game.Images;
import game.WayPoint;
import org.junit.Test;
import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class WaypointTest {
	
	@Test
	public void testToString() throws MalformedURLException, IOException {
		Drawables.initialise(new Window(1024, 640), 824, 640, new File(
				"default.xml").toURI().toURL());
		
		WayPoint test1 = new WayPoint(new BasicVector(new double[] { 0, 0, 0 }), "1");
		assertEquals("This should create the string WayPoint1", "WayPoint1", test1.toString());
		//assertEquals("The test should return Waypoint1","Waypoint1",test1.toString());
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testTargetBearing() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotVel() {
		fail("Not yet implemented");
	}

	@Override
	public Vector getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPos(Vector newPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector getVel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVel(Vector newVel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollidingPos(Vector checkPos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingObj(Physical checkObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getBearing() {
		// TODO Auto-generated method stub
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

	@Override
	public Drawing draw() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Drawable o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
