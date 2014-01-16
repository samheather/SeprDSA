package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Timing;
import engine.TaskCanceller;
import engine.Timing.NRunnable;

public class TimingTest {
	
	private int x = 0;
	TaskCanceller tc1 = null;
	TaskCanceller tc2 = null;
	double startTime = System.currentTimeMillis();
	
	@Test
	public void testLogic() {
		tc1 = Timing.doIn(10000, new NRunnable() {
			public void run (int n) {
				System.out.println("fail");
			}
		});
		tc2 = Timing.doIn(20000, new NRunnable() {
			public void run (int n) {
				System.out.println("win");
			}
		});
		tc1.cancel();
		while((System.currentTimeMillis() - startTime) <= 40000) {
			Timing.logic();
		}
		
	}

}
