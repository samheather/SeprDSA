
import engine.Audible;
import engine.Drawables;
import game.Plane;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

public class SeprDSA {

    public static void main(String[] args) {
	Drawables.initialise(800, 600);
	SoundStore.get().init();
	SoundStore.get().setMusicOn(true);
	
	Plane p = new Plane();
	Audible.playSound("sounds/arribba.wav", true);
	SoundStore.get().setCurrentMusicVolume(9.0f);
	
	while (true) {
	    Drawables.logic();
	    
	    
	    if (Display.isCloseRequested()){ //If x is clicked you should clear your things.
	    	Display.destroy();
	    	AL.destroy();
	    	System.exit(0);
	    }
	}

    }

}

