package game;

import java.awt.Desktop;
import java.net.*;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Widget;
import engine.graphics.Drawables;


/**
 * Class for the map image to be drawn.
 * 
 * @author sbh514
 */

public class MainMenu extends Widget {
	
	private Button startButton;
	private Button helpButton;
	private Button exitButton;
	private MainMenuBackground mmb;
	
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
	
	protected void layout() {
		startButton.setPosition(446, 194);
		startButton.setSize(130,45);
		helpButton.setPosition(446, 260);
		helpButton.setSize(130,45);
		exitButton.setPosition(446, 324);
		exitButton.setSize(130,45);
	}
	
	public void show() {
		main.SeprDSA.gameCurrentlyPlaying = false;
		mmb.show();
		layout();
	}
	
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
		
		mmb = new MainMenuBackground();
		
		createButtons();
		layout();
	}
}
