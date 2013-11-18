
package engine;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.la4j.vector.*;
import java.io.IOException;
import org.lwjgl.LWJGLException;

public class Drawables {
    private static List<Drawable> drawables;
    static {
	drawables = new ArrayList<Drawable>();
    }
    public static void add(Drawable d) {
	drawables.add(d);
    }
    public static void remove(Drawable d) {
	drawables.remove(d);
    }
    public static void initialise(int width, int height) {
	try {
	    Display.setDisplayMode(new DisplayMode(width,height));
	    Display.create();
	    Display.setVSyncEnabled(true);
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}

	GL11.glEnable(GL11.GL_TEXTURE_2D);
	GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	GL11.glEnable(GL11.GL_BLEND);
	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	GL11.glViewport(0,0,width,height);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glOrtho(0, width, height, 0, 1, -1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    public static void deinitialise() {
	Display.destroy();
    }
    public static void logic() {
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	for(int i = 0; i<drawables.size(); i++) {
	    Sprite s = drawables.get(i).draw();
	    s.image.bind();
	    GL11.glPushMatrix();
	    GL11.glRotatef(s.rotation, 0.0f, 0.0f, 1.0f);
	    GL11.glScalef(s.scale, s.scale, 0.0f);
	    GL11.glTranslated(s.translation.get(0), s.translation.get(1), 0.0);
	    GL11.glBegin(GL11.GL_QUADS);
	    Vector size = s.image.size();
	    GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2d(-size.get(0)/2.0, -size.get(1)/2.0);
	    GL11.glTexCoord2f(1, 0);
	    GL11.glVertex2d(size.get(0)/2.0, -size.get(1)/2.0);
	    GL11.glTexCoord2f(1, 1);
	    GL11.glVertex2d(size.get(0)/2.0, size.get(1)/2.0);
	    GL11.glTexCoord2f(0, 1);
	    GL11.glVertex2d(-size.get(0)/2.0, size.get(1)/2.0);
	    GL11.glEnd();
	    GL11.glPopMatrix();
	}
	Display.update();
    }
}
