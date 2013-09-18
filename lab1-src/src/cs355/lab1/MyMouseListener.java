package cs355.lab1;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cs355.lab1.ShapeManager.ShapeMode;
import cs355.models.*;

public class MyMouseListener implements MouseListener, MouseMotionListener{
	
	private ShapeManager shapes = ShapeManager.getInstance(); 
	private boolean dragging;
	
	private void initDrag() {
		dragging = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//System.out.printf("click: %d %d\n", e.getX(),e.getY());
		
		if (shapes.getCurrentShapeMode() == ShapeMode.TRIANGLE){
			
			if(!shapes.isTriangleStarted()){
				Triangle triangle = new Triangle(new Point(e.getX(),e.getY()));
				shapes.add(triangle);
			}
			else{
				Triangle triangle = (Triangle) shapes.getCurrentShape();
				if(triangle.getPoints().size() == 1){
					triangle.addPoint(new Point(e.getX(),e.getY()));
					shapes.update(triangle);
				}
				else if(triangle.getPoints().size() == 2){
					triangle.addPoint(new Point(e.getX(),e.getY()));
					shapes.moveOn();
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
		
		//System.out.printf("press: %d %d\n", e.getX(),e.getY());
		
		if(shapes.getCurrentShapeMode() != ShapeMode.TRIANGLE){
			dragging = true;
			
			Point start = new Point(e.getX(),e.getY());
			
			if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
				Line shape = new Line(start);
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
				Rectangle shape = new Rectangle(start);
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
				Square shape = new Square(start);
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
				Ellipse shape = new Ellipse(start);
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
				Circle shape = new Circle(start);
				shapes.add(shape);
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

		if (dragging && (shapes.getCurrentShapeMode() != ShapeMode.TRIANGLE)){	
			shapes.moveOn();
			initDrag();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (dragging) {	
			//System.out.printf("dragging: %d %d\n", e.getX(),e.getY());

			Point end = new Point(e.getX(),e.getY());
			
			if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
				Line shape = (Line) shapes.getCurrentShape();
				shape.setEndPoint(end);
				shapes.update(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
				Rectangle shape = (Rectangle) shapes.getCurrentShape();
				shape.setEndPoint(end);
				shapes.update(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
				Square shape = (Square) shapes.getCurrentShape();
				shape.setEndPoint(end);
				shapes.update(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
				Ellipse shape = (Ellipse) shapes.getCurrentShape();
				shape.setEndPoint(end);
				shapes.update(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
				Circle shape = (Circle) shapes.getCurrentShape();
				shape.setEndPoint(end);
				shapes.update(shape);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// no need
	}

}
