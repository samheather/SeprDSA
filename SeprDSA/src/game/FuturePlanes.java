package game;

import java.util.ArrayList;

public class FuturePlanes {

	public FuturePlanes() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<FuturePlane> futurePlanes = 
			new ArrayList<FuturePlane>();
	
	/**
	 * This method adds a FuturePlane to the list 
	 * @param p
	 */
	public static void add(FuturePlane p) {
		futurePlanes.add(p);
	}

	/** 
	 * This method removes a FuturePlane from the list
	 * @param p
	 */
	public static void remove(FuturePlane p) {
		futurePlanes.remove(p);
	}
	
	/** 
	 * This method returns the number of FuturePlanes in the list
	 * @return
	 */
	public static int size(){
		return futurePlanes.size();
	}

}
