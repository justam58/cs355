package cs355.models;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Line extends Shape{
	
	private ArrayList<Point2D> points;
	private static final int HIT_BOX_SIZE = 4;
	
	public Line(Point2D start) {
		points = new ArrayList<Point2D>();
		points.add(start);
		points.add(start);
	}

	public void setEndPoint(Point2D s) {
		points.set(1, s);
	}

	public void setStart(Point2D s){
		int index = indexOf(s);
		if(index == 0){
			Point2D temp = points.get(1);
			points.set(0, temp);
		}
		setEndPoint(s);
	}
	
	public ArrayList<Point2D> getPoints() {
		return points;
	}
	
	private int indexOf(Point2D p){
		int result = -1;
		for(int i = 0; i < points.size(); i++){
			if(Math.abs(points.get(i).getX() - p.getX()) <= HIT_BOX_SIZE &&
			   Math.abs(points.get(i).getY() - p.getY()) <= HIT_BOX_SIZE){
				result = i;
				break;
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<Point2D> getResizePoints() {
		return points;
	}

	@Override
	public Point getRotatePoint() {
		return null;
	}

	@Override
	public Point2D getCenter() {
		double xTotal = points.get(0).getX() + points.get(1).getX();
		double yTotal = points.get(0).getY() + points.get(1).getY();
		return new Point2D.Double(xTotal/2, yTotal/2);
	}

	@Override
	public boolean contains(Point2D p) {
		Point2D p1 = points.get(0);
		Point2D p2 = points.get(1);
		double dist;
		if(p1.getX() == p2.getX()){
			dist = Math.abs(p.getX() - p1.getX());
		}
		else{
			dist = Math.abs((p2.getX() - p1.getX()) * (p1.getY() - p.getY()) - (p1.getX() - p.getX()) * (p2.getY() - p1.getY())) / 
				   Math.sqrt((Math.pow((p2.getX() - p1.getX()), 2) + (Math.pow((p2.getY() - p1.getY()), 2))));
		}
		return dist <= HIT_BOX_SIZE;
	}

	@Override
	public void move(double d_x, double d_y) {
		for(int i = 0; i < points.size(); i++){
			points.set(i,new Point2D.Double(points.get(i).getX()+d_x,points.get(i).getY()+d_y));
		}
	}
	
}
