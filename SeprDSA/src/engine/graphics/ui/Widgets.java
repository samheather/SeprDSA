package engine.graphics.ui;

import java.util.ArrayList;
import java.util.List;

import de.matthiasmann.twl.Widget;

public class Widgets {
	static List<Widget> widgets;
	static {
		widgets = new ArrayList<Widget>();
	}
	public void add(Widget w) {
		widgets.add(w);
	}
	public void remove(Widget w) {
		widgets.remove(w);
	}
	

}
