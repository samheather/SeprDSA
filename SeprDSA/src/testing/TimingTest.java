package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import engine.timing.*;

public class TimingTest {

	engine.timing.TaskCanceller tc1 = null;
	engine.timing.TaskCanceller tc2 = null;
	double startTime = System.currentTimeMillis();

	@Test
	public void testLogic() {
		tc1 = Timing.doIn(51, new Timing.NRunnable() {
			public void run(int n) {
				tc2.cancel();
				assertTrue(tc2.isCancelled());
			}
		});
		tc2 = Timing.doNTimes(10, 10, new Timing.NRunnable() {
			public void run(int n) {
				assertFalse(tc2.isCancelled());
			}
		});
		while ((System.currentTimeMillis() - startTime) <= 1000) {
			engine.timing.Timing.logic();
		}

	}

}
