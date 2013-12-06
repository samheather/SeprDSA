package engine.graphics;

import java.lang.Comparable;

public interface Drawable extends Comparable<Drawable> {
	Drawing draw();

	public double getZ();

	@Override
	public int compareTo(Drawable o);

}
