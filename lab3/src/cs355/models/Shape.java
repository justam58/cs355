package cs355.models;

import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Shape {
	
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
	
}
