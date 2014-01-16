package game;

import java.io.IOException;

import engine.graphics.drawing.Texture;

/**
 * Images class - used to define the images used within the projec to save
 * writing file paths elsewhere.
 * @author sbh514
 *
 */

public class Images {
	/**
	 * Load a new image as a texture.
	 * <p>
	 * Calls the creation of a texture from an image from the Texture class.
	 * Throws IOException if image not found.
	 * @param fileName
	 * @return
	 */
	private static Texture load(String fileName) {
		// Initially null before changing.
		Texture i = null;
		try {
			// Get the path by concatenating relative path with fileName
			i = new Texture("res/images/" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return i;
	}

	/**
	 * Example call: Creates Texture with name map, where texture is generated
	 * from image file map.png.
	 */
	public static Texture map = load("map.png");
	public static Texture map_crop = load("map_crop.png");
	/**
	 * Array of plane textures (to save creating multiple un-ordered objects.
	 */
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
	public static Texture waypoints[] = new Texture[] { 
		load("waypoint_aqua.png"), 
		load("waypoint_blue.png"), 
		load("waypoint_green.png"), 
		load("waypoint_red.png"), 
		load("waypoint_yellow.png")};
	public static Texture entryExitPoint = load("exit.png");
	public static Texture homeButton = load("homeButton.png");
}
