
import engine.Drawables;
import game.Plane;
import game.Map;

public class SeprDSA {

    public static void main(String[] args) {
	Drawables.initialise(1024, 640);

	Plane p = new Plane();
	Map m = new Map();
	
	while (true) {
	    Drawables.logic();
	}

    }

}

