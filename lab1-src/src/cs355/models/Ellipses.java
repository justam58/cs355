package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Ellipses extends Shape{

	private Point center;
	private double height;
	private double width;
	
	public Ellipses(Color color, Point c, double h, double w) {
		super(color);
		center = c;
		height = h;
		width = w;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

}
