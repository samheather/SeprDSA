package game;

import engine.graphics.drawing.Font;

/**
 * Class for managing the fonts we use in the game.
 * <p>
 * Class for managing the fonts we use in the game - all fonts are defined here
 * as instances of 'Font', which comes from our Graphics engine.
 * 
 * @author sbh514
 */
public class Fonts {
	public static Font test = new Font("Tahoma Bold", java.awt.Font.BOLD,
			144);
	public static Font smallFont = new Font("Tahoma Bold",
			java.awt.Font.BOLD, 48);
	public static Font planeFont = new Font("Tahoma Bold",
			java.awt.Font.BOLD, 64);
	public static Font wayPointFont = new Font("Tahoma Bold",
			java.awt.Font.BOLD, 80);
	public static Font sideMenuTitle = new Font("Tahoma Bold",
			java.awt.Font.BOLD, 64);
	public static Font sideMenuText = new Font("Tahoma Bold",
			java.awt.Font.ITALIC, 64);
}


// Switch to Tahoma Bold font if font rendering problem.