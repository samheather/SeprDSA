package engine.audio;

import java.io.IOException;
import org.newdawn.slick.openal.SoundStore;

/** 8 or 16 bit encoding between 8 kHz and 48 kHz is what javax supports. 
 * If there are errors, it's your sound file. Use Audacity to convert it to a 16
 * bit format.
 */

public class Audio {
	/**
	 * Plays sound with path based on path to file, whether to loop, and volume
	 * <p>
	 * First configures volume.  Then, depending on .wav or .ogg, loads the file
	 * using apporpriate loader.  Sound then played using external soundLibrary,
	 * passing parameters originally passed in.
	 * @param path
	 * @param looping
	 * @param volume
	 */
	public static void playSound(String path, boolean looping, float volume)
	{
		try {
			// Have to set of everything to a specific volume
			SoundStore.get().setSoundVolume(volume);
			if (path.endsWith(".wav")) {
				org.newdawn.slick.openal.Audio sound = SoundStore.get().getWAV(
						path); // Special method to play .wav sound.
				sound.playAsSoundEffect(1, 1, looping); // (pitch gain looping)
			} else if (path.endsWith(".ogg")) {
				org.newdawn.slick.openal.Audio sound = SoundStore.get().getOgg(
						path); // Support for .ogg files.
				sound.playAsSoundEffect(1, 1, looping);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}