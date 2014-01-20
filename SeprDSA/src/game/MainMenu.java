package game;

import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Widget;
import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;

/**
 * Class for the map image to be drawn.
 * 
 * @author sbh514
 */

public class MainMenu extends Widget /*implements Drawable*/ {
	
	private Button startButton;
	private Button helpButton;
	private Button exitButton;
	
	private void createButtons() {
		startButton = new Button("");
		startButton.setTheme("button");
		startButton.addCallback(new Runnable() {
			public void run() {
				System.out.println("Start game");
			}
		});
		add(startButton);
		
		helpButton = new Button("");
		helpButton.setTheme("button");
		helpButton.addCallback(new Runnable() {
			public void run() {
				System.out.println("Show Help");
			}
		});
		add(helpButton);
		
		exitButton = new Button("");
		exitButton.setTheme("button");
		exitButton.addCallback(new Runnable() {
			public void run() {
				System.out.println("Exit game");
			}
		});
		add(exitButton);
	}
	
	protected void layout() {
		startButton.setPosition(10, 10);
		startButton.setSize(10,10);
		helpButton.setPosition(10, 10);
		helpButton.setSize(10,10);
		exitButton.setPosition(10, 10);
		exitButton.setSize(10,10);
	}
	
	/**
	 * Constructor - add this class to Drawables so it's drawn.
	 */
	public MainMenu() {
		Drawables.add(this);
		
		createButtons();
	}

	/**
	 * Necessary for layering of drawable objects.
	 */
	private double z = 100000000;

	/**
	 * What to draw when instances of Map are drawn.
	 */
	public Drawing draw() {
		return new Sprite(Images.mainMenu);
	}

	/**
	 * Z-value may be necessary depending on draw order.
	 */
	public double getZ() {
		return z;
	}

//	/**
//	 * Allow sorting of drawables to support ordering of objects for drawing
//	 * (i.e. for layers - make sure instances of Map aren't drawn on top of
//	 * planes).
//	 */
//	@Override
//	public int compareTo(Drawable o) {
//		// Compare to other drawables to establsih draw order.
//		// TODO Auto-generated method stub
//		return (int) (z - o.getZ());
//	}
}
