package cs355.models;

import java.awt.Color;
import java.awt.Point;

public class Ellipse extends Shape{

	private Point center;
	private int height;
	private int width;
	
	public Ellipse(Color color, Point c, int h, int w) {
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
