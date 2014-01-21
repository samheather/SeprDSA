package testing;

import static org.junit.Assert.*;

import engine.physics.Physicals;
import game.EntryExitPoint;
import game.WayPoint;

import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class PhysicalsTest {
	
	@Test
	public void testLogicColliding() {
		EntryExitPoint testEEP1 = new EntryExitPoint(new BasicVector(
				new double[] { 12, 300, 0 }), 1, 20, 12);
		WayPoint testWP = new WayPoint(
				new BasicVector(new double[] { 12, 300, 0 }), "1");
		
		assertFalse("This should return false as the Physicals list should " +
				"contain the EntryExitPoint and the Waypoint.", 
				Physicals.logic().isEmpty());
		
		Physicals.remove(testEEP1);
		Physicals.remove(testWP);
		assertTrue("This should return true as the Physicals list should not " +
				"contain the EntryExitPoint and the Waypoint as they have " +
				"been removed.", Physicals.logic().isEmpty());
	}

}
