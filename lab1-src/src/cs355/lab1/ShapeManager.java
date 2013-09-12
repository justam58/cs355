package cs355.lab1;

import java.awt.Color;
import java.util.ArrayList;

import cs355.models.Shape;

public class ShapeManager {
	
	// singleton
    private static ShapeManager instance = new ShapeManager();

    public static ShapeManager getInstance() {
        return instance;
    }
    
	// different shapes in enum
	public enum ShapeMode { TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE }
	
	// current shape and color
	private Color currentColor;
	private ShapeMode currentShape;
    
	// all of the shapes that have been drawn
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	// getters and setters 
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void add(Shape newShape){
		shapes.add(newShape);
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public ShapeMode getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(ShapeMode currentShape) {
		this.currentShape = currentShape;
	}
	
}
