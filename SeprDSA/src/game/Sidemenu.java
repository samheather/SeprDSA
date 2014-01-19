package game;

import org.la4j.vector.dense.BasicVector;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.EditField;

import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;

public class Sidemenu extends Widget {

	private Text titleText;
	private Text[] upcomingFlights = new Text[5];
	private Button exitGameButton;
	private Label[] labelArray = new Label[6];
	private Label changeAltitudeLabel;
	private DialogLayout helloPanel;
	private EditField newAltitudeField;
	public int startOfSideMenuX = 0;

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

	private void setupLabels() {
		for (int i = 0; i < labelArray.length; i++) {
			labelArray[i] = new Label();
			labelArray[i].setText("Will auto-populate from Planes Array");
			add(labelArray[i]);
		}

		changeAltitudeLabel = new Label();
		changeAltitudeLabel.setText("New Altitude:");
		add(changeAltitudeLabel);
	}

	private void setupTextField() {
		newAltitudeField = new EditField();
		add(newAltitudeField);
	}

	private void createHelloPanel() {
		// helloPanel = new DialogLayout();
		// helloPanel.setTheme("panel");

		System.out.println("This is a stub");

		// helloPanel.setHorizontalGroup(helloPanel.createParallelGroup(label));
		// helloPanel.setVerticalGroup(helloPanel.createParallelGroup(label));
		// helloPanel.setVisible(true);
		// add(helloPanel);
	}

	protected void layout() {
		// button.adjustSize(); //Calculate optimal size instead of manually
		// setting it
		for (int i = 0; i < labelArray.length; i++) {
			labelArray[i].setPosition(startOfSideMenuX, 110 + 20 * i);
			labelArray[i].setSize(200, 20);
		}

		int altitudeControls = 360;
		changeAltitudeLabel.setPosition(startOfSideMenuX, altitudeControls);
		changeAltitudeLabel.setSize(200, 20);

		newAltitudeField.setPosition(startOfSideMenuX + 50,
				altitudeControls + 25);
		newAltitudeField.setSize(100, 30);

		exitGameButton
				.setPosition(startOfSideMenuX + 35, 554);
		exitGameButton.setSize(130, 45);
	}

	public Sidemenu() {

		Drawables.add(this);

		createButton();
		setupLabels();
		setupTextField();
		// createHelloPanel();

		labelArray[0].setText("Number - Entry - Time");
	}

	public void drawSidemenu() {
		// System.out.println("Sidemenu draw ran " +
		// FuturePlanes.futurePlanes.size());
		int numberOfFuturePlanes = FuturePlanes.futurePlanes.size();
		for (int i = 0; i < 5; i++) {
			String tempString = "";
			if (i < numberOfFuturePlanes) {
				tempString = FuturePlanes.futurePlanes.get(i).getFnumber()
						+ "E/E" + "TIME";

			}
			labelArray[i + 1].setText(tempString);

		}
	}
	/*
	 * @Override public double getZ() { // TODO Auto-generated method stub
	 * return 0; }
	 * 
	 * @Override public int compareTo(Drawable o) { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public void clickDown(int button, Vector pos) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void clickUp(int button) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * @Override public void clickAway() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void move(Vector newPos) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 */
}
