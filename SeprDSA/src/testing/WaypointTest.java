package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import engine.graphics.Drawables;
import engine.graphics.display.Window;
import game.WayPoint;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class WaypointTest {
	
	@Test
	public void testToString() throws MalformedURLException, IOException {
		Drawables.initialise(new Window(1024, 640), 824, 640, new File(
				"default.xml").toURI().toURL());
		
		WayPoint test1 = new WayPoint(new BasicVector(new double[] { 0, 0, 0 }), "1");
		System.out.print(test1.toString());
		assertEquals("The test should return WayPoint1", "WayPoint1", test1.toString());
	}


}
