
package engine;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.la4j.vector.*;
import org.la4j.vector.dense.*;
import java.io.IOException;

public class Image {
    private Texture internal;
    public Image(String fileType, String filePath) {
	try {
	    internal = TextureLoader.getTexture(fileType, ResourceLoader.getResourceAsStream(filePath));
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }
    public void bind() {
	internal.bind();
    }
    public Vector size() {
	return new BasicVector(new double[]{internal.getTextureWidth(), internal.getTextureHeight()});
    }
}



