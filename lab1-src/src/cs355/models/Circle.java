package cs355.models;

import java.awt.Point;

public class Circle extends Shape {
	
	private Point upperLeftCorner;
	private int radius;

	public Circle(Point start) {
		upperLeftCorner = start;
		setEndPoint(start);
	}

	public void setEndPoint(Point end){
		Point start = upperLeftCorner;
		radius = Math.min(Math.abs(start.x-end.x), Math.abs(start.y-end.y));
		upperLeftCorner = new Point(Math.min(start.x,end.x),Math.min(start.y,end.y));
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getRadius() {
		return radius;
	}

}
