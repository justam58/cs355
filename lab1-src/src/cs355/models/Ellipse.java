package cs355.models;

import java.awt.Point;
import java.awt.geom.AffineTransform;
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
//		try{
//			AffineTransform at = new AffineTransform();
//			at.rotate(-rotation, center.x, center.y);
//			at.translate(end.x, end.y);
//			end.x = (int) at.getTranslateX();
//			end.y = (int) at.getTranslateY();
//		}
//		catch(NullPointerException e){}
		height = Math.abs(start.y-end.y);
		width = Math.abs(start.x-end.x);
		upperLeftCorner = new Point(Math.min(start.x,end.x),Math.min(start.y,end.y));
		calculateCenter();
	}
	
	public void setStart(Point s){
		int index = indexOf(s);
		System.out.printf("index is %d\n",index);
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
//		AffineTransform at = new AffineTransform();
//		at.translate(start.x, start.y);
//		at.rotate(-rotation, center.x, center.y);
//		start.x = (int) at.getTranslateX();
//		start.y = (int) at.getTranslateY();
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
//		AffineTransform at = new AffineTransform();
//		at.rotate(-rotation, center.x, center.y);
//		at.translate(p.x, p.y);
//		p.x = (int) at.getTranslateX();
//		p.y = (int) at.getTranslateY();
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
		AffineTransform at = new AffineTransform();
		at.rotate(-rotation, center.x, center.y);
		at.translate(p.x, p.y);
		double x = at.getTranslateX();
		double y = at.getTranslateY();
		return (Math.pow((x-center.x)/(width/2.0), 2) + Math.pow((y-center.y)/(height/2.0), 2) <= 1);
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

	@Override
	public Point getRotatePoint() {
		ArrayList<Point> points = getResizePoints();
		Point a = points.get(0);
		Point b = points.get(1);
		return new Point((a.x+b.x)/2,(a.y+b.y-30)/2);
	}

}
