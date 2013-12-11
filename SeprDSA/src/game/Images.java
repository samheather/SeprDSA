package game;

import java.io.IOException;

import engine.graphics.sprite.Image;

public class Images {
	private static Image load(String fileName) {
		Image i = null;
		try {
			i = new Image("res/images/" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return i;
	}

	public static Image planes[] = new Image[] { load("plane1.png"),
			load("plane2.png") };
	public static Image plane1 = load("plane1.png");
	public static Image plane2 = load("plane2.png");
	public static Image map = load("map.png");
	public static Image waypoint = load("waypoint.png");
	public static Image entryExitPoint = load("exit.png");
}
