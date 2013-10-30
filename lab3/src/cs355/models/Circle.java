package cs355.models;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Circle extends Shape {
	
	private double size;
	
	public Circle(Color color) {
		super(color);
	}
	
	public Circle(Point2D start) {
		super();
		origin = start;
		size = 0;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	@Override
	public ArrayList<Point2D> getResizePoints() {
		ArrayList<Point2D> bPoints = new ArrayList<Point2D>();
		bPoints.add(new Point2D.Double(0,0)); // 0 upper left
		bPoints.add(new Point2D.Double(size,0)); // 1 upper right
		bPoints.add(new Point2D.Double(size,size)); // 2 lower right
		bPoints.add(new Point2D.Double(0,size)); // 3 lower left
		return bPoints;
	}

	@Override
	public Point2D getRotatePoint() {
		ArrayList<Point2D> points = getResizePoints();
		Point2D a = points.get(0);
		Point2D b = points.get(1);
		return new Point2D.Double((a.getX()+b.getX())/2,(a.getY()+b.getY()-30)/2);
	}

	@Override
	public boolean contains(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		return (Math.pow(x - (size/2), 2) + Math.pow(y - (size/2), 2) <= Math.pow(size/2.0, 2));
	}

	@Override
	public void move(double d_x, double d_y) {
		origin = new Point2D.Double(origin.getX()+d_x,origin.getY()+d_y);
	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double(size/2,size/2);
	}
}
