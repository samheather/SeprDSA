package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import engine.graphics.Drawables;
import engine.graphics.display.Window;
import game.WayPoint;

import org.junit.BeforeClass;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class WaypointTest {

//	@BeforeClass 
//  public static void setUpClass() throws MalformedURLException, IOException {      
//	 Drawables.initialise(new Window(1024, 640), 824, 640, new File(
//				"default.xml").toURI().toURL());
//}
	
	@Test
	public void testToString() throws MalformedURLException, IOException {
		
		WayPoint test1 = new WayPoint(
				new BasicVector(new double[] { 0, 0, 0 }), "1");
		assertEquals("The test should return WayPoint: 1", "WayPoint: 1",
				test1.toString());
	}

}
