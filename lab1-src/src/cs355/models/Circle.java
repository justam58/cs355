package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Circle extends Shape {
	
	private Point center;
	private int radius;

	public Circle(Color color, Point c, int r) {
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
