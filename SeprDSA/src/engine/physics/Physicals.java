package engine.physics;

import java.util.ArrayList;

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

	public static ArrayList<Physical[]> logic(double time) {
		ArrayList<Physical[]> colliding;
		colliding = new ArrayList<Physical[]>();

		for (int i = 0; i < physicals.size(); i++) {
			Physical phys = physicals.get(i);
			// Update physical object's positions
			phys.setPos(phys.getPos().add((phys.getVel().multiply(time))));

			// This for loop will iterate all physicals to check for collisions
			for (int j = 0; j < physicals.size(); j++) {
				Physical checkOther = physicals.get(j);

				// If the physical isn't itself and is colliding
				if (!(phys.equals(checkOther))
						&& (phys.isCollidingPos(checkOther.getPos()))) {
					colliding.add(new Physical[] { phys, checkOther });
				}

			}
		}
		// System.out.println(colliding);
		return colliding;
	}

}
