package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Rectangle extends Shape{
	
	private Point upperLeftCorner;
	private double height;
	private double width;

	public Rectangle(Color color, Point ulc, double h, double w) {
		super(color);
		upperLeftCorner = ulc;
		height = h;
		width = w;
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public void setUpperLeftCorner(Point upperLeftCorner) {
		this.upperLeftCorner = upperLeftCorner;
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
