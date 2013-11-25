package engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import java.util.List;

class Input {

	private static List<Keyboardable> keyboardables;
	static {
		keyboardables = new ArrayList<Keyboardable>();
	}
	
	private static List<Clickable> clickables;
	static {
		clickables = new ArrayList<Clickable>();
	}
	
	public void addClickable(Clickable x){
		clickables.add(x);
	}
	public void removeClickable(Clickable x){
		clickables.remove(x);
	}
	public void addKeyboardable(Keyboardable x){
		keyboardables.add(x);
	}
	public void removeKeyboardabl(Keyboardable x){
		keyboardables.remove(x);
	}
	
	public void logic(){
		while(Keyboard.next()){
			if (Keyboard.getEventKeyState()){
				int key = Keyboard.getEventKey();
				for (int i = 0 ; i < keyboardables.size() ; i++){
					keyboardables.get(i).handleKeyboard(key);
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


