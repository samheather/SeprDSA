
import engine.Drawables;
import engine.Input;
import game.Plane;

public class SeprDSA {

    public static void main(String[] args) {
	Drawables.initialise(800, 600);

	Plane p = new Plane();
	
	while (true) {
		Input.logic();
	    Drawables.logic();
	}

    }

}

