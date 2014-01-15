package engine.input;

import java.util.List;

public interface Keyboardable {
	
	/**
	 * Detects what key has been pressed and acts on the button pressed
	 * @param key
	 * @param pressed
	 */
	void handleKeyboard(int key, boolean pressed);

	/**Stores the list of keys that perform an action when pressed
	 * 
	 * @return
	 */
	List<Integer> keys();
}
