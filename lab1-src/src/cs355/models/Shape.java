package cs355.models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Shape {

	private Color color;
	protected Point center;
	private double rotation;
	
	public Shape(){}
	
	public Shape(Color color){
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public Point getCenter() {
		return center;
	}

	public abstract boolean contains(Point p);
	
	public abstract void setCenter();
	
	public abstract ArrayList<Point> getResizePoints();
	
}
