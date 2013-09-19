package cs355.models;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

public class Triangle extends Shape{
	
	Polygon polygon;
	
	public Triangle(Point a) {
		polygon = new Polygon();
		polygon.addPoint(a.x,a.y);
	}

	public int getPointsSize() {
		return polygon.npoints;
	}

	public void addPoint(Point point) {
		if(getPointsSize() == 3){
			System.out.println("ERROR: Triangle can only has 3 points.");
			return;
		}
		polygon.addPoint(point.x,point.y);
	}

	public Polygon getPolygon() {
		return polygon;
	}

	@Override
	public boolean contains(Point p) {
		return polygon.contains(p);
	}

	@Override
	public Point getCenter() {
		Rectangle2D boundRec = polygon.getBounds2D();
		return new Point((int)boundRec.getCenterX(), (int)boundRec.getCenterY());
	}
	
}
