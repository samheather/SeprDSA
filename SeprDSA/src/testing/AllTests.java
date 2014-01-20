package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ EntryExitPointTest.class, FuturePlaneTest.class,
		LeaderboardTest.class, PhysicalsTest.class, PlanesTest.class,
		PlaneTest.class, TimingTest.class, WaypointTest.class })
public class AllTests {
	
}

