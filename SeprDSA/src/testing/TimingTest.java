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
		tc1 = Timing.doIn(51, new NRunnable() {
			public void run (int n) {
				System.out.println("fail");
				tc2.cancel();
			}
		}, null);
		tc2 = Timing.doNTimes(10, 10, new NRunnable() {
			public void run (int n) {
				System.out.println("win");
				//System.out.println(tc2.cancel.value);
			}
		}, null);
		//tc1.cancel();
		while((System.currentTimeMillis() - startTime) <= 1000) {
			Timing.logic();
		}
		
	}

}
