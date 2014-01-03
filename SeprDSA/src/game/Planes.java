package game;

import java.util.ArrayList;
import java.util.List;

public class Planes {
	private static List<Plane> planes;
	static {
		planes = new ArrayList<Plane>();
	}

	public static void add(Plane p) {
		planes.add(p);
	}

	public static void remove(Plane p) {
		planes.remove(p);
	}
	
	public static int size(){
		return planes.size();
	}

}
