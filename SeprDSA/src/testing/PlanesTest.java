package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlanesTest {

	@Test
	public void testUpdateTimer() {
		game.Planes.updateTimer();
		assertNotNull("Checks if the futurePlane array is null. Which it shouldn't be.", game.FuturePlanes.futurePlanes);
	}

}
