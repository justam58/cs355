package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Rectangle extends Shape{
	
	private Point upperLeftCorner;
	private int height;
	private int width;

	public Rectangle(Color color, Point ulc, int h, int w) {
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
