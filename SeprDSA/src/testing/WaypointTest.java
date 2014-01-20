package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import game.WayPoint;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class WaypointTest {

	@Test
	public void testToString() throws MalformedURLException, IOException {
		
		WayPoint test1 = new WayPoint(
				new BasicVector(new double[] { 0, 0, 0 }), "1");
		assertEquals("The test should return WayPoint: 1", "WayPoint: 1",
				test1.toString());
	}

}
