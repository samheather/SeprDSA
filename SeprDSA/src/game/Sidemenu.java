package game;

import org.la4j.vector.dense.BasicVector;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.EditField;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;


/**
 * Class for displaying the side menu in the game.
 * @author Samuel
 *
 */
public class Sidemenu extends Widget {

	/**
	 * TWL Button for exiting game.
	 */
	private Button exitGameButton;
	/**
	 * Array of labels for displaying upcoming flight and title Text objects.
	 */
	private Label[] labelArray = new Label[6];
	/**
	 * Width of SideMenu, used for positioning other items elsewhere.
	 */
	public static final int width = 800;

	/**
	 * Returns the width of the virtual display - the width of the side menu.
	 * @return width
	 */
	public static double remainingDisplayWidth() {
		return Drawables.virtualDisplaySize().get(0) - width;
	}

	/**
	 * Creates and displays the exit game button as transparent button over the
	 * button image in the background image.
	 */
	private void createButton() {
		exitGameButton = new Button("");
		exitGameButton.setTheme("button");
		exitGameButton.addCallback(new Runnable() {
			public void run() {
				System.out.println("Game should exit here.");
			}
		});
		add(exitGameButton);
	}

	/**
	 * Set;s up the labels that will display upcoming flights and adds them to 
	 * the display with some place holder text that will be immediately 
	 * overwritten on the first call of draw below.
	 */
	private void setupLabels() {
		for (int i = 0; i < labelArray.length; i++) {
			labelArray[i] = new Label();
			labelArray[i].setText("Will auto-populate from Planes Array");
			add(labelArray[i]);
		}
	}

	/**
	 * Lays out the exit button and the labels.
	 */
	protected void layout() {
		// button.adjustSize(); //Calculate optimal size instead of manually
		// setting it
		System.out.println(Drawables.virtualDisplaySize().get(0) + " " + Display.getWidth());
		for (int i = 0; i < labelArray.length; i++) {
            labelArray[i].setPosition(0, 110 + 20 * i);
            labelArray[i].setSize(200, 20);
		}
		exitGameButton.setPosition(35, 554);
		exitGameButton.setSize(130, 45);
	}

	/**
	 * Constructor for the side menu.  Adds itself to Drawables and creates 
	 * the buttons and labels, then initialises some text.
	 */
	public Sidemenu() {

		Drawables.add(this);

		createButton();
		setupLabels();

		labelArray[0].setText("Number - Entry - Time");
	}

	/**
	 * Draw the Sidemenu - called regularly to update the text labels in the 
	 * side menu which contain the information on when a plane is incoming.
	 */
	public void drawSidemenu() {
		int numberOfFuturePlanes = FuturePlanes.futurePlanes.size();
		for (int i = 0; i < 5; i++) {
			String tempString = "";
			if (i < numberOfFuturePlanes) {
				tempString = FuturePlanes.futurePlanes.get(i).getFnumber()
						+ " - "
						+ FuturePlanes.futurePlanes.get(i).getEntryPoint()
								.sidemenuString() + " - "
						+ FuturePlanes.futurePlanes.get(i).timeTillAppears;
			}
			labelArray[i + 1].setText(tempString);

		}
	}
}
