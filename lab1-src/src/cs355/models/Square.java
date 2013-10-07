package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

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
		setCenter();
	}
	
	public void setStart(Point s){
		if(s.x > upperLeftCorner.x && s.y > upperLeftCorner.y){
			start = upperLeftCorner;
		}
		else if(s.x > upperLeftCorner.x && s.y <= upperLeftCorner.y){
			start = new Point(s.x - size, s.y + size);
		}
		else if(s.x <= upperLeftCorner.x && s.y > upperLeftCorner.y){
			start = new Point(s.x + size, s.y - size);
		}
		else{
			start = new Point(s.x + size, s.y + size);
		}
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getSize() {
		return size;
	}

	@Override
	public boolean contains(Point p) {
		if(p.y < upperLeftCorner.y ||
		   p.x < upperLeftCorner.x ||
		   p.y > upperLeftCorner.y + size ||
		   p.x > upperLeftCorner.x + size ){
			return false;
		}
		return true;
	}

	@Override
	public void setCenter() {
		center = new Point(upperLeftCorner.x + size/2, upperLeftCorner.y + size/2);
	}
	
	@Override
	public ArrayList<Point> getResizePoints(){
		ArrayList<Point> bPoints = new ArrayList<Point>();
		bPoints.add(upperLeftCorner);
		bPoints.add(new Point(upperLeftCorner.x + size, upperLeftCorner.y));
		bPoints.add(new Point(upperLeftCorner.x + size, upperLeftCorner.y + size));
		bPoints.add(new Point(upperLeftCorner.x, upperLeftCorner.y + size));
		return bPoints;
	}
	
}
