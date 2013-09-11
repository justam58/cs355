package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Square extends Shape {

	private Point upperLeftCorner;
	private double size;
	
	public Square(Color color, Point ulc, double s) {
		super(color);
		upperLeftCorner = ulc;
		size = s;
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public void setUpperLeftCorner(Point upperLeftCorner) {
		this.upperLeftCorner = upperLeftCorner;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
}
