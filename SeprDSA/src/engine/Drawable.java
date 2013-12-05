package engine;

import java.lang.Comparable;

public interface Drawable extends Comparable<Drawable> {
	Sprite draw();
	
	public double getZ();
	
	@Override
	public int compareTo(Drawable o);
	
}
