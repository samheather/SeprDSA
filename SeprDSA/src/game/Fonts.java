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
	public static Font test = new Font("Times New Roman", java.awt.Font.BOLD,
			36);
	public static Font smallFont = new Font("Times New Roman",
			java.awt.Font.BOLD, 12);
	public static Font planeFont = new Font("Times New Roman",
			java.awt.Font.BOLD, 16);
	public static Font sideMenuTitle = new Font("Times New Roman",
			java.awt.Font.BOLD, 16);
	public static Font sideMenuText = new Font("Times New Roman",
			java.awt.Font.ITALIC, 16);
}
