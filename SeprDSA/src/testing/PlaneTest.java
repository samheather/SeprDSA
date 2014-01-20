package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import engine.graphics.Drawables;
import engine.graphics.display.Window;
import game.EntryExitPoint;
import game.Plane;
import game.WayPoint;

import org.junit.BeforeClass;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class PlaneTest {
	private ArrayList<WayPoint> wayPointList = new ArrayList<WayPoint>();

	@BeforeClass
	public static void setUp() throws MalformedURLException, IOException {
		Drawables.initialise(new Window(1024, 640), 824, 640, new File(
				"default.xml").toURI().toURL());
	}

	@Test
	public void testToString() {
		EntryExitPoint testEEP1 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		WayPoint testWP = new WayPoint(
				new BasicVector(new double[] { 0, 0, 0 }), "1");
		wayPointList.add(testWP);

		EntryExitPoint enterPoint = testEEP1;
		EntryExitPoint exitPoint = testEEP1;

		wayPointList = new ArrayList<WayPoint>();

		Plane test1 = new Plane("12345678", wayPointList, enterPoint, exitPoint, 0);
		assertEquals("This should return the string Plane12345678",
				"Plane12345678", test1.toString());
	}

	@Test
	public void testIsCollidingPos() {
		EntryExitPoint testEEP1 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		EntryExitPoint testEEP2 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		WayPoint testWP = new WayPoint(
				new BasicVector(new double[] { 0, 0, 0 }), "1");
		wayPointList.add(testWP);

		EntryExitPoint enterPoint1 = testEEP1;
		EntryExitPoint exitPoint1 = testEEP1;
		EntryExitPoint enterPoint2 = testEEP2;
		EntryExitPoint exitPoint2 = testEEP2;

		wayPointList = new ArrayList<WayPoint>();

		Plane test1 = new Plane("ABCDEFGH", wayPointList, enterPoint1,
				exitPoint2, 0);
		Plane test2 = new Plane("abcdefgh", wayPointList, enterPoint2,
				exitPoint1, 0);
		assertEquals("This should return true as as the points are colliding",
				true, test1.isCollidingPos(test2.getPos()));
	}

	@Test
	public void testIsCollidingObj() {
		EntryExitPoint testEEP1 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		EntryExitPoint testEEP2 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		WayPoint testWP = new WayPoint(
				new BasicVector(new double[] { 0, 0, 0 }), "1");
		wayPointList.add(testWP);

		EntryExitPoint enterPoint1 = testEEP1;
		EntryExitPoint exitPoint1 = testEEP1;
		EntryExitPoint enterPoint2 = testEEP2;
		EntryExitPoint exitPoint2 = testEEP2;

		wayPointList = new ArrayList<WayPoint>();

		Plane test1 = new Plane("ABCDEFGH", wayPointList, enterPoint1,
				exitPoint2, 0);
		Plane test2 = new Plane("abcdefgh", wayPointList, enterPoint2,
				exitPoint1, 0);
		assertEquals("This should return true as as the points are colliding",
				true, test1.isCollidingObj(test2));
	}

}
