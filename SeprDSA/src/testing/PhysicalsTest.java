package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import engine.graphics.Drawables;
import engine.graphics.display.Window;
import engine.physics.Physicals;
import game.EntryExitPoint;
import game.WayPoint;

import org.junit.BeforeClass;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class PhysicalsTest {
//	@BeforeClass 
//    public static void setUpClass() throws MalformedURLException, IOException {      
//	 Drawables.initialise(new Window(1024, 640), 824, 640, new File(
//				"default.xml").toURI().toURL());
//}
	
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
