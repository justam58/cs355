package cs355.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Shape {
	
	private Color color;
	protected Point2D origin;
	private double rotation;
	
	public Shape() {
		this.rotation = 0;
	}
	
	public Shape(Color color) {
		this.color = color;
		this.origin = new Point2D.Double(0,0);
		this.rotation = 0;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Point2D getOrigin() {
		return origin;
	}
	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public abstract ArrayList<Point2D> getResizePoints();
	
	public abstract Point2D getCenter();
	
	public abstract Point2D getRotatePoint();
	
	public abstract boolean contains(Point2D p);
	
	public abstract void move(double d_x, double d_y);
	
}
