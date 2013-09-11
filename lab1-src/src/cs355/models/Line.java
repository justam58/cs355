package cs355.models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Line extends Shape{
	
	private ArrayList<Point> points;

	public Line(Color color, Point a, Point b) {
		super(color);
		points = new ArrayList<Point>();
		points.add(a);
		points.add(b);
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}