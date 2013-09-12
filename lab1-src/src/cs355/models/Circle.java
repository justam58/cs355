package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Circle extends Shape {
	
	private Point upperLeftCorner;
	private int radius;
	
	public Circle() {}

	public Circle(Color color, Point ulc, int r) {
		super(color);
		upperLeftCorner = ulc;
		radius = r;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public void setUpperLeftCorner(Point upperLeftCorner) {
		this.upperLeftCorner = upperLeftCorner;
	}

}
