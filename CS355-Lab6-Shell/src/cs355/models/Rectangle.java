package cs355.models;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Rectangle extends Shape{
	
	private double height;
	private double width;
	
	public Rectangle(Color color) {
		super(color);
	}
	
	public Rectangle(Point2D start) {
		super();
		origin = start;
		height = 0;
		width = 0;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public ArrayList<Point2D> getResizePoints() {
		ArrayList<Point2D> bPoints = new ArrayList<Point2D>();
		bPoints.add(new Point2D.Double(0,0)); // 0 upper left
		bPoints.add(new Point2D.Double(width,0)); // 1 upper right
		bPoints.add(new Point2D.Double(width,height)); // 2 lower right
		bPoints.add(new Point2D.Double(0,height)); // 3 lower left
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
		if(p.getY() < 0 ||
		   p.getX() < 0 ||
		   p.getY() > height ||
		   p.getX() > width){
				return false;
		}
		return true;
	}

	@Override
	public void move(double d_x, double d_y) {
		origin = new Point2D.Double(origin.getX()+d_x,origin.getY()+d_y);
	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double(width/2,height/2);
	}
}
