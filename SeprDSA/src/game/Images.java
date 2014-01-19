package game;

import java.io.IOException;

import engine.graphics.drawing.Texture;

/**
 * Images class - used to define the images used within the projec to save
 * writing file paths elsewhere.
 * 
 * @author sbh514
 * 
 */

public class Images {
	/**
	 * Load a new image as a texture.
	 * <p>
	 * Calls the creation of a texture from an image from the Texture class.
	 * Throws IOException if image not found.
	 * 
	 * @param fileName
	 * @return
	 */
	private static final String imagesLocation = "res/images";

	private static Texture load(String fileName) {
		// Initially null before changing.
		Texture i = null;
		try {
			// Get the path by concatenating relative path with fileName
			i = new Texture(imagesLocation + "/" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not load image at: '" + imagesLocation
					+ "/" + fileName + "'");
			e.printStackTrace();
			System.exit(0);
		}
		return i;
	}

	/**
	 * Example call: Creates Texture with name map, where texture is generated
	 * from image file map.png.
	 */
	public static Texture map = load("new_map_4K.png");
	/**
	 * Array of plane textures (to save creating multiple un-ordered objects.
	 */
	public static Texture planes[] = new Texture[] {
			load("many_planes/nPlane1.png"), load("many_planes/nPlane2.png"),
			load("many_planes/nPlane3.png") };
	public static Texture waypoints[] = new Texture[] { load("checkpoint.png") };
	public static Texture entryExitPoint = load("exit.png");
}
