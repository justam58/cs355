package cs355.lab1;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import cs355.ViewRefresher;
import cs355.models.*;

public class MyViewRefresher implements ViewRefresher{

	private ShapeManager shapeManager = ShapeManager.getInstance(); 
	
	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = shapeManager.getShapes();
		int selectedIndex = shapeManager.getSelectedIndex();

		for(int i = 0; i < shapes.size(); i++){
			g2d.setColor(shapes.get(i).getColor());
			boolean selected = (i == selectedIndex);
			switch(shapes.get(i).getClass().getName()){
				case "cs355.models.Line" :			drawLine(g2d,(Line) shapes.get(i),selected);
													break;
				case "cs355.models.Square" :		drawSquare(g2d,(Square) shapes.get(i),selected);
													break;
				case "cs355.models.Rectangle" :		drawRectangle(g2d,(Rectangle) shapes.get(i),selected);
													break;
				case "cs355.models.Circle" :		drawCircle(g2d,(Circle) shapes.get(i),selected);
													break;
				case "cs355.models.Ellipse" :		drawEllipse(g2d,(Ellipse) shapes.get(i),selected);
													break;
				case "cs355.models.Triangle" :		drawTrianagle(g2d,(Triangle) shapes.get(i),selected);
													break;		
				default:							System.out.printf("Invalid Shape : %s\n" ,shapes.get(i).getClass().getName(),selected);
                									break;
			}
		}
	}
	
	private void drawLine(Graphics2D g2d, Line line, boolean selected){
		ArrayList<Point> points = line.getPoints();
		Point a = points.get(0);
		Point b = points.get(1);
		g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}
	
	private void drawSquare(Graphics2D g2d, Square square, boolean selected){
		Point ulc = square.getUpperLeftCorner();
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), square.getSize(), square.getSize());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}
	
	private void drawRectangle(Graphics2D g2d, Rectangle rectangle, boolean selected){
		Point ulc = rectangle.getUpperLeftCorner();
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), rectangle.getWidth(), rectangle.getHeight());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}
	
	private void drawCircle(Graphics2D g2d, Circle circle, boolean selected){
		Point ulc = circle.getUpperLeftCorner();
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), circle.getRadius(), circle.getRadius());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}
	
	private void drawEllipse(Graphics2D g2d, Ellipse ellipse, boolean selected){
		Point ulc = ellipse.getUpperLeftCorner();
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), ellipse.getWidth(), ellipse.getHeight());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}
	
	private void drawTrianagle(Graphics2D g2d, Triangle triangle, boolean selected){
		if(triangle.getPointsSize() != 3){
			return;
		}
		Polygon polygon = triangle.getPolygon();
		g2d.fillPolygon(polygon);
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
	}

}
