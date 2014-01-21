package testing;

import static org.junit.Assert.*;
import engine.physics.Physicals;
import game.EntryExitPoint;

import org.junit.AfterClass;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class EntryExitPointTest {
	private static EntryExitPoint test1 = new EntryExitPoint(new BasicVector(new double[] {
			12, 300, 0 }), 0, 20, 12);
	private static EntryExitPoint test2 = new EntryExitPoint(new BasicVector(new double[] {
			10, 300, 0 }), 0, 20, 12);
	private static EntryExitPoint test3 = new EntryExitPoint(new BasicVector(new double[] {
			12, 600, 0 }), 0, 20, 12);
	private static EntryExitPoint test4 = new EntryExitPoint(new BasicVector(new double[] {
			10, 30, 0 }), 0, 20, 12);
	
	@Test
	public void testToString() {
		EntryExitPoint test1 = new EntryExitPoint(new BasicVector(new double[] {
				12, 300, 0 }), 1, 20, 12);
		assertEquals("The result should be EntryExitPoint12",
				"EntryExitPoint12", test1.toString());
	}

	@Test
	public void testIsCollidingPos() {
		assertEquals("This should return true as as the points are colliding",
				true, test1.isCollidingPos(test2.getPos()));
		assertEquals(
				"This test should return false as the object and the vector are not colliding",
				false, test3.isCollidingPos(test4.getPos()));
	}

	@Test
	public void testIsCollidingObj() {
		assertEquals("This should return true as as the objects are colliding",
				true, test1.isCollidingObj(test2));
		assertEquals(
				"This test should return false as the object and the vector are not colliding",
				false, test3.isCollidingObj(test4));
	}
	
	@AfterClass
	public static void cleanUp() {
		Physicals.remove(test1);
		Physicals.remove(test2);
		Physicals.remove(test3);
		Physicals.remove(test4);
	}

}
