package cs355.models;

import java.awt.Point;

public class Circle extends Shape {
	
	private Point upperLeftCorner;
	private Point start;
	private int radius;

	public Circle(Point start) {
		upperLeftCorner = start;
		this.start = start;
	}

	public void setEndPoint(Point end){
		int delta_x = end.x - start.x;
		int delta_y = end.y - start.y;
		radius = Math.min(Math.abs(delta_x), Math.abs(delta_y));
		if(delta_x <= 0 && delta_y <= 0){
			upperLeftCorner = new Point(start.x - radius, start.y - radius); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			upperLeftCorner = new Point(start.x - radius, start.y); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			upperLeftCorner = new Point(start.x, start.y - radius); 
		}
		else{
			upperLeftCorner = new Point(start.x, start.y); 
		}
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public boolean contains(Point p) {
		return (Math.pow(p.x - getCenter().x, 2) + Math.pow(p.y - getCenter().y, 2) <= Math.pow(radius/2.0, 2));
	}

	@Override
	public Point getCenter() {
		return new Point(upperLeftCorner.x + radius/2.0, upperLeftCorner.y + radius/2.0);
	}

}
