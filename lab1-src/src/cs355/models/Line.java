package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Line extends Shape{
	
	private ArrayList<Point> points;
	
	public Line(Point start) {
		points = new ArrayList<Point>();
		points.add(start);
		points.add(start);
	}

	public void setEndPoint(Point end) {
		points.add(1, end);;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}
	
}
