package engine.physics;

import engine.Timing;
import game.EntryExitPoint;
import game.Plane;
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
			phys.setBearing(phys.getBearing() + ((float)(phys.rotVel() * time * (phys.targetBearing() - phys.getBearing()))));
			/*
			if((phys.targetBearing() - phys.getBearing()) == 0.0f) {
				
			} else if (phys.targetBearing() < phys.getBearing()) {
				phys.setBearing((float)(-phys.rotVel() * time * * (phys.targetBearing() - phys.getBearing())));
			} else {
				phys.setBearing((float)(phys.rotVel() * time * 1000));
			} */
			

			// This for loop will iterate all physicals to check for collisions
			for (int j = 0; j < physicals.size(); j++) {
				Physical checkOther = physicals.get(j);

				// If the physical isn't itself and is colliding
				if (!(phys.equals(checkOther))
						&& (phys.isCollidingObj(checkOther))) {
					colliding.add(new Physical[] { phys, checkOther });
				}

			}
		}
		// System.out.println(colliding);
		if (colliding.size() > 0){
			for (Physical[] physicalPair : colliding) {
				if (physicalPair[0] instanceof Plane){
					if ((physicalPair[1] instanceof WayPoint) && physicalPair[1] == ((Plane)physicalPair[0]).getNextWayPoint()){
						// plane with waypoint collision
						((Plane)physicalPair[0]).updateWaypoints();
					}else if(physicalPair[1] instanceof EntryExitPoint && ((Plane)physicalPair[0]).getWayPoints().size() == 0  && physicalPair[1] == ((Plane)physicalPair[0]).getExitPoint()){
						// plane with entryexit collision
						((Plane)physicalPair[0]).destroy();
					}else if(physicalPair[1] instanceof Plane){
						// plane with plane collision
						// CLEANUP AND PUT BACK TO MAIN MENU
					}
				}
			}
		}
		return colliding;
	}

}
