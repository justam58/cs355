package cs355.lab1;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import cs355.lab1.ShapeManager.Mode;
import cs355.models.*;

public class MyMouseListener implements MouseListener, MouseMotionListener{
	
	private ShapeManager shapeManager = ShapeManager.getInstance(); 
	private boolean dragging;
	
	private void initDrag() {
		dragging = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.printf("click: %d %d\n", e.getX(),e.getY());
		
		Point p = new Point(e.getX(),e.getY());
		
		if (shapeManager.getCurrentMode() == Mode.TRIANGLE){
			
			if(!shapeManager.isTriangleStarted()){
				Triangle triangle = new Triangle(p);
				shapeManager.add(triangle);
				//System.out.println("start triangle");
			}
			else{
				Triangle triangle = (Triangle) shapeManager.getCurrentShape();
				if(triangle.getPointsSize() == 1){
					triangle.addPoint(p);
					shapeManager.update(triangle);
					//System.out.println("continue triangle");
				}
				else if(triangle.getPointsSize() == 2){
					triangle.addPoint(p);
					shapeManager.moveOn();
					//System.out.println("done triangle");
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// no need
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// no need
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		System.out.printf("press: %d %d\n", e.getX(),e.getY());
		
		Point p = new Point(e.getX(),e.getY());
		
		if(shapeManager.getCurrentMode() == Mode.SELECT){
			if(shapeManager.getSelectedIndex() != -1){
				Shape shape = shapeManager.getSelectedShape();
				ArrayList<Point> resizePoints = shape.getResizePoints();
				for(int i = 0; i < resizePoints.size(); i++){
					if(containsPoint(p,resizePoints.get(i))){
//						System.out.println("got resize points!");
						String shapeType = shape.getClass().getName();
						switch(shapeType){
							case "cs355.models.Line" :			Line line = (Line) shape;
																line.setStart(p);
																break;
							case "cs355.models.Square" :		Square square = (Square) shape;
																square.setStart(p);
																break;
							case "cs355.models.Rectangle" :		Rectangle rectangle = (Rectangle) shape;
																rectangle.setStart(p);
																break;
							case "cs355.models.Circle" :		Circle circle = (Circle) shape;
																circle.setStart(p);
																break;
							case "cs355.models.Ellipse" :		Ellipse ellipse = (Ellipse) shape;
																ellipse.setStart(p);
																break;
							case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
																triangle.setSelectedPoint(p);
																break;		
							default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
			                									break;
						}
						shapeManager.updateResize(shape);
						dragging = true;
					}
				}
			}
			if(!dragging){
				ArrayList<Shape> shapes = shapeManager.getShapes();
				for(int i = 0; i < shapes.size(); i++){
					boolean contains = shapes.get(i).contains(p);
					if(contains){
						shapeManager.setSelectedIndex(i);
						break;
					}
					else{
						shapeManager.setSelectedIndex(-1);
					}
				}		
			}
		}
		else if(shapeManager.getCurrentMode() != Mode.TRIANGLE){
			dragging = true;
			
			Point start = new Point(e.getX(),e.getY());
			
			if (shapeManager.getCurrentMode() == Mode.LINE){
				Line shape = new Line(start);
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.RECTANGLE){
				Rectangle shape = new Rectangle(start);
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.SQUARE){
				Square shape = new Square(start);
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.ELLIPSE){
				Ellipse shape = new Ellipse(start);
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.CIRCLE){
				Circle shape = new Circle(start);
				shapeManager.add(shape);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

		if (dragging && (shapeManager.getCurrentMode() != Mode.TRIANGLE)){	
			shapeManager.moveOn();
			initDrag();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (dragging) {	
			//System.out.printf("dragging: %d %d\n", e.getX(),e.getY());

			Point end = new Point(e.getX(),e.getY());
			
			if(shapeManager.getCurrentMode() == Mode.SELECT){
				Shape shape = shapeManager.getSelectedShape();
				String shapeType = shape.getClass().getName();
				switch(shapeType){
					case "cs355.models.Line" :			Line line = (Line) shape;
														line.setEndPoint(end);
														break;
					case "cs355.models.Square" :		Square square = (Square) shape;
														square.setEndPoint(end);
														break;
					case "cs355.models.Rectangle" :		Rectangle rectangle = (Rectangle) shape;
														rectangle.setEndPoint(end);
														break;
					case "cs355.models.Circle" :		Circle circle = (Circle) shape;
														circle.setEndPoint(end);
														break;
					case "cs355.models.Ellipse" :		Ellipse ellipse = (Ellipse) shape;
														ellipse.setEndPoint(end);
														break;
					case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
														triangle.updateSelectedPoint(end);
														break;		
					default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
	                									break;
				}
				shapeManager.updateResize(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.LINE){
				Line shape = (Line) shapeManager.getCurrentShape();
				shape.setEndPoint(end);
				shapeManager.update(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.RECTANGLE){
				Rectangle shape = (Rectangle) shapeManager.getCurrentShape();
				shape.setEndPoint(end);
				shapeManager.update(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.SQUARE){
				Square shape = (Square) shapeManager.getCurrentShape();
				shape.setEndPoint(end);
				shapeManager.update(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.ELLIPSE){
				Ellipse shape = (Ellipse) shapeManager.getCurrentShape();
				shape.setEndPoint(end);
				shapeManager.update(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.CIRCLE){
				Circle shape = (Circle) shapeManager.getCurrentShape();
				shape.setEndPoint(end);
				shapeManager.update(shape);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// no need
	}
	
	public boolean containsPoint(Point clickedPoint, Point p) {
		if(clickedPoint.y < (p.y - 2) ||
		   clickedPoint.x < (p.x - 2) ||
		   clickedPoint.y > (p.y + 2) ||
		   clickedPoint.x > (p.x + 2) ){
			return false;
		}
		return true;
	}
}
