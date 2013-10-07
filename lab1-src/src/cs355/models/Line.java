package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Line extends Shape{
	
	private ArrayList<Point> points;
	private static final int HIT_BOX_SIZE = 3;
	
	public Line(Point start) {
		points = new ArrayList<Point>();
		points.add(start);
		points.add(start);
	}

	public void setEndPoint(Point end) {
		points.set(1, end);
		setCenter();
	}

	public void setStart(Point s){
		int index = points.indexOf(s);
		if(index == 0){
			Point temp = points.get(1);
			points.set(0, temp);
		}
		setEndPoint(s);
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	@Override
	public boolean contains(Point p) {
		Point p1 = points.get(0);
		Point p2 = points.get(1);
		double dist;
		if(p1.x == p2.x){
			dist = Math.abs(p.x - p1.x);
		}
		else{
			dist = Math.abs((p2.x - p1.x) * (p1.y - p.y) - (p1.x - p.x) * (p2.y - p1.y)) / 
				   Math.sqrt((Math.pow((p2.x - p1.x), 2) + (Math.pow((p2.y - p1.y), 2))));
		}
		return dist <= HIT_BOX_SIZE;
	}

	@Override
	public void setCenter() {
		int xTotal = points.get(0).x + points.get(1).x;
		int yTotal = points.get(0).y + points.get(1).y;
		center = new Point(xTotal/2, yTotal/2);
	}

	@Override
	public ArrayList<Point> getResizePoints() {
		return points;
	}
	
}
