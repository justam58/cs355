package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

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

	@Override
	public boolean contains(Point p) {
		if(p.y < upperLeftCorner.y ||
		   p.x < upperLeftCorner.x ||
		   p.y > upperLeftCorner.y + height ||
		   p.x > upperLeftCorner.x + width ){
				return false;
		}
		return true;
	}

	@Override
	public Point getCenter() {
		return new Point(upperLeftCorner.x + width/2, upperLeftCorner.y + height/2);
	}
	
	@Override
	public ArrayList<Point> getResizePoints(){
		ArrayList<Point> bPoints = new ArrayList<Point>();
		bPoints.add(upperLeftCorner);
		bPoints.add(new Point(upperLeftCorner.x + width, upperLeftCorner.y));
		bPoints.add(new Point(upperLeftCorner.x + width, upperLeftCorner.y + height));
		bPoints.add(new Point(upperLeftCorner.x, upperLeftCorner.y + height));
		return bPoints;
	}
}
