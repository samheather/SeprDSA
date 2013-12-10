package engine.graphics.text;

public class Text extends engine.graphics.Drawing {

	public Text(String text, engine.graphics.text.Font font) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.font = font;
	}

	private String text;
	private engine.graphics.text.Font font;

	@Override
	public void render() {
		// TODO Auto-generated method stub
		font.render(text);

	}

}
