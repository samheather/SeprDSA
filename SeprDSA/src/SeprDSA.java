import engine.Audible;
import engine.Drawables;
import engine.Input;
import game.Plane;
import game.Map;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

public class SeprDSA {
	public static void main(String[] args) {
		Drawables.initialise(1024, 640);
		Display.setTitle("Dat flying game");
		SoundStore.get().init();
		SoundStore.get().setCurrentMusicVolume(9.0f);


		Plane p = new Plane();
		Map m = new Map();
		// Audible.playSound("sounds/arribba.wav", true, 0.1f);
		// Audible.playSound("sounds/Booboo.wav", true, 0.5f);
		// Audible.playSound("sounds/Arf.ogg", true, 0.5f);

		while (true) {
			Input.logic();
			Drawables.logic();
			/*

			if (Display.isCloseRequested()) { // If x is clicked you should
												// clear your things.
				Display.destroy();
				AL.destroy();
				System.exit(0);
			}
			*/
		}
	}

}
