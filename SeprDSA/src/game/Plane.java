package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.SeprDSA;

import org.lwjgl.input.Keyboard;
import org.la4j.vector.Vector;

import engine.graphics.*;
import engine.graphics.drawing.Drawing;
import engine.graphics.drawing.Font.Alignment;
import engine.graphics.drawing.Texture;
import engine.graphics.drawing.primitives.*;
import engine.input.Clickable;
import engine.input.Input;
import engine.input.Keyboardable;
import engine.physics.Physical;
import engine.physics.Physicals;

import engine.Timing;

import org.la4j.vector.dense.*;

public class Plane implements Drawable, Keyboardable, Physical, Clickable {
	
	private static Random randomgen = new Random();
	private double x; // Should be pixel values for x,y
	private double y;
	private double z;
	private Vector position = new BasicVector(new double[] { 0, 0, 0 });
	private Vector velocity = new BasicVector(new double[] { 0, 0, 0 });
	private float rotation = 0.0f;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private double radius = 50;
	private int randomImage = randomgen.nextInt(Images.planes.length);
	private int size = 60;
	private String number;
	private Text numbertext;
	private int speed = randomgen.nextInt(10);
	private ArrayList <WayPoint> wayPointList;
	private EntryExitPoint exitPoint;
	private int score;
	private Vector endLine = new BasicVector(new double[] { 0, 0, 0 });
	private boolean lineExists = false;

	public Plane(String fnumber, ArrayList<WayPoint> pointList, EntryExitPoint startPoint, EntryExitPoint endPoint) {
		number = fnumber;
		wayPointList = pointList;
		exitPoint = endPoint;
		score = wayPointList.size()*10;
		//position = enterPoint.getPos();
		setPos(startPoint.getPos());
		numbertext = new Text(fnumber, Fonts.planeFont, Alignment.CENTRED);
		Drawables.add(this);
		Physicals.add(this);
		Planes.add(this);
		Input.addKeyboardable(this);
		Input.addClickable(this);
	}

	public String toString() {
		return "Plane" + number;
	}
	
	public void updateWaypoints() {
		if (wayPointList.size() > 0){
			System.out.println(wayPointList.get(0).toString());
			wayPointList.remove(0);
			// update flight plan stuff too at some point
		}
	}
	
	public WayPoint getNextWayPoint() {
		if (wayPointList.size() > 0) {
		return wayPointList.get(0);
		} else{
			return null;
		}
	}
	
	public EntryExitPoint getExitPoint() {
		if (exitPoint != null) {
			return exitPoint;
		}else{
			return null;
		}
	}
	
	public ArrayList<WayPoint> getWayPoints() {
		return wayPointList;
	}
	
	public String getFNumber() {
		return number;
	}

	public Drawing draw() {
		if (left) {
			x -= 10.0;
		}
		if (right) {
			x += 10.0;
		}
		if (up) {
			y += 10.0;
		}
		if (down) {
			y -= 10.0;
		}

		return new
			 Sprite(Images.planes[randomImage])
			.scale(size / Images.planes[randomImage].size().get(0))
			.rotate(rotation)
			.overlay (
				 numbertext
				.red(1.0).blue(1.0).green(1.0).alpha(0.75)
				.translate(new BasicVector(new double[] {0, -40}))
			 )
			.translate(new BasicVector(new double[] { x, y }))
			.overlay ((lineExists ? new Line(getPos(),endLine) : new Identity()).alpha(1.0).red(1.0));
	}

	public Vector getPos() {
		return new BasicVector(new double[] { x, y, z });
	}

	public void setPos(Vector newPos) {
		x = newPos.get(0);
		y = newPos.get(1);
		z = newPos.get(2);
	}

	public Vector getVel() {
		return velocity;
	}

	public void setVel(Vector newVel) {
		velocity = newVel;
	}

	public boolean isCollidingPos(Vector checkPos) {
		return Math.sqrt(Math.pow(x - checkPos.get(0), 2)
				+ Math.pow(y - checkPos.get(1), 2)) < radius;
	}

	public boolean isCollidingObj(Physical checkObj) {
		return Math.sqrt(Math.pow(x - checkObj.getPos().get(0), 2)
				+ Math.pow(y - checkObj.getPos().get(1), 2)) < radius;
	}

	public float getBearing() {
		return rotation;
	};
	
	public void setBearing(float newBearing){
		float oldBearing = getBearing();
//		System.out.println("Rotation" + rotation);
//		System.out.println("Old "+oldBearing);
//		System.out.println("New "+newBearing);
//		System.out.println("BearingChange "+Math.abs(newBearing - oldBearing));
//		for (int i = 1; i <= Math.abs(newBearing - oldBearing); i++){
//			double localTime = 0;
			//if (newBearing > oldBearing){

		Timing.doNTimesIn(10, 1000, new Runnable(){
			public void run() {
				System.out.println("cat");
			}
		});

				rotation = newBearing;
				setVel(new BasicVector(new double[] {Math.cos(Math.toRadians(newBearing )),Math.sin(Math.toRadians(newBearing )),0}).multiply(0));
//			} else{
//				setVel(new BasicVector(new double[] {Math.cos(Math.toRadians(oldBearing + i)),Math.sin(Math.toRadians(oldBearing + i)),0}).multiply(50));
//			}
			//while (localTime < 100) {localTime += SeprDSA.timer;}
			
//		}
	};

	public void destroy() {
		for (int i = size; i > 0; i--) {
			double localTime = 0;
			size -= 1;
			//while (localTime < 5) {localTime += SeprDSA.timer;}
		}
		Planes.remove(this);
		Input.removeKeyboardable(this);
		Input.removeClickable(this);
		Physicals.remove(this);
		Drawables.remove(this);
	}

	@Override
	public void handleKeyboard(int key, boolean pressed) {

		if (key == Keyboard.KEY_LEFT || key == Keyboard.KEY_A) {
			left = pressed;
		} else if (key == Keyboard.KEY_RIGHT || key == Keyboard.KEY_D) {
			right = pressed;
		} else if (key == Keyboard.KEY_UP || key == Keyboard.KEY_W) {
			up = pressed;
		} else if (key == Keyboard.KEY_DOWN || key == Keyboard.KEY_S) {
			down = pressed;
		}
	}

	@Override
	public List<Integer> keys() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(Keyboard.KEY_LEFT);
		result.add(Keyboard.KEY_RIGHT);
		result.add(Keyboard.KEY_UP);
		result.add(Keyboard.KEY_DOWN);
		result.add(Keyboard.KEY_W);
		result.add(Keyboard.KEY_A);
		result.add(Keyboard.KEY_S);
		result.add(Keyboard.KEY_D);
		return result;
	}

	public double getZ() {
		return z;
	}

	public int compareTo(Drawable o) {
		return (int) (this.getZ() - o.getZ());
	}

	public void clickDown(int button, Vector pos) {
		//System.out.println(number);
		endLine = pos;
		lineExists = true;
	}

	public void clickUp(int button) {
		lineExists = false;
		Vector planePos = new BasicVector( new double[]{getPos().get(0),getPos().get(1)});
		Vector temp = planePos.subtract(endLine);
		float angle = (float) Math.toDegrees(Math.atan(temp.get(1)/temp.get(0)));
		angle = planePos.get(0) < endLine.get(0) ? angle : angle + 180;
		setBearing((float)angle);
	}

	public void clickAway() {
		//System.out.println("Click away");
	}

	public void move(Vector newPos) {
		//System.out.println("Move");
		endLine = newPos;
	}
}
