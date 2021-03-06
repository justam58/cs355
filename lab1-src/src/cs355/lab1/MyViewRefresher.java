package cs355.lab1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
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
			switch(shapes.get(i).getClass().getName()){
				case "cs355.models.Line" :			drawLine(g2d,(Line) shapes.get(i),false);
													break;
				case "cs355.models.Square" :		drawSquare(g2d,(Square) shapes.get(i),false);
													break;
				case "cs355.models.Rectangle" :		drawRectangle(g2d,(Rectangle) shapes.get(i),false);
													break;
				case "cs355.models.Circle" :		drawCircle(g2d,(Circle) shapes.get(i),false);
													break;
				case "cs355.models.Ellipse" :		drawEllipse(g2d,(Ellipse) shapes.get(i),false);
													break;
				case "cs355.models.Triangle" :		drawTrianagle(g2d,(Triangle) shapes.get(i),false);
													break;		
				default:							System.out.printf("Invalid Shape : %s\n" ,shapes.get(i).getClass().getName());
                									break;
			}
		}
		if(selectedIndex != -1){
			Shape shape = shapes.get(selectedIndex);
			switch(shape.getClass().getName()){
				case "cs355.models.Line" :			drawLine(g2d,(Line) shape,true);
													break;
				case "cs355.models.Square" :		drawSquare(g2d,(Square) shape,true);
													break;
				case "cs355.models.Rectangle" :		drawRectangle(g2d,(Rectangle) shape,true);
													break;
				case "cs355.models.Circle" :		drawCircle(g2d,(Circle) shape,true);
													break;
				case "cs355.models.Ellipse" :		drawEllipse(g2d,(Ellipse) shape,true);
													break;
				case "cs355.models.Triangle" :		drawTrianagle(g2d,(Triangle) shape,true);
													break;		
				default:							System.out.printf("Invalid Shape : %s\n" ,shape.getClass().getName());
		        									break;
			}
		}
	}
	
	private void drawLine(Graphics2D g2d, Line line, boolean selected){
		if(selected){
			drawResizePoints(g2d,line.getResizePoints());
			return;
		}
		ArrayList<Point> points = line.getPoints();
		Point a = points.get(0);
		Point b = points.get(1);
		g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());

//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)line.getCenter().x, (int)line.getCenter().y, 2, 2);
	}
	
	private void drawSquare(Graphics2D g2d, Square square, boolean selected){
		Point ulc = square.getUpperLeftCorner();
		if(selected){
			g2d.rotate(square.getRotation(),square.getCenter().x,square.getCenter().y);
			drawResizePoints(g2d,square.getResizePoints());
			g2d.fillRect((int)square.getRotatePoint().x-2, (int)square.getRotatePoint().y-2, 4, 4);
			g2d.setColor(Color.GREEN);
			g2d.drawRect((int)ulc.getX(), (int)ulc.getY(), square.getSize(), square.getSize());
			g2d.rotate(-square.getRotation(),square.getCenter().x,square.getCenter().y);
			return;
		}
		g2d.rotate(square.getRotation(),square.getCenter().x,square.getCenter().y);
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), square.getSize(), square.getSize());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)square.getCenter().x, (int)square.getCenter().y, 2, 2);
		g2d.rotate(-square.getRotation(),square.getCenter().x,square.getCenter().y);
	}
	
	private void drawRectangle(Graphics2D g2d, Rectangle rectangle, boolean selected){
		Point ulc = rectangle.getUpperLeftCorner();
		if(selected){
			g2d.rotate(rectangle.getRotation(),rectangle.getCenter().x,rectangle.getCenter().y);
			drawResizePoints(g2d,rectangle.getResizePoints());
			g2d.fillRect((int)rectangle.getRotatePoint().x-2, (int)rectangle.getRotatePoint().y-2, 4, 4);
			g2d.setColor(Color.GREEN);
			g2d.drawRect((int)ulc.getX(), (int)ulc.getY(), rectangle.getWidth(), rectangle.getHeight());
			g2d.rotate(-rectangle.getRotation(),rectangle.getCenter().x,rectangle.getCenter().y);
			return;
		}
		
		g2d.rotate(rectangle.getRotation(),rectangle.getCenter().x,rectangle.getCenter().y);
		g2d.fillRect((int)ulc.getX(), (int)ulc.getY(), rectangle.getWidth(), rectangle.getHeight());
		

		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)rectangle.getCenter().x, (int)rectangle.getCenter().y, 2, 2);
		g2d.rotate(-rectangle.getRotation(),rectangle.getCenter().x,rectangle.getCenter().y);
	}
	
	private void drawCircle(Graphics2D g2d, Circle circle, boolean selected){
		Point ulc = circle.getUpperLeftCorner();
		if(selected){
			drawResizePoints(g2d,circle.getResizePoints());
			g2d.setColor(Color.GREEN);
			g2d.drawOval((int)ulc.getX(), (int)ulc.getY(), circle.getRadius(), circle.getRadius());
			return;
		}
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), circle.getRadius(), circle.getRadius());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)circle.getCenter().x, (int)circle.getCenter().y, 2, 2);
	}
	
	private void drawEllipse(Graphics2D g2d, Ellipse ellipse, boolean selected){
		Point ulc = ellipse.getUpperLeftCorner();
		if(selected){
			g2d.rotate(ellipse.getRotation(),ellipse.getCenter().x,ellipse.getCenter().y);
			drawResizePoints(g2d,ellipse.getResizePoints());
			g2d.fillRect((int)ellipse.getRotatePoint().x-2, (int)ellipse.getRotatePoint().y-2, 4, 4);
			g2d.setColor(Color.GREEN);
			g2d.drawOval((int)ulc.getX(), (int)ulc.getY(), ellipse.getWidth(), ellipse.getHeight());
			g2d.rotate(-ellipse.getRotation(),ellipse.getCenter().x,ellipse.getCenter().y);
			return;
		}
		g2d.rotate(ellipse.getRotation(),ellipse.getCenter().x,ellipse.getCenter().y);
		g2d.fillOval((int)ulc.getX(), (int)ulc.getY(), ellipse.getWidth(), ellipse.getHeight());
		
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)ellipse.getCenter().x, (int)ellipse.getCenter().y, 2, 2);
		g2d.rotate(-ellipse.getRotation(),ellipse.getCenter().x,ellipse.getCenter().y);
	}
	
	private void drawTrianagle(Graphics2D g2d, Triangle triangle, boolean selected){
		if(triangle.getPointsSize() != 3){
			return;
		}
		
		int[] xs = new int[3];
		int[] ys = new int[3];
		ArrayList<Point> points = triangle.getPoints();
		for(int i = 0; i < 3; i++){
			xs[i] = points.get(i).x;
			ys[i] = points.get(i).y;
		}

		if(selected){
			g2d.rotate(triangle.getRotation(),triangle.getCenter().x,triangle.getCenter().y);
			drawResizePoints(g2d,triangle.getResizePoints());
			g2d.fillRect((int)triangle.getRotatePoint().x-2, (int)triangle.getRotatePoint().y-2, 4, 4);
			g2d.setColor(Color.GREEN);
			g2d.drawPolygon(xs,ys,3);
			g2d.rotate(-triangle.getRotation(),triangle.getCenter().x,triangle.getCenter().y);
			return;
		}

		g2d.rotate(triangle.getRotation(),triangle.getCenter().x,triangle.getCenter().y);
		g2d.fillPolygon(xs,ys,3);
			
//		draw center
//		g2d.setColor(Color.BLACK);
//		g2d.drawRect((int)triangle.getCenter().x, (int)triangle.getCenter().y, 2, 2);
		g2d.rotate(-triangle.getRotation(),triangle.getCenter().x,triangle.getCenter().y);
	}
	
	private void drawResizePoints(Graphics2D g2d, ArrayList<Point> points){
		g2d.setColor(Color.orange);
		for(int i = 0; i < points.size(); i++){
			g2d.fillRect((int)points.get(i).x-2, (int)points.get(i).y-2, 4, 4);
		}
	}

}
