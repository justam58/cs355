package cs355.models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Triangle extends Shape{
	
	private ArrayList<Point> points;
	
	public Triangle(Color color, Point a, Point b, Point c) {
		super(color);
		points = new ArrayList<Point>();
		points.add(a);
		points.add(b);
		points.add(c);
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}