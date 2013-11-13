package cs355.models;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Triangle extends Shape{
	
	ArrayList<Point2D> points;
	int selectedIndex;
	
	public Triangle(Point2D a) {
		points = new ArrayList<Point2D>();
		points.add(a);
	}

	public int getPointsSize() {
		return points.size();
	}
	
	public ArrayList<Point2D> getPoints(){
		return points;
	}

	public void addPoint(Point2D point) {
		points.add(point);
	}
	
	public void updateSelectedPoint(Point2D s){
		points.set(selectedIndex, s);
	}
	
	public void setSelectedPoint(Point2D s){
		selectedIndex = indexOf(s);
	}
	
	private int indexOf(Point2D p){
		int result = -1;
		for(int i = 0; i < points.size(); i++){
			if(Math.abs(points.get(i).getX() - p.getX()) <= 8 &&
			   Math.abs(points.get(i).getY() - p.getY()) <= 8){
				result = i;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean contains(java.awt.geom.Point2D p) {
		double x = p.getX();
		double y = p.getY();
		
		Point2D pp = new Point2D.Double(x,y);
		
		Point2D p1 = points.get(0);
		Point2D p2 = points.get(1);
		Point2D p3 = points.get(2);

		boolean b1, b2, b3;
		
		b1 = sign(pp, p1, p2) < 0.0f;
		b2 = sign(pp, p2, p3) < 0.0f;
		b3 = sign(pp, p3, p1) < 0.0f;
		
		return ((b1 == b2) && (b2 == b3));
	}
	
	private double sign(Point2D p1, Point2D p2, Point2D p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}
	
	@Override
	public ArrayList<Point2D> getResizePoints() {
		return points;
	}

	@Override
	public Point2D getRotatePoint() {
		ArrayList<Point2D> points = getResizePoints();
		Point2D a = points.get(0);
		Point2D b = points.get(1);
		Point2D result = new Point2D.Double((a.getX()+b.getX()-50)/2,(a.getY()+b.getY()-50)/2);
		if(this.contains(result)){
			result = new Point2D.Double((a.getX()+b.getX()+50)/2,(a.getY()+b.getY()+50)/2);
		}
		return result;
	}

	@Override
	public Point2D getCenter() {
		double xTotal = 0;
		double yTotal = 0;
		for(int i = 0; i < 3; i++){
			xTotal += points.get(i).getX();
			yTotal += points.get(i).getY();
		}
		return new java.awt.geom.Point2D.Double(xTotal/3, yTotal/3);
	}

	@Override
	public void move(double d_x, double d_y) {
		for(int i = 0; i < points.size(); i++){
			points.set(i,new Point2D.Double(points.get(i).getX()+d_x,points.get(i).getY()+d_y));
		}
	}
	
}
