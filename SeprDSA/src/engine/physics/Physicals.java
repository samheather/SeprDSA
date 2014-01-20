package engine.physics;

import engine.graphics.Drawables;
import engine.timing.Timing;
import game.EntryExitPoint;
import game.Plane;
import game.Sidemenu;
import game.WayPoint;

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

	public static ArrayList<Physical[]> logic() {
		ArrayList<Physical[]> colliding = new ArrayList<Physical[]>();
		double time = Timing.timeSinceLastFrame() / 1000;

		for (int i = 0; i < physicals.size(); i++) {
			Physical phys = physicals.get(i);
			// Update physical object's positions
			phys.setPos(phys.getPos().add((phys.getVel().multiply(time))));
			if (Math.abs(phys.targetBearing() - phys.getBearing()) < (50 * phys
					.rotVel() * time)) {
				phys.setBearing(phys.targetBearing());
			} else {
				phys.setBearing(phys.getBearing()
						+ ((float) (phys.rotVel() * time * 50 * Math.signum(((Math
								.abs(phys.targetBearing() - phys.getBearing()) % 360) < 180) ? (phys
								.targetBearing() - phys.getBearing()) : (phys
								.getBearing() - phys.targetBearing())))));
			}

			// This for loop will iterate all physicals to check for collisions
			for (int j = 0; j < physicals.size(); j++) {
				Physical checkOther = physicals.get(j);

				// If the physical isn't itself and is colliding
				if (!(phys.equals(checkOther))
						&& (phys.isCollidingObj(checkOther))) {
					colliding.add(new Physical[] { phys, checkOther });
				} 

			}
			if (phys instanceof Plane && phys.getPos().get(0) > Sidemenu.
					width/2 + Sidemenu.remainingDisplayWidth()/2 
					|| phys.getPos().get(0) < Sidemenu.
					width/2 - Sidemenu.remainingDisplayWidth()/2 
					|| phys.getPos().get(1) > Drawables.
					virtualDisplaySize().get(1) / 2 
					|| phys.getPos().get(1) < -Drawables.
					virtualDisplaySize().get(1) / 2) {
				//plane with edge of screen collision
				
			}
		}
		// System.out.println(colliding);
		if (colliding.size() > 0) {
			for (Physical[] physicalPair : colliding) {
				if (physicalPair[0] instanceof Plane) {
					Plane checkPlane = (Plane) physicalPair[0];
					if ((physicalPair[1] instanceof WayPoint)
							&& physicalPair[1] == checkPlane
									.getNextWayPoint()) {
						// plane with waypoint collision
						checkPlane.updateWaypoints();
					} else if (physicalPair[1] instanceof EntryExitPoint
							&& checkPlane.getWayPoints().size() == 0
							&& physicalPair[1] == checkPlane
									.getExitPoint()) {
						// plane with entryexit collision
						checkPlane.destroy();
					} else if (physicalPair[1] instanceof Plane) {
						// plane with plane collision
						// CLEANUP AND PUT BACK TO MAIN MENU
					}  
				}
			}
		}
		return colliding;
	}

}
