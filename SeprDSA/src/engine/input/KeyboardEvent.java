package engine.input;

public class KeyboardEvent {

	private final int keyCode;
	private final char keyChar;
	private final boolean pressed;

	public int keyCode() {
		return keyCode;
	}

	public char keyChar() {
		return keyChar;
	}

	public boolean pressed() {
		return pressed;
	}

	public KeyboardEvent(int keyCode, char keyChar, boolean pressed) {
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.pressed = pressed;
	}
}
