package cs355.lab1;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cs355.GUIFunctions;
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
			Triangle shape = (Triangle) shapes.getCurrentShape();
			
			if(shape == null){
				//System.out.println("start drawing triangle");
				shapes.setCurrentShape(new Triangle());
				shape = (Triangle) shapes.getCurrentShape();
				shape.addPoint(new Point(e.getX(),e.getY()));
			}
			else if(shape.getPoints().size() < 2){
				//System.out.println("continue drawing triangle");
				shape.addPoint(new Point(e.getX(),e.getY()));
			}
			else{
				shape.addPoint(new Point(e.getX(),e.getY()));
				shapes.add();
				shapes.moveOn();
				GUIFunctions.refresh();
				//System.out.println("done drawing triangle");
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
		
		if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
			
			dragging = true;
			
			//System.out.println("start drawing line");
			Line shape = new Line();
			shape.addPoint(new Point(e.getX(),e.getY()));
			shapes.setCurrentShape(shape);
			shapes.add();
		}
		
		if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
			
			dragging = true;
			d_dragStartX = e.getX();
			d_dragStartY = e.getY();		
			
			//System.out.println("start drawing rectanagle");
			Rectangle shape = new Rectangle();
			shape.setUpperLeftCorner(new Point(e.getX(),e.getY()));
			shape.setHeight(0);
			shape.setWidth(0);
			shapes.setCurrentShape(shape);
			shapes.add();
		}
		
		if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
			
			dragging = true;
			d_dragStartX = e.getX();
			d_dragStartY = e.getY();		
			
			//System.out.println("start drawing square");
			Square shape = new Square();
			shape.setUpperLeftCorner(new Point(e.getX(),e.getY()));
			shape.setSize(0);
			shapes.setCurrentShape(shape);
			shapes.add();
		}
		
		if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
			
			dragging = true;
			d_dragStartX = e.getX();
			d_dragStartY = e.getY();		
			
			//System.out.println("start drawing ellipse");
			Ellipse shape = new Ellipse();
			shape.setUpperLeftCorner(new Point(e.getX(),e.getY()));
			shape.setHeight(0);
			shape.setWidth(0);
			shapes.setCurrentShape(shape);
			shapes.add();
		}
		
		if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
			
			dragging = true;
			d_dragStartX = e.getX();
			d_dragStartY = e.getY();		
			
			//System.out.println("start drawing cirlce");
			Circle shape = new Circle();
			shape.setUpperLeftCorner(new Point(e.getX(),e.getY()));
			shape.setRadius(0);
			shapes.setCurrentShape(shape);
			shapes.add();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (shapes.getCurrentShapeMode() != ShapeMode.TRIANGLE ){
			
			shapes.moveOn();
			initDrag();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (dragging) {
			
			//System.out.printf("dragging: %d %d\n", e.getX(),e.getY());
			
			if (shapes.getCurrentShapeMode() == ShapeMode.LINE){
				Line shape = (Line) shapes.getCurrentShape();
				shape.setEndPoint(new Point(e.getX(),e.getY()));
				shapes.setCurrentShape(shape);
				shapes.update();
				GUIFunctions.refresh();
				//System.out.println("done drawing line");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.RECTANGLE){
				
				int d_deltaX = (e.getX() - d_dragStartX);
				int d_deltaY = (e.getY() - d_dragStartY);
				
				Rectangle shape = (Rectangle) shapes.getCurrentShape();
				shape.setHeight(d_deltaY);
				shape.setWidth(d_deltaX);
				shapes.setCurrentShape(shape);
				shapes.update();
				GUIFunctions.refresh();
				//System.out.println("done drawing rectangle");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.SQUARE){
				
				int d_deltaX = (e.getX() - d_dragStartX);
				int d_deltaY = (e.getY() - d_dragStartY);
				
				Square shape = (Square) shapes.getCurrentShape();
				shape.setSize(Math.min(d_deltaX,d_deltaY));
				shapes.setCurrentShape(shape);
				shapes.update();
				GUIFunctions.refresh();
				//System.out.println("done drawing square");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.ELLIPSE){
				
				int d_deltaX = (e.getX() - d_dragStartX);
				int d_deltaY = (e.getY() - d_dragStartY);
				
				Ellipse shape = (Ellipse) shapes.getCurrentShape();
				shape.setHeight(d_deltaY);
				shape.setWidth(d_deltaX);
				shapes.setCurrentShape(shape);
				shapes.update();
				GUIFunctions.refresh();
				//System.out.println("done drawing ellipse");
			}
			
			if (shapes.getCurrentShapeMode() == ShapeMode.CIRCLE){
				
				int d_deltaX = (e.getX() - d_dragStartX);
				int d_deltaY = (e.getY() - d_dragStartY);
				
				Circle shape = (Circle) shapes.getCurrentShape();
				shape.setRadius(Math.min(d_deltaX,d_deltaY));
				shapes.setCurrentShape(shape);
				shapes.update();
				GUIFunctions.refresh();
				//System.out.println("done drawing circle");
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// no need
		
	}

}
