package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Triangle extends Shape{
	
	ArrayList<Point> points;
	int selectedIndex;
	
	public Triangle(Point a) {
		points = new ArrayList<Point>();
		points.add(a);
	}

	public int getPointsSize() {
		return points.size();
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}

	public void addPoint(Point point) {
		points.add(point);
		if(getPointsSize() == 3){
			calculateCenter();
		}
	}
	
	public void updateSelectedPoint(Point s){
		points.set(selectedIndex, s);
	}
	
	public void setSelectedPoint(Point s){
		selectedIndex = indexOf(s);
	}
	
	private int indexOf(Point p){
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
		Point p1 = points.get(0);
		Point p2 = points.get(1);
		Point p3 = points.get(2);

		boolean b1, b2, b3;
		
		b1 = sign(p, p1, p2) < 0.0f;
		b2 = sign(p, p2, p3) < 0.0f;
		b3 = sign(p, p3, p1) < 0.0f;
		
		return ((b1 == b2) && (b2 == b3));
	}

	@Override
	public void calculateCenter() {
		int xTotal = 0;
		int yTotal = 0;
		for(int i = 0; i < 3; i++){
			xTotal += points.get(i).x;
			yTotal += points.get(i).y;
		}
		center = new Point(xTotal/3, yTotal/3);
	}
	
	private float sign(Point p1, Point p2, Point p3) {
		return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}
	
	@Override
	public ArrayList<Point> getResizePoints() {
		return points;
	}

	@Override
	public void move(int d_x, int d_y) {
		// TODO Auto-generated method stub
		
	}
	
}
