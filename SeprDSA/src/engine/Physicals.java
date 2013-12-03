package engine;

import java.util.ArrayList;
import java.util.List;

import org.la4j.vector.Vector;

public class Physicals {
	private static ArrayList<Physical> physicals;
	static {
		physicals = new ArrayList<Physical>();
	}

	public static void add(Physical d) {
		physicals.add(d);
	}

	public static void remove(Physical d) {
		physicals.remove(d);
	}

	public static ArrayList<Physical[]> logic(double time){
		ArrayList<Physical[]> colliding;
		colliding = new ArrayList<Physical[]>();
		
		for (int i = 0; i < physicals.size(); i++) {
			Physical phys = physicals.get(i);
			phys.setPos(phys.getPos().add((phys.getVel().multiply(time)))); // Update physical object's positions
			
			
			
			for (int j = 0; j < physicals.size(); j++){ // This for loop will iterate all physicals to check for collisions
				Physical checkOther = physicals.get(j);
				
				if (!(phys.equals(checkOther)) && (phys.isCollidingPos(checkOther.getPos()))){ // If the physical isn't itself and is colliding
					colliding.add(new Physical[] {phys, checkOther});
				}
				
			}
		}
		//System.out.println(colliding);
		return colliding;
	}

}
