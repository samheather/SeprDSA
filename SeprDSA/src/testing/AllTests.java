package testing;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import engine.graphics.Drawables;
import engine.graphics.display.Window;


//If running test separately you may need to uncomment the BeforeClass methods
@RunWith(Suite.class)
@SuiteClasses({ EntryExitPointTest.class, FuturePlaneTest.class,
		PhysicalsTest.class, PlanesTest.class, PlaneTest.class,
		TimingTest.class, WaypointTest.class })
public class AllTests {
	 @BeforeClass 
	    public static void setUpClass() throws MalformedURLException, IOException {      
		 Drawables.initialise(new Window(1024, 640), 824, 640, new File(
					"default.xml").toURI().toURL());
    }
	
}

