package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import engine.timing.*;

public class TimingTest {

	private int x = 0;
	engine.timing.TaskCanceller tc1 = null;
	engine.timing.TaskCanceller tc2 = null;
	double startTime = System.currentTimeMillis();

	@Test
	public void testLogic() {
		tc1 = Timing.doIn(51, new Timing.NRunnable() {
			public void run(int n) {
				System.out.println("fail");
				tc2.cancel();
			}
		});
		tc2 = Timing.doNTimes(10, 10, new Timing.NRunnable() {
			public void run(int n) {
				System.out.println("win");
				// System.out.println(tc2.cancel.value);
			}
		});
		// tc1.cancel();
		while ((System.currentTimeMillis() - startTime) <= 1000) {
			engine.timing.Timing.logic();
		}

	}

}
