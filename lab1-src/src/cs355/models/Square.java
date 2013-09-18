package cs355.models;

import java.awt.Point;

public class Square extends Shape {

	private Point upperLeftCorner;
	private Point start;
	private int size;
	
	public Square(Point start) {
		upperLeftCorner = start;
		this.start = start;
		setEndPoint(start);
	}
	
	public void setEndPoint(Point end){
		int delta_x = end.x - start.x;
		int delta_y = end.y - start.y;
		size = Math.min(Math.abs(delta_x), Math.abs(delta_y));
		if(delta_x <= 0 && delta_y <= 0){
			upperLeftCorner = new Point(start.x - size, start.y - size); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			upperLeftCorner = new Point(start.x - size, start.y); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			upperLeftCorner = new Point(start.x, start.y - size); 
		}
		else{
			upperLeftCorner = new Point(start.x, start.y); 
		}
	}
	
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getSize() {
		return size;
	}
	
}
