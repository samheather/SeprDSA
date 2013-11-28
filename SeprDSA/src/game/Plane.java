
package game;

import engine.*;
import org.la4j.vector.dense.*;

public class Plane implements Drawable {
    public Plane() {
    	Drawables.add(this);
    }
    public Sprite draw() {
    	return new Sprite(Images.plane, new BasicVector(new double[]{512.0, 320.0}), 1.0f, 0.0f);
    }
}
