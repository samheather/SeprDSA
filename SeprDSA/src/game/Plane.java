
package game;

import engine.*;
import org.la4j.vector.dense.*;

public class Plane implements Drawable {
    public Plane() {
	Drawables.add(this);
    }
    public Sprite draw() {
	return new Sprite(Images.plane, new BasicVector(new double[]{400.0, 300.0}), 1.0f, 0.0f);
    }
}
