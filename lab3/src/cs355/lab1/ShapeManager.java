package cs355.lab1;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import cs355.models.Shape;
import cs355.models.Square;

public class ShapeManager {
	
	// singleton
    private static ShapeManager instance = new ShapeManager();

    public static ShapeManager getInstance() {
        return instance;
    }
    
	// different shapes in enum
	public enum Mode { TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE, SELECT }
	
	private Color currentColor;
	private Mode currentMode;
	private ArrayList<Shape> shapes;

	public ShapeManager() {
		shapes = new ArrayList<Shape>();
		currentColor = Color.WHITE;
		
		Square test = new Square(currentColor);
		test.setSize(50);
		test.setCenter(new Point2D.Double(100,200));
		shapes.add(test);
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(Mode currentMode) {
		this.currentMode = currentMode;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
}
