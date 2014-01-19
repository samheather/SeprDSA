package engine.input;

public class MouseEvent {
	private int x;
	private int y;
	private int button;
	private boolean pressed;
	
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
