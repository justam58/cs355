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
		calculateCenter();
	}
	
	public void setStart(Point s){
		int index = indexOf(s);
		if(index == 0){
			start = getResizePoints().get(2);
		}
		else if(index == 1){
			start = getResizePoints().get(3);
		}
		else if(index == 2){
			start = getResizePoints().get(0);
		}
		else{
			start = getResizePoints().get(1);
		}
	}

	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getSize() {
		return size;
	}
	
	private int indexOf(Point p){
		ArrayList<Point> points = getResizePoints();
		int result = -1;
		for(int i = 0; i < points.size(); i++){
			if(Math.abs(points.get(i).x - p.x) <= 2 &&
			   Math.abs(points.get(i).y - p.y) <= 2){
				result = i;
				break;
			}
		}
		return result;
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
	public void calculateCenter() {
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

	@Override
	public void move(int d_x, int d_y) {
		upperLeftCorner.x += d_x;
		upperLeftCorner.y += d_y;
		calculateCenter();
	}

	@Override
	public Point getRotatePoint() {
		ArrayList<Point> points = getResizePoints();
		Point a = points.get(0);
		Point b = points.get(1);
		return new Point((a.x+b.x)/2,(a.y+b.y-30)/2);
	}
	
}
