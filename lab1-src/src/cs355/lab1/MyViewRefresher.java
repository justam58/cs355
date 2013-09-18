package cs355.lab1;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import cs355.ViewRefresher;
import cs355.models.*;

public class MyViewRefresher implements ViewRefresher{

	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = ShapeManager.getInstance().getShapes();
		
		for(int i = 0; i < shapes.size(); i++){
			g2d.setColor(shapes.get(i).getColor());
			switch(shapes.get(i).getClass().getName()){
				case "cs355.models.Line" :			drawLine(g2d,(Line) shapes.get(i));
													break;
				case "cs355.models.Square" :		drawSquare(g2d,(Square) shapes.get(i));
													break;
				case "cs355.models.Rectangle" :		drawRectangle(g2d,(Rectangle) shapes.get(i));
													break;
				case "cs355.models.Circle" :		drawCircle(g2d,(Circle) shapes.get(i));
													break;
				case "cs355.models.Ellipse" :		drawEllipse(g2d,(Ellipse) shapes.get(i));
													break;
				case "cs355.models.Triangle" :		drawTrianagle(g2d,(Triangle) shapes.get(i));
													break;		
				default:							System.out.printf("Invalid Shape : %s\n" ,shapes.get(i).getClass().getName());
                									break;
			}
		}
	}
	
	private void drawLine(Graphics2D g2d, Line line){
		//System.out.println("draw line!");
		ArrayList<Point> points = line.getPoints();
		Point a = points.get(0);
		Point b = points.get(1);
		g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
	}
	
	private void drawSquare(Graphics2D g2d, Square square){
		//System.out.println("draw square!");
		Point ulc = square.getUpperLeftCorner();
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), square.getSize(), square.getSize());
	}
	
	private void drawRectangle(Graphics2D g2d, Rectangle rectangle){
		//System.out.println("draw rectangle!");
		Point ulc = rectangle.getUpperLeftCorner();
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), rectangle.getWidth(), rectangle.getHeight());
	}
	
	private void drawCircle(Graphics2D g2d, Circle circle){
		//System.out.println("draw circle!");
		Point ulc = circle.getUpperLeftCorner();
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), circle.getRadius(), circle.getRadius());
	}
	
	private void drawEllipse(Graphics2D g2d, Ellipse ellipse){
		//System.out.println("draw ellipse!");
		Point ulc = ellipse.getUpperLeftCorner();
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), ellipse.getWidth(), ellipse.getHeight());
	}
	
	private void drawTrianagle(Graphics2D g2d, Triangle triangle){
		//System.out.println("draw triangle!");	
		ArrayList<Point> points = triangle.getPoints();		
		Polygon polygon = new Polygon();
		if(points.size() != 3){
			return;
		}
		for(int i = 0; i < 3; i++){
			polygon.addPoint((int)points.get(i).getX(),(int)points.get(i).getY());
		}
		g2d.fillPolygon(polygon);
	}

}
