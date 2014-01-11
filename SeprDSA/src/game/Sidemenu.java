package game;

import org.la4j.vector.dense.BasicVector;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Texture;
import engine.graphics.drawing.primitives.Sprite;
import engine.input.Clickable;

public class Sidemenu implements Drawable, Clickable{
	
	private Texture titleText;
	private Texture[] upcomingFlights = new Texture[5];
	
	public Sidemenu() {
		Drawables.add(this);
		this.titleText = new Texture("Number - Entry Point - Time", Fonts.sideMenuTitle);
		
		upcomingFlights[0] = new Texture("abc", Fonts.sideMenuText);
		upcomingFlights[1] = new Texture("abc", Fonts.sideMenuText);
		upcomingFlights[2] = new Texture("abc", Fonts.sideMenuText);
		upcomingFlights[3] = new Texture("abc", Fonts.sideMenuText);
		upcomingFlights[4] = new Texture("abc", Fonts.sideMenuText);
	}
	
	public Drawing draw() {
		for (int i = 1; i <= Math.min(5,Planes.planes.size()); i++) {
			String tempString = Planes.planes.get(i-1).getFnumber() + "E/E" + "TIME";
			upcomingFlights[i-1] = new Texture(tempString, Fonts.sideMenuText);
		}
		return new
			 Sprite(Images.homeButton)
			.scale(200 / Images.homeButton.size().get(0))
			.red(1.0).blue(1.0).green(1.0).alpha(0.75)
//			.translate(new BasicVector(new double[] {0, -40}))
			.overlay(new Sprite(titleText)
					.overlay(new Sprite(upcomingFlights[0])		
					.overlay(new Sprite(upcomingFlights[1])
					.overlay(new Sprite(upcomingFlights[2])
					.overlay(new Sprite(upcomingFlights[3])
					.overlay(new Sprite(upcomingFlights[4])))))))
			.translate(new BasicVector(new double[] { 500, 500 }));

	}

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
	public void click(int button) {
		// TODO Auto-generated method stub
		
	}

}
