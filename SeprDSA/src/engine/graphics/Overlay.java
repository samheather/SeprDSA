package engine.graphics;

public class Overlay extends Drawing {

	private Drawing top;
	private Drawing bottom;

	public Overlay(Drawing bottom, Drawing top) {
		// TODO Auto-generated constructor stub
		this.bottom = bottom;
		this.top = top;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		bottom.render();
		top.render();

	}

}
