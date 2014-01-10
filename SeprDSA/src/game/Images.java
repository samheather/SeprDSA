package game;

import java.io.IOException;

import engine.graphics.drawing.Texture;

public class Images {
	private static Texture load(String fileName) {
		Texture i = null;
		try {
			i = new Texture("res/images/" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return i;
	}

	public static Texture planes[] = new Texture[] { 
		load("many_planes/plane1.png"),
		load("many_planes/plane2.png"),
		load("many_planes/plane3.png"),
		load("many_planes/plane4.png"),
		load("many_planes/plane5.png"),
		load("many_planes/plane6.png"),
		load("many_planes/plane7.png"),
		load("many_planes/plane8.png"),
		load("many_planes/plane9.png"),
		load("many_planes/plane10.png"),
		load("many_planes/plane11.png"),
		load("many_planes/plane12.png"),
		load("many_planes/plane13.png"),
		load("many_planes/plane14.png") };
	//public static Texture plane1 = load("plane1.png");
	//public static Texture plane2 = load("plane2.png");
	public static Texture map = load("map.png");
	public static Texture waypoints[] = new Texture[] { load("waypoint_aqua.png"), load("waypoint_blue.png"), load("waypoint_green.png"), load("waypoint_red.png"), load("waypoint_yellow.png")};
	public static Texture entryExitPoint = load("exit.png");
}
