package game;

import engine.graphics.image.Image;

public class Images {
	private static Image load(String fileName) {
		return new Image("res/images/" + fileName);
	}
	public static Image planes[] = new Image[] { load("plane1.png"),
			load("plane2.png") };
	public static Image plane1 = load("plane1.png");
	public static Image plane2 = load("plane2.png");
	public static Image map = load("map.png");
	public static Image waypoint = load("waypoint.png");
	public static Image entryExitPoint = load("exit.png");
}
