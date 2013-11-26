package engine;

import java.io.IOException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

public class Audible {

	public static void playSound(String path, boolean looping, float volume) {
		try {
			Audio sound = SoundStore.get().getWAV(path);
			SoundStore.get().setSoundVolume(volume);
			sound.playAsSoundEffect(1, 1, looping); // (pitch gain looping)
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}