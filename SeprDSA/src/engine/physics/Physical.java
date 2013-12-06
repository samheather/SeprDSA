package engine.physics;

import org.la4j.vector.*;

public interface Physical {
	Vector getPos();

	void setPos(Vector newPos);

	Vector getVel();

	void setVel(Vector newVel);

	boolean isCollidingPos(Vector checkPos);

	boolean isCollidingObj(Physical checkObj);
}
