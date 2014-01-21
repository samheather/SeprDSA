package engine.physics;

import org.la4j.vector.Vector;

/**
 * Interface for objects that implement Physical
 * 
 * @author sbh514
 * 
 */
public interface Physical {
	/**
	 * Get position Vector of this Physical object.
	 * 
	 * @return Vector
	 */
	Vector getPos();

	/**
	 * Set new position Vector of this Physical object.
	 * 
	 * @param newPos
	 */
	void setPos(Vector newPos);

	/**
	 * Get Velocity of this Physical object.
	 * 
	 * @return Vector
	 */
	Vector getVel();

	/**
	 * Set Velocity of this Physical object.
	 * 
	 * @param newVel
	 */
	void setVel(Vector newVel);

	/**
	 * Check if Physical object is colliding with position in Vector.
	 * 
	 * @param checkPos
	 * @return boolean indicating wheter the Physical is colliding with a Vector point
	 */
	boolean isCollidingPos(Vector checkPos);

	/**
	 * Check if Physical object is colliding with other Physical object.
	 * 
	 * @param checkObj
	 * @return boolean that indicated if Physical and checkObj are colliding
	 */
	boolean isCollidingObj(Physical checkObj);

	/**
	 * Get current bearing of Physical object.
	 * 
	 * @return float
	 */
	float getBearing();

	public void setBearing(final float newBearing);

	float targetBearing();

	float rotVel();
}
