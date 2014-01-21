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

/**
 * Will handle input from mouse and keyboard with Logic method
 */
public class Input {

	private static List<Keyboardable> keyboardables = new ArrayList<Keyboardable>();
	private static Map<Integer, List<Keyboardable>> map = new HashMap<Integer, List<Keyboardable>>();
	private static Clickable current;
	private static List<Clickable> clickables = new ArrayList<Clickable>();
	private static List<Scrollable> scrollables = new ArrayList<Scrollable>();
	private static ArrayList<MouseEvent> mouseEvents = new ArrayList<MouseEvent>();
	private static ArrayList<KeyboardEvent> keyboardEvents = new ArrayList<KeyboardEvent>();
	
	/**
	 * Adds a Clickable object to an ArrayList to handle mouse input
	 * @param x A Clickable object that will have clicking events
	 */
	public static void addClickable(Clickable x) {
		clickables.add(x);
	}
	
	/**
	 * Stops x from having the mouse interact with it with buttons
	 * @param x Clickable to stop having mouse button interaction
	 */
	public static void removeClickable(Clickable x) {
		clickables.remove(x);
	}

	/**
	 * Allow x to interact with mouse wheel
	 * @param x A Scrollable that you want to respond to mouse wheel events
	 */
	public static void addScrollable(Scrollable x) {
		scrollables.add(x);
	}

	/**
	 * Stops x from responding to mouse wheel
	 * @param x Scrollable object to stop responding to mouse wheel
	 */
	public static void removeScrollable(Scrollable x) {
		scrollables.remove(x);
	}

	/**
	 * Getter for mouse events
	 * @return A list of mouse events
	 */
	public static ArrayList<MouseEvent> mouseEvents() {
		return mouseEvents;
	}

	/**
	 * Getter for keyboard events
	 * @return An ArrayList of keyboard events
	 */
	public static ArrayList<KeyboardEvent> keyboardEvents() {
		return keyboardEvents;
	}

	/**
	 * Adds a Keyboardable object to ArrayList of objects that respond to keyboard
	 * @param x a Keyboardable object that is to respond to keyboard input
	 */
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

	/**
	 * Stops x from responding to keyboard input
	 * @param x Keyboardable object to stop responding to input from keyboard
	 */
	public static void removeKeyboardable(Keyboardable x) {
		keyboardables.remove(x);
	}

	/**
	 * Handles all logic associated with input from mouse and keyboard
	 * <p>
	 * (Is to be called every frame to ensure handling input is fine)
	 */
	public static void logic() {
		ArrayList<KeyboardEvent> ke = new ArrayList<KeyboardEvent>();
		while (Keyboard.next()) {
			ke.add(new KeyboardEvent(Keyboard.getEventKey(), Keyboard
					.getEventCharacter(), Keyboard.getEventKeyState()));
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
		keyboardEvents = ke;

		ArrayList<MouseEvent> me = new ArrayList<MouseEvent>();
		while (Mouse.next()) {
			me.add(new MouseEvent(Mouse.getEventX(), Mouse.getEventY(), Mouse
					.getEventButton(), Mouse.getEventButtonState()));
			int button = -1;
			Vector pos = Drawables.windowCoords(new BasicVector(new double[] {
					Mouse.getEventX(), Mouse.getEventY() }));
			if ((button = Mouse.getEventButton()) == -1) {

				int scroll = Mouse.getEventDWheel();
				if (scroll != 0) {
					for (Scrollable i : scrollables) {
						i.scroll(scroll);
					}
				} else {
					if (current != null) {
						current.move(pos);
					}
				}
			} else {
				boolean down = Mouse.getEventButtonState();
				if (down) {
					List<Drawable> collisions = Drawables.collisions(pos);
					Clickable c = null;
					for (int i = collisions.size() - 1; i >= 0; i--) {
						if (collisions.get(i) instanceof Clickable) {
							c = (Clickable) collisions.get(i);
							/*if (c instanceof Plane) {
								System.out.println(((Plane) c).getFNumber());
								System.out.println(pos);
							}*/
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
		mouseEvents = me;
	}
}
