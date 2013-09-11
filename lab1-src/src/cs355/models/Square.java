package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Square extends Shape {

	private Point upperLeftCorner;
	private int size;
	
	public Square(Color color, Point ulc, int s) {
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
