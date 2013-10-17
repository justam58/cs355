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
	public enum Mode { TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE, SELECT }
	
	// current shape and color
	private Color currentColor = Color.WHITE;
	private Mode currentMode = Mode.TRIANGLE;
	private int totalIndex = 0;
	private int selectedIndex = -1;
	
	// start drawing triangle or not
		private boolean triangleStarted = false;
	    
	// all of the shapes that have been drawn
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public void updateDrawingShape(Shape shape){
		shapes.set(totalIndex,shape);
		GUIFunctions.refresh();
	}
	
	public void updateSelectedShape(Shape shape){
		shapes.set(selectedIndex,shape);
		GUIFunctions.refresh();
	}
		
	public void add(Shape shape){
		shape.setColor(currentColor);
		shapes.add(totalIndex,shape);
		if(currentMode == Mode.TRIANGLE){
			setTriangleStarted(true);
		}
	}
	
	public void moveOn(){
		System.out.println("move on");
		totalIndex++;
		setTriangleStarted(false);
		GUIFunctions.refresh();
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
		Shape shape;
		if(selectedIndex != -1){
			shape = getSelectedShape();
			shape.setColor(currentColor);
			updateSelectedShape(shape);
			GUIFunctions.refresh();
		}
		try{
			shape = getCurrentShape();
		}
		catch(IndexOutOfBoundsException e){
			return;
		}
		shape.setColor(currentColor);
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(Mode currentMode) {
		this.currentMode = currentMode;
		setTriangleStarted(false);
		if(currentMode != Mode.SELECT){
			setSelectedIndex(-1);
		}
	}

	public Shape getCurrentShape() {
		return shapes.get(totalIndex);
	}

	public boolean isTriangleStarted() {
		return triangleStarted;
	}

	public void setTriangleStarted(boolean triangleStarted) {
		this.triangleStarted = triangleStarted;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
		GUIFunctions.refresh();
	}
	
	public Shape getSelectedShape(){
		return shapes.get(selectedIndex);
	}
	
}
