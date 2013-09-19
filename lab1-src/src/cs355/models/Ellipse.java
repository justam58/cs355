package cs355.models;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape{

	private Point upperLeftCorner;
	private Point start;
	private int height;
	private int width;
	
	public Ellipse(Point start) {
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
		Ellipse2D.Double ellispe = new Ellipse2D.Double(upperLeftCorner.x,upperLeftCorner.y,width,height);
		return ellispe.contains(p);	
	}

	@Override
	public Point getCenter() {
		return new Point(upperLeftCorner.x + width/2, upperLeftCorner.y + height/2);
	}

}
