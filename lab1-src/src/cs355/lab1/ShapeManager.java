package cs355.lab1;

import java.awt.Color;
import java.util.ArrayList;

import cs355.GUIFunctions;
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
	private ShapeMode currentShapeMode = ShapeMode.TRIANGLE;
	private int currentIndex = 0;
	
	// start drawing triangle or not
	private boolean triangleStarted = false;
    
	// all of the shapes that have been drawn
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	// getters and setters 
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void update(Shape shape){
		shapes.set(currentIndex,shape);
		if(currentShapeMode != ShapeMode.TRIANGLE){
			GUIFunctions.refresh();
		}
	}
		
	public void add(Shape shape){
		shape.setColor(currentColor);
		shapes.add(currentIndex,shape);
		if(currentShapeMode == ShapeMode.TRIANGLE){
			setTriangleStarted(true);
		}
	}
	
	public void moveOn(){
		//System.out.println("done with one shape!");
		currentIndex++;
		setTriangleStarted(false);
		GUIFunctions.refresh();
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public ShapeMode getCurrentShapeMode() {
		return currentShapeMode;
	}

	public void setCurrentShapeMode(ShapeMode currentShapeMode) {
		this.currentShapeMode = currentShapeMode;
		setTriangleStarted(false);
	}

	public Shape getCurrentShape() {
		return shapes.get(currentIndex);
	}

	public boolean isTriangleStarted() {
		return triangleStarted;
	}

	public void setTriangleStarted(boolean triangleStarted) {
		this.triangleStarted = triangleStarted;
	}
	
}
