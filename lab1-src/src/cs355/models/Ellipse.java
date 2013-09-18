package cs355.models;

import java.awt.Point;

public class Ellipse extends Shape{

	private Point upperLeftCorner;
	private int height;
	private int width;
	
	public Ellipse(Point start) {
		upperLeftCorner = start;
		setEndPoint(start);
	}

	public void setEndPoint(Point end){
		Point start = upperLeftCorner;
		height = Math.abs(start.y-end.y);
		width = Math.abs(start.x-end.x);
		upperLeftCorner = new Point(Math.min(start.x,end.x),Math.min(start.y,end.y));
	}
	
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
