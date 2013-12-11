package engine.graphics.sprite;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image extends Texture {

	public Image(String filePath) throws IOException {
		/*
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		*/
		super(ImageIO.read(new File(filePath)));
	}
}
