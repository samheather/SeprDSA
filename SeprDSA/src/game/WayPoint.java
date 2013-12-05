package game;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

import engine.Drawable;
import engine.Drawables;
import engine.Physical;
import engine.Physicals;
import engine.Sprite;



	public class WayPoint implements Drawable, Physical {
		
		private Vector position = new BasicVector( new double[] {0,0,0});
		private int radius = 50;
		
		public WayPoint(Vector pos) {
			position = pos;
			Drawables.add(this);
			Physicals.add(this);
		}
		public Sprite draw(){
			return new Sprite(Images.waypoint, new BasicVector(
					new double[] { position.get(0), position.get(1) }), 1.0f,
					0.0f);
		}

		public Vector getPos() {
			return position;
		}

		public void setPos(Vector newPos) {
			position = newPos;
			
		}

		public Vector getVel() {
			return new BasicVector(new double[] {0,0,0});
		}

		public void setVel(Vector newVel) {
		}
		
		public boolean isCollidingPos(Vector checkPos) {
			return false;
		}

		public boolean isCollidingObj(Physical checkObj) {
			return false;
		}
	}

