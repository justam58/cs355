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
	private ShapeMode currentShapeMode;
	private Shape currentShape;
	private int currentIndex = 0;
    
	// all of the shapes that have been drawn
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	// getters and setters 
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void update(){
		shapes.set(currentIndex,currentShape);
	}
		
	public void add(){
		shapes.add(currentIndex,currentShape);
	}
	
	public void moveOn(){
		//System.out.println("done with one shape!");
		currentShape = null;
		currentIndex++;
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
	}

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
		if(currentShape != null){
			this.currentShape.setColor(currentColor);
		}
	}
	
}
