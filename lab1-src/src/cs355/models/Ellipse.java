package cs355.models;

import java.awt.Point;
import java.util.ArrayList;

public class Ellipse extends Shape{

	private Point upperLeftCorner;
	private Point start;
	private int height;
	private int width;
	
	public Ellipse(Point start) {
		upperLeftCorner = start;
		this.start = start;
		setEndPoint(start);
	}

	public void setEndPoint(Point end){
		height = Math.abs(start.y-end.y);
		width = Math.abs(start.x-end.x);
		upperLeftCorner = new Point(Math.min(start.x,end.x),Math.min(start.y,end.y));
		calculateCenter();
	}
	
	public void setStart(Point s){
		int index = indexOf(s);
		if(index == 0){
			start = getResizePoints().get(2);
		}
		else if(index == 1){
			start = getResizePoints().get(3);
		}
		else if(index == 2){
			start = getResizePoints().get(0);
		}
		else{
			start = getResizePoints().get(1);
		}
	}
	
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	private int indexOf(Point p){
		ArrayList<Point> points = getResizePoints();
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
		return (Math.pow((p.x - getCenter().x)/(width/2.0), 2) + Math.pow((p.y - getCenter().y)/(height/2.0), 2) <= 1);
	}

	@Override
	public void calculateCenter() {
		center = new Point(upperLeftCorner.x + width/2, upperLeftCorner.y + height/2);
	}
	
	@Override
	public ArrayList<Point> getResizePoints(){
		ArrayList<Point> bPoints = new ArrayList<Point>();
		bPoints.add(upperLeftCorner);
		bPoints.add(new Point(upperLeftCorner.x + width, upperLeftCorner.y));
		bPoints.add(new Point(upperLeftCorner.x + width, upperLeftCorner.y + height));
		bPoints.add(new Point(upperLeftCorner.x, upperLeftCorner.y + height));
		return bPoints;
	}

	@Override
	public void move(int d_x, int d_y) {
		upperLeftCorner.x += d_x;
		upperLeftCorner.y += d_y;
		calculateCenter();
	}

}
