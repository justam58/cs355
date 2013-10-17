package cs355.models;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import cs355.lab1.ShapeManager;
import cs355.lab1.Transformation;

public abstract class Shape {
	
	private Transformation tfm = Transformation.getInstance(); 
	
	private Color color;
	private Point2D center;
	private double rotation;
	
	public Shape(Color color) {
		this.color = color;
		this.center = new Point2D.Double(0,0);
		this.rotation = 0;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Point2D getCenter() {
		return center;
	}
	public void setCenter(Point2D center) {
		this.center = center;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public abstract ArrayList<Point> getResizePoints();
	
	public abstract Point getRotatePoint();
	
	public abstract boolean contains(Point p);
	
	public abstract void move(int d_x, int d_y);
	
}
