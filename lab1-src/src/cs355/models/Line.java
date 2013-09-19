package cs355.models;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Line extends Shape{
	
	private ArrayList<Point> points;
	private static final int HIT_BOX_SIZE = 2;
	
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
	
	@Override
	public boolean contains(Point p) {
		Line2D.Double line = new Line2D.Double(points.get(0),points.get(1));
		int boxX = p.x - HIT_BOX_SIZE / 2;
		int boxY = p.y - HIT_BOX_SIZE / 2;
		return line.intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE);
	}

	@Override
	public Point getCenter() {
		Line2D.Double line = new Line2D.Double(points.get(0),points.get(1));
		Rectangle2D boundRec = line.getBounds2D();
		return new Point((int)boundRec.getCenterX(), (int)boundRec.getCenterY());
	}
	
}
