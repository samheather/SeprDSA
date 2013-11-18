import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

public class SeprDSA {

	public static void main(String[] args) throws LWJGLException  {
		// TODO Auto-generated method stub
		/*for (int i=0; i<3;i++){
		System.out.println("David wrote this line.");
		System.out.println("Jamaal wrote this line.");
		System.out.println("Sam wrote this line.");
		System.out.println("Dan thinks Jamaal smells.");
		}*/
		
		Display.setDisplayMode(new DisplayMode(800,600));
		Display.create();
		
		while (!Display.isCloseRequested()) {
			
			// render OpenGL here
			
			Display.update();
			}
		
		
	}

}

