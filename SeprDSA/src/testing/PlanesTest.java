package testing;

import static org.junit.Assert.*;
import engine.graphics.Drawables;
import engine.graphics.display.Window;
import game.EntryExitPoint;
import game.Sidemenu;
import game.WayPoint;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import main.SeprDSA;

import org.junit.BeforeClass;
import org.junit.Test;
import org.la4j.vector.dense.BasicVector;

public class PlanesTest {
	
	@BeforeClass
	public static void setUp() throws MalformedURLException, IOException {
		Drawables.initialise(new Window(1024, 640), 824, 640, new File(
				"default.xml").toURI().toURL());
		EntryExitPoint newExit1 = new EntryExitPoint(new BasicVector(
			new double[] { Sidemenu.width / 2,
					Drawables.virtualDisplaySize().get(1) / 2, 11 } 
			), -90, 85, 1); 
		SeprDSA.addEntryExitPoint(newExit1);
		WayPoint testWP1 = new WayPoint(
				new BasicVector(new double[] { 10, 20, 0 }), "1");
		WayPoint testWP2 = new WayPoint(
				new BasicVector(new double[] { 310, 320, 0 }), "2");
		SeprDSA.addWayPoint(testWP1);
		SeprDSA.addWayPoint(testWP2);
	}
		
	
	@Test
	public void testUpdateTimer() {
		game.Planes.updateTimer();
		assertNotNull("Checks if the futurePlane array is null. Which it shouldn't be.", game.FuturePlanes.futurePlanes);
	}

}
