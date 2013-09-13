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
	private int d_dragStartX;
	private int d_dragStartY;
	
	private void initDrag() {
		dragging = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//System.out.printf("click: %d %d\n", e.getX(),e.getY());
		
		if (shapes.getCurrentShapeMode() == ShapeMode.TRIANGLE){
			
			if(!shapes.isTriangleStarted()){
				System.out.println("start drawing triangle");
				Triangle triangle = new Triangle(new Point(e.getX(),e.getY()));
				shapes.add(triangle);
			}
			else{
				Triangle triangle = (Triangle) shapes.getCurrentShape();
				if(triangle.getPoints().size() == 1){
					System.out.println("continue drawing triangle");
					triangle.addPoint(new Point(e.getX(),e.getY()));
					shapes.update(triangle);
				}
				else if(triangle.getPoints().size() == 2){
					triangle.addPoint(new Point(e.getX(),e.getY()));
					shapes.moveOn();
					System.out.println("done drawing triangle");
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
			d_dragStartX = e.getX();
			d_dragStartY = e.getY();	
			
			if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
				System.out.println("start drawing line");
				Line shape = new Line(new Point(e.getX(),e.getY()));
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
				System.out.println("start drawing rectangle");
				Rectangle shape = new Rectangle(new Point(e.getX(),e.getY()));
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
				System.out.println("start drawing square");
				Square shape = new Square(new Point(e.getX(),e.getY()));
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
				System.out.println("start drawing ellipse");
				Ellipse shape = new Ellipse(new Point(e.getX(),e.getY()));
				shapes.add(shape);
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
				System.out.println("start drawing circle");
				Circle shape = new Circle(new Point(e.getX(),e.getY()));
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
			
			int upperLeftCornerX = d_dragStartX;
			int upperLeftCornerY = d_dragStartY;
			
			int d_deltaX = (e.getX() - d_dragStartX);
			int d_deltaY = (e.getY() - d_dragStartY);
			
			int height = Math.abs(d_deltaY);
			int width = Math.abs(d_deltaX);
			
			if(d_deltaX <= 0){
				upperLeftCornerX += d_deltaX;
			}
			if(d_deltaY <= 0){
				upperLeftCornerY += d_deltaY;
			}	
			
			Point upperLeftCorner = new Point(upperLeftCornerX,upperLeftCornerY);
			
			if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
				Line shape = (Line) shapes.getCurrentShape();
				shape.setEndPoint(new Point(e.getX(),e.getY()));
				shapes.update(shape);
				System.out.println("done drawing line");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
				Rectangle shape = (Rectangle) shapes.getCurrentShape();
				shape.setHeight(height);
				shape.setWidth(width);
				shape.setUpperLeftCorner(upperLeftCorner);
				shapes.update(shape);
				System.out.println("done drawing rectangle");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
				Square shape = (Square) shapes.getCurrentShape();
				shape.setSize(Math.min(width,height));
				shape.setUpperLeftCorner(upperLeftCorner);
				shapes.update(shape);
				System.out.println("done drawing square");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
				Ellipse shape = (Ellipse) shapes.getCurrentShape();
				shape.setHeight(height);
				shape.setWidth(width);
				shape.setUpperLeftCorner(upperLeftCorner);
				shapes.update(shape);
				System.out.println("done drawing ellipse");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
				Circle shape = (Circle) shapes.getCurrentShape();
				shape.setRadius(Math.min(width,height));
				shape.setUpperLeftCorner(upperLeftCorner);
				shapes.update(shape);
				System.out.println("done drawing circle");
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// no need
		
	}

}
