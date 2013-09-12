package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Ellipse extends Shape{

	private Point upperLeftCorner;
	private int height;
	private int width;
	
	public Ellipse(Point upperLeftCorner) {
		this.upperLeftCorner = upperLeftCorner;
		height = 0;
		width = 0;
	}
	
	public Ellipse(Color color, Point c, int h, int w) {
		super(color);
		upperLeftCorner = c;
		height = h;
		width = w;
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

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public void setUpperLeftCorner(Point upperLeftCorner) {
		this.upperLeftCorner = upperLeftCorner;
	}

}
