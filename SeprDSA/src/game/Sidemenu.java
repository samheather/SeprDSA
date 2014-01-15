package game;

import java.io.IOException;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.primitives.Sprite;
import engine.graphics.drawing.primitives.Text;
import engine.input.Clickable;

public class Sidemenu extends Widget {

	private Text titleText;
	private Text[] upcomingFlights = new Text[5];
	private Button button;
	private DialogLayout helloPanel;
	
	private void createButton() {
	    button = new Button("Epic button");
	    button.setTheme("button");
	    button.addCallback(new Runnable() {
            public void run() {
                    System.out.println("some action!");
            }
	    });
	    add(button);
	}

	private void createHelloPanel() {
		helloPanel = new DialogLayout();
		//helloPanel.setTheme("panel");

		Label label = new Label();
		label.setText("Hello world Panelcxvcxvxcvcvcxvxvxcv\nsdsadsad");

		helloPanel.setHorizontalGroup(helloPanel.createParallelGroup(label));
		helloPanel.setVerticalGroup(helloPanel.createParallelGroup(label));
		helloPanel.setVisible(true);
		//add(helloPanel);
	}
	protected void layout(){
		//helloPanel.setPosition(0, 0);
		//helloPanel.setSize(1000, 1000);
		//helloPanel.adjustSize();
	    button.setPosition(0, 0);
	    button.setSize(100, 100);
	    //button.adjustSize(); //Calculate optimal size instead of manually setting it
	}
	
	public Sidemenu() {
		try {
			Drawables.add(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		this.titleText = new Text("Number - Entry Point - Time",
				Fonts.sideMenuTitle, Alignment.CENTRED);

		upcomingFlights[0] = new Text("abc", Fonts.sideMenuText,
				Alignment.CENTRED);
		upcomingFlights[1] = new Text("abc", Fonts.sideMenuText,
				Alignment.CENTRED);
		upcomingFlights[2] = new Text("abc", Fonts.sideMenuText,
				Alignment.CENTRED);
		upcomingFlights[3] = new Text("abc", Fonts.sideMenuText,
				Alignment.CENTRED);
		upcomingFlights[4] = new Text("abc", Fonts.sideMenuText,
				Alignment.CENTRED);
		createButton();
		createHelloPanel();
	}

	public Drawing draw() {
		for (int i = 1; i <= Math.min(5, Planes.planes.size()); i++) {
			String tempString = Planes.planes.get(i - 1).getFNumber() + "E/E"
					+ "TIME";
			upcomingFlights[i - 1] = new Text(tempString, Fonts.sideMenuText,
					Alignment.CENTRED);
		}
		return new
			 Sprite(Images.homeButton)
			.scale(200 / Images.homeButton.size().get(0))
			.red(1.0).blue(1.0).green(1.0).alpha(0.75)
//			.translate(new BasicVector(new double[] {0, -40}))
			.overlay(titleText
					.overlay(upcomingFlights[0]	
					.overlay(upcomingFlights[1]
					.overlay(upcomingFlights[2]
					.overlay(upcomingFlights[3]
					.overlay(upcomingFlights[4]))))))
			.translate(new BasicVector(new double[] { 320, 320 }));

	}
/*
	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Drawable o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clickDown(int button, Vector pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickUp(int button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickAway() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Vector newPos) {
		// TODO Auto-generated method stub

	}
*/
}
