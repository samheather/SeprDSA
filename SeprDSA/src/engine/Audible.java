package engine;

import java.io.IOException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

public class Audible {

	public static void playSound(String path, boolean looping, float volume) {
		try {
			SoundStore.get().setSoundVolume(volume);
			if (path.endsWith(".wav")) {
				Audio sound = SoundStore.get().getWAV(path);
				sound.playAsSoundEffect(1, 1, looping); // (pitch gain looping)
			} else if (path.endsWith(".ogg")) {
				Audio sound = SoundStore.get().getOgg(path);
				sound.playAsSoundEffect(1, 1, looping);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}