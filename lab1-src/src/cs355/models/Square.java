package cs355.models;

import java.awt.Point;

public class Square extends Shape {

	private Point upperLeftCorner;
	private int size;
	
	public Square(Point start) {
		upperLeftCorner = start;
		setEndPoint(start);
	}
	
	public void setEndPoint(Point end){
		Point start = upperLeftCorner;
		size = Math.min(Math.abs(start.x-end.x), Math.abs(start.y-end.y));
		upperLeftCorner = new Point(Math.min(start.x,end.x),Math.min(start.y,end.y));
	}
	
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getSize() {
		return size;
	}
	
}
