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
		test.setSize(200);
		test.setCenter(new Point2D.Double(256,256));
		test.setRotation(Math.PI/4);
		Square test1 = new Square(currentColor);
		test1.setSize(50);
		test1.setCenter(new Point2D.Double(0,0));
		Square test2 = new Square(currentColor);
		test2.setSize(48);
		test2.setCenter(new Point2D.Double(2000,2000));
		shapes.add(test);
		shapes.add(test2);
		shapes.add(test1);
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
