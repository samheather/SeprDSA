import engine.Audible;
import engine.Drawables;
import engine.Input;
import engine.Physicals;
import engine.Window;
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

	public static void main(String[] args) {
		Random randomgen = new Random(System.currentTimeMillis());
		Drawables.initialise(new Window(1024, 640), 1024, 640);
		Display.setTitle("Dat flying game");
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);

		Map m = new Map();
		Plane p = new Plane(new BasicVector(new double[] {0,0,0}), 0.0f); // Plane(position,rotation)
		Plane p1 = new Plane(new BasicVector(new double[] {200,0,0}), 30.0f);
		Leaderboard l = new Leaderboard();
	
		// Audible.playSound("sounds/arribba.wav", true, 0.1f);
		// Audible.playSound("sounds/Booboo.wav", true, 0.5f);
		// Audible.playSound("sounds/Arf.ogg", true, 0.5f);
		
		for (int i = 1; i <10; i++){
			WayPoint w = new WayPoint(new BasicVector(new double[] {(randomgen.nextDouble()-0.5)*924,(randomgen.nextDouble()-0.5)*540,randomgen.nextDouble()*20}));
			System.out.println(w.getPos());
		}
		
		while (true) {
			Physicals.logic(0.01);
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
