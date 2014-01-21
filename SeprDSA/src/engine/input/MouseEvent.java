package engine.input;

/**
 * Stores a mouse event. Used internally to communicate with TWL.
 */
public class MouseEvent {
	private final int x;
	private final int y;
	private final int button;
	private final boolean pressed;

	public boolean pressed() {
		return pressed;
	}

	public int button() {
		return button;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public MouseEvent(int x, int y, int button, boolean pressed) {
		this.x = x;
		this.y = y;
		this.button = button;
		this.pressed = pressed;
	}
}
