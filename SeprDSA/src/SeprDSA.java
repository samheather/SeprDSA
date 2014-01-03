import engine.graphics.Drawables;
import engine.graphics.display.Window;
import engine.input.Input;
import engine.physics.Physicals;
import game.EntryExitPoint;
import game.FuturePlane;
import game.Leaderboard;
import game.Plane;
import game.Map;
import game.WayPoint;
import java.util.Random;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

public class SeprDSA {

	private static int pixelsFromEdge = 100;

	public static void main(String[] args) {
		Random randomgen = new Random(System.currentTimeMillis());
		Drawables.initialise(new Window(1640, 640), 1064, 640);
		Display.setTitle("Dat flying game");
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);

		Map m = new Map();
		FuturePlane fp = new FuturePlane();
		Leaderboard l = new Leaderboard();

		// Audible.playSound("sounds/arribba.wav", true, 0.1f);
		// Audible.playSound("sounds/Booboo.wav", true, 0.5f);
		// Audible.playSound("sounds/Arf.ogg", true, 0.5f);

		for (int i = 1; i <= 5; i++) { // Random EntryExits
			new EntryExitPoint(new BasicVector(new double[] { 0, 0, 0 }), 0,
					360, i);
		}

		new EntryExitPoint(new BasicVector(new double[] { -170, -48, 0 }), 0,
				10, 0); // Landing Strip

		for (Integer i = 1; i <= 10; i++) { // Random waypoints
			new WayPoint(new BasicVector(new double[] {
					(randomgen.nextDouble() - 0.5)
							* (Display.getWidth() - pixelsFromEdge),
					(randomgen.nextDouble() - 0.5)
							* (Display.getHeight() - pixelsFromEdge),
					randomgen.nextDouble() * 20 }),i.toString());
		}

		while (true) {
			Physicals.logic(1);
			Input.logic();
			Drawables.logic();

			if (Display.isCloseRequested()) { // If x is clicked you should
				AL.destroy(); // clear your things.
				Display.destroy();
				System.exit(0);
			}

		}
	}

}
