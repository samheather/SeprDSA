package engine.graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import engine.graphics.sprite.Texture;

public class Text extends Sprite {

	public Text(String text, Font font) {
		super(drawText(text, font));
		// TODO Auto-generated constructor stub
	}

	public static Texture drawText(String text, Font font) {
		double x = Math.round(Drawables.virtualDisplaySize().get(0));
		double y = Math.round(Drawables.virtualDisplaySize().get(1));
		BufferedImage image = new BufferedImage((int) x, (int) y,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setFont(font);
		
		
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		
		graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics.setRenderingHint(RenderingHints.KEY_DITHERING,
				RenderingHints.VALUE_DITHER_ENABLE);
		
		graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);


		double sizex = graphics.getFontMetrics().stringWidth(text);
		double sizey = graphics.getFontMetrics().getHeight();
		graphics.drawString(text, (int) ((x / 2.0) - (sizex / 2.0)),
				(int) ((y / 2.0) + (sizey / 2.0)));
		return new Texture(image);
	}

}
