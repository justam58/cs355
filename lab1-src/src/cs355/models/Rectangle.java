package cs355.models;

import java.awt.Point;

public class Rectangle extends Shape{
	
	private Point upperLeftCorner;
	private Point start;
	private int height;
	private int width;
	
	public Rectangle(Point start) {
		upperLeftCorner = start;
		this.start = start;
		setEndPoint(start);
	}	

	public void setEndPoint(Point end){
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
