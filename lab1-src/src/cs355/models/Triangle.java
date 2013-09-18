package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Triangle extends Shape{
	
	private ArrayList<Point> points;
	
	public Triangle(Point a) {
		points = new ArrayList<Point>();
		points.add(a);
	}

	public int getPointsSize() {
		return points.size();
	}

	public void addPoint(Point point) {
		if(getPointsSize() == 3){
			System.out.println("ERROR: Triangle can only has 3 points.");
			return;
		}
		points.add(point);
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
}
