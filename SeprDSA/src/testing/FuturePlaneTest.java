package testing;

import static org.junit.Assert.*;
import game.FuturePlane;

import org.junit.Test;

public class FuturePlaneTest {

	@Test
	public void testGenerateFlightNumber() throws InterruptedException {
		assertTrue("Checks that the Flight Number is equal to 8", FuturePlane
				.generateFlightNumber().length() == 8);
	}

}
