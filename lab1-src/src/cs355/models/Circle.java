package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Circle extends Shape {
	
	private Point upperLeftCorner;
	private Point start;
	private int radius;

	public Circle(Point start) {
		upperLeftCorner = start;
		this.start = start;
		setEndPoint(start);
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

	public int getRadius() {
		return radius;
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
		return (Math.pow(p.x - getCenter().x, 2) + Math.pow(p.y - getCenter().y, 2) <= Math.pow(radius/2.0, 2));
	}

	@Override
	public void calculateCenter() {
		center = new Point(upperLeftCorner.x + radius/2, upperLeftCorner.y + radius/2);
	}
	
	@Override
	public ArrayList<Point> getResizePoints(){
		ArrayList<Point> bPoints = new ArrayList<Point>();
		bPoints.add(upperLeftCorner);
		bPoints.add(new Point(upperLeftCorner.x + radius, upperLeftCorner.y));
		bPoints.add(new Point(upperLeftCorner.x + radius, upperLeftCorner.y + radius));
		bPoints.add(new Point(upperLeftCorner.x, upperLeftCorner.y + radius));
		return bPoints;
	}

	@Override
	public void move(int d_x, int d_y) {
		// TODO Auto-generated method stub
		
	}

}
