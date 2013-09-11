package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Circle extends Shape {
	
	private Point center;
	private double radius;

	public Circle(Color color, Point c, double r) {
		super(color);
		center = c;
		radius = r;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
