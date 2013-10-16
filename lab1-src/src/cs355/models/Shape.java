package cs355.models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Shape {

	private Color color;
	protected Point center;
	protected double rotation = 0;
	
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
	
	public abstract void move(int d_x, int d_y);
	
	public abstract boolean contains(Point p);
	
	public abstract void calculateCenter();
	
	public abstract ArrayList<Point> getResizePoints();
	
	public abstract Point getRotatePoint();
	
}
