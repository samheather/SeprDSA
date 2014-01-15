package engine.input;

import java.util.HashMap;
import java.util.Map;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.graphics.Drawable;
import engine.graphics.Drawables;
import game.Plane;

import java.util.ArrayList;
import java.util.List;

public class Input {

	private static List<Keyboardable> keyboardables;
	private static Map<Integer, List<Keyboardable>> map;
	private static Clickable current;
	static {
		keyboardables = new ArrayList<Keyboardable>();
		map = new HashMap<Integer, List<Keyboardable>>();
	}

	private static List<Clickable> clickables;
	static {
		clickables = new ArrayList<Clickable>();
	}

	public static void addClickable(Clickable x) {
		clickables.add(x);
	}

	public static void removeClickable(Clickable x) {
		clickables.remove(x);
	}

	public static void addKeyboardable(Keyboardable x) {
		keyboardables.add(x);
		List<Integer> keys = x.keys();
		for (int i = 0; i < keys.size(); i++) {
			List<Keyboardable> handlers = map.get(keys.get(i));
			if (handlers == null) {
				handlers = new ArrayList<Keyboardable>();
				map.put(keys.get(i), handlers);
			}
			handlers.add(x);
		}

	}

	public static void removeKeyboardable(Keyboardable x) {
		keyboardables.remove(x);
	}

	public static void logic() {
		while (Keyboard.next()) {
			int key = Keyboard.getEventKey();
			List<Keyboardable> handlers = map.get(key);
			if (handlers == null) {
				continue;
			}
			for (int i = 0; i < handlers.size(); i++) {
				handlers.get(i)
						.handleKeyboard(key, Keyboard.getEventKeyState());
			}
		}
		while (Mouse.next()) {
			int button = -1;
			Vector pos = Drawables.windowCoords(new BasicVector(new double[] {
					Mouse.getEventX(), Mouse.getEventY() }));
			if ((button = Mouse.getEventButton()) == -1) {
				if (current != null) {
					current.move(pos);
				}
			} else {
				boolean down = Mouse.getEventButtonState();
				if (down) {
					List<Drawable> collisions = Drawables.collisions(pos);
					Clickable c = null;
					for (int i = collisions.size() - 1; i >= 0; i--) {
						if (collisions.get(i) instanceof Clickable) {
							c = (Clickable) collisions.get(i);
							if(c instanceof Plane) {
								System.out.println(((Plane) c).getFNumber());
								System.out.println(pos);
							}
							if (c == current) {
								c.clickDown(button, pos);
							} else {
								if (current != null)
									current.clickAway();
								current = c;
								c.clickDown(button, pos);
							}
							break;
						}
					}
					if (c == null) {
						if (current != null)
							current.clickAway();
						current = null;
					}

				} else {
					if (current != null)
						current.clickUp(button);
					current = null;
				}

			}
		}
	}
}
