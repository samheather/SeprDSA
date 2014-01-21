package game;

import java.awt.Desktop;
import java.net.*;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Widget;
import engine.graphics.Drawables;


/**
 * Class for creating and showing the invisible menu buttons.
 * 
 * @author sbh514
 */
public class MainMenu extends Widget {
	
	private Button startButton;
	private Button helpButton;
	private Button exitButton;
	/**
	 * Instance of MainMenuBackground - the layer containing the background 
	 * image with button images.
	 */
	private MainMenuBackground mmb;
	/**
	 * Scale factor for making the window bigger, set from SeprDSA.java.
	 */
	private double resizeWindowScaleFactor = 1;
	
	/**
	 * Create instances of buttons and add to TWL.
	 */
	private void createButtons() {
		startButton = new Button(" ");
		startButton.setTheme("button");
		startButton.addCallback(new Runnable() {
			public void run() {
				hide();
			}
		});
		add(startButton);
		
		helpButton = new Button(" ");
		helpButton.setTheme("button");
		helpButton.addCallback(new Runnable() {
			public void run() {
				try {
			        Desktop.getDesktop().browse(new URL("http://atcga.me/manual.html").toURI());
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		});
		add(helpButton);
		
		exitButton = new Button(" ");
		exitButton.setTheme("button");
		exitButton.addCallback(new Runnable() {
			public void run() {
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}
		});
		add(exitButton);
	}
	
	/**
	 * Layout the transparent buttons to match the images of buttons beneath.
	 */
	protected void layout() {
		startButton.setPosition((int)(446*resizeWindowScaleFactor), (int)(194*resizeWindowScaleFactor));
		startButton.setSize((int)(130*resizeWindowScaleFactor),(int)(45*resizeWindowScaleFactor));
		helpButton.setPosition((int)(446*resizeWindowScaleFactor), (int)(260*resizeWindowScaleFactor));
		helpButton.setSize((int)(130*resizeWindowScaleFactor),(int)(45*resizeWindowScaleFactor));
		exitButton.setPosition((int)(446*resizeWindowScaleFactor), (int)(324*resizeWindowScaleFactor));
		exitButton.setSize((int)(130*resizeWindowScaleFactor),(int)(45*resizeWindowScaleFactor));
	}
	
	/**
	 * Show this main menu (i.e. create the invisible buttons and show the 
	 * background image from MainMenuBackground class.
	 */
	public void show() {
		main.SeprDSA.gameCurrentlyPlaying = false;
		mmb.show();
		layout();
	}
	
	/**
	 * Hide this main menu (i.e. hide these buttons by setting them to (0,0,0,0)
	 * and by calling hide on the MainMenuBackground instance.
	 */
	public void hide() {
		mmb.hide();
		startButton.setPosition(0, 0);
		startButton.setSize(0, 0);
		helpButton.setPosition(0, 0);
		helpButton.setSize(0, 0);
		exitButton.setPosition(0, 0);
		exitButton.setSize(0, 0);
		main.SeprDSA.gameCurrentlyPlaying = true;
	}
	
	/**
	 * Constructor - add this class to Drawables so it's drawn.
	 */
	public MainMenu() {
		Drawables.add(this);
		
		resizeWindowScaleFactor = main.SeprDSA.resizeWindowScaleFactor;
		
		mmb = new MainMenuBackground();
		
		createButtons();
		layout();
	}
}
