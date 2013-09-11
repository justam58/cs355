package cs355.lab1;

import java.util.ArrayList;

import cs355.models.Shape;

public class ShapeManager {
	
    private static ShapeManager instance = new ShapeManager();

    public static ShapeManager getInstance() {
        return instance;
    }
    
	public ArrayList<Shape> shapes;

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void add(Shape newShape){
		shapes.add(newShape);
	}

}
