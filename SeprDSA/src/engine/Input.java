package engine;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import java.util.List;

public class Input {

	private static List<Keyboardable> keyboardables;
	private static Map<Integer, List<Keyboardable>> map;
	static {
		keyboardables = new ArrayList<Keyboardable>();
		map = new HashMap<Integer, List<Keyboardable>>();
	}
	
	private static List<Clickable> clickables;
	static {
		clickables = new ArrayList<Clickable>();
	}
	
	public static void addClickable(Clickable x){
		clickables.add(x);
	}
	public static void removeClickable(Clickable x){
		clickables.remove(x);
	}
	public static void addKeyboardable(Keyboardable x){
		keyboardables.add(x);
		List<Integer> keys = x.keys();
		for (int i = 0; i<keys.size(); i++) {
			List<Keyboardable> handlers = map.get(keys.get(i));
			if(handlers == null) {
				handlers = new ArrayList<Keyboardable>();
				map.put(keys.get(i), handlers);
			}
			handlers.add(x);
		}
		
	}
	public static void removeKeyboardable(Keyboardable x){
		keyboardables.remove(x);
	}
	
	public static void logic(){
		while(Keyboard.next()){
			if (Keyboard.getEventKeyState()){
				int key = Keyboard.getEventKey();
				List<Keyboardable> handlers = map.get(key);
				if(handlers == null) {
					continue;
				}
				for (int i = 0; i< handlers.size(); i++) {
					handlers.get(i).handleKeyboard(key);
					
				}
			}
		}
		while(Mouse.next()){
			if (Mouse.getEventButtonState()){
				int button = Mouse.getEventButton();
				for (int i = 0 ; i < clickables.size() ; i++){
					clickables.get(i).click(button);
				}
			}	
		}
	}
}


