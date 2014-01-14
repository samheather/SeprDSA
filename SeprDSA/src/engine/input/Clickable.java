package engine.input;

import org.la4j.vector.Vector;

public interface Clickable {

	void clickDown(int button, Vector pos);
	void clickUp(int button);
	void clickAway();
	void move(Vector newPos);

}
