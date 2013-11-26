package engine;

import java.util.List;


 public interface Keyboardable {
	
	void handleKeyboard(int key);
	
	List<Integer> keys();
}
