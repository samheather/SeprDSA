package engine.physics;

import game.WayPoint;

import org.la4j.vector.*;
/**
 * Interface for objects that implement Physical
 * @author sbh514
 *
 */
public interface Physical {
	/**
	 * Get position Vector of this Physical object.
	 * @return
	 */
	Vector getPos();

	/**
	 * Set new position Vector of this Physical object.
	 * @param newPos
	 */
	void setPos(Vector newPos);

	/**
	 * Get Velocity of this Physical object.
	 * @return
	 */
	Vector getVel();

	/**
	 * Set Velocity of this Physical object.
	 * @param newVel
	 */
	void setVel(Vector newVel);

	/**
	 * Check if Physical object is colliding with position in Vector.
	 * @param checkPos
	 * @return
	 */
	boolean isCollidingPos(Vector checkPos);

	/**
	 * Check if Physical object is colliding with other Physical object.
	 * @param checkObj
	 * @return
	 */
	boolean isCollidingObj(Physical checkObj);

	/**
	 * Get current bearing of Physical object.
	 * @return
	 */
	float getBearing();
}
