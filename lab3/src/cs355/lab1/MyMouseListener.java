package cs355.lab1;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import cs355.lab1.ShapeManager.Mode;
import cs355.models.*;

public class MyMouseListener implements MouseListener, MouseMotionListener{
	
	private ShapeManager shapeManager = ShapeManager.getInstance();
	private Transformation tfm = Transformation.getInstance(); 
	
	private boolean resizing;
	private boolean dragging;
	private boolean rotating;
	private int d_dragStartX;
	private int d_dragStartY;
	
	private void initDrag() {
		resizing = false;
		dragging = false;
		rotating = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.printf("%d, %d",e.getX(),e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.printf("press: %d %d\n", e.getX(),e.getY());
		
		Point p = new Point(e.getX(),e.getY());
		
		if(shapeManager.getCurrentMode() == Mode.SELECT){
			if(shapeManager.getSelectedIndex() != -1){
				Shape shape = shapeManager.getSelectedShape();
				ArrayList<Point> resizePoints = shape.getResizePoints();
				boolean foundResizePoint = false;
				for(int i = 0; i < resizePoints.size(); i++){
					if(containsPoint(p,resizePoints.get(i),shape)){
						foundResizePoint = true;
						String shapeType = shape.getClass().getName();
						switch(shapeType){
//							case "cs355.models.Line" :			Line line = (Line) shape;
//																line.setStart(p);
//																break;
							case "cs355.models.Square" :		Square square = (Square) shape;
																square.setStart(p);
																break;
//							case "cs355.models.Rectangle" :		Rectangle rectangle = (Rectangle) shape;
//																rectangle.setStart(p);
//																break;
//							case "cs355.models.Circle" :		Circle circle = (Circle) shape;
//																circle.setStart(p);
//																break;
//							case "cs355.models.Ellipse" :		Ellipse ellipse = (Ellipse) shape;
//																ellipse.setStart(p);
//																break;
//							case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
//																triangle.setSelectedPoint(p);
//																break;		
							default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
			                									break;
						}
						shapeManager.updateSelectedShape(shape);
						resizing = true;
						System.out.println("resize");
						break;
					}
				}
				System.out.printf("selected index is %d\n",shapeManager.getSelectedIndex());
				if(!foundResizePoint && shapeManager.getSelectedShape().contains(p)){
					System.out.println("drag");
					dragging = true;
					d_dragStartX = p.x;
					d_dragStartY = p.y;		
				}
				else if(!foundResizePoint && shapeManager.getSelectedShape().getClass().getName() != "cs355.models.Circle" && 
						shapeManager.getSelectedShape().getClass().getName() != "cs355.models.Line" &&
						containsPoint(p,shapeManager.getSelectedShape().getRotatePoint(),shape.getRotation(),shape.getCenter())){
					System.out.println("rotate");
					rotating = true;
				}
			}
			if(!resizing && !rotating && !dragging){
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
//		else if(shapeManager.getCurrentMode() != Mode.TRIANGLE){
//			resizing = true;
//			
//			Point start = new Point(e.getX(),e.getY());
//			
//			if (shapeManager.getCurrentMode() == Mode.LINE){
//				Line shape = new Line(start);
//				shapeManager.add(shape);
//			}
//			
//			if (shapeManager.getCurrentMode() == Mode.RECTANGLE){
//				Rectangle shape = new Rectangle(start);
//				shapeManager.add(shape);
//			}
//			
//			if (shapeManager.getCurrentMode() == Mode.SQUARE){
//				Square shape = new Square(start);
//				shapeManager.add(shape);
//			}
//			
//			if (shapeManager.getCurrentMode() == Mode.ELLIPSE){
//				Ellipse shape = new Ellipse(start);
//				shapeManager.add(shape);
//			}
//			
//			if (shapeManager.getCurrentMode() == Mode.CIRCLE){
//				Circle shape = new Circle(start);
//				shapeManager.add(shape);
//			}
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	public double getRotateAngle(Point a, Point b){
		double x = a.x - b.x;
		double y = a.y - b.y;
		return Math.atan2(y, x);
	}
	
	public boolean containsPoint(Point2D cp, Point2D p, Shape s) {
		AffineTransform t = tfm.viewToObejct(s);
		Point2D clickedPoint = new Point2D.Double();
		t.transform(cp, clickedPoint);
		if(clickedPoint.getY() < (p.getY() - 2) ||
		   clickedPoint.getX() < (p.getX() - 2) ||
		   clickedPoint.getY() > (p.getY() + 2) ||
		   clickedPoint.getX() > (p.getX() + 2) ){
			return false;
		}
		return true;
	}

}
