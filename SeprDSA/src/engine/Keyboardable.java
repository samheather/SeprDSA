package engine;

import java.util.List;

public interface Keyboardable {

	void handleKeyboard(int key, boolean pressed);

	List<Integer> keys();
}
