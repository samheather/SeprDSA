
package engine;

import org.la4j.vector.*;

public class Sprite {
    public Image image;
    public Vector translation;
    public float scale;
    public float rotation;
    public Sprite(Image image_, Vector translation_, float scale_, 
    		float rotation_) {
	image = image_;
	translation = translation_;
	scale = scale_;
	rotation = rotation_;
    }
}

