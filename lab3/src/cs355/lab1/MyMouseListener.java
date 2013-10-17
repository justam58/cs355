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
	private ViewManager view = ViewManager.getInstance();
	
	private boolean resizing;
	private boolean dragging;
	private boolean rotating;
	private double d_dragStartX;
	private double d_dragStartY;
	
	private Point2D start = null;
	
	private void pickStart(Point2D p, ArrayList<Point2D> points, Shape shape){
		int index = indexOf(p,points);
		Point2D s = new Point2D.Double();
		if(index == 0){
			s = points.get(2);
		}
		else if(index == 1){
			s = points.get(3);
		}
		else if(index == 2){
			s = points.get(0);
		}
		else{
			s = points.get(1);
		}
		AffineTransform t = tfm.objectToWorld(shape);
		Point2D sp = new Point2D.Double();
		t.transform(s, sp);	
		start = sp;
		System.out.println(start);
	}
	
	private void initDrag() {
		resizing = false;
		dragging = false;
		rotating = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		Point2D end = new Point2D.Double(e.getX(),e.getY());
		
		if(rotating){
			Shape shape = shapeManager.getSelectedShape();
			if(shape.getClass().getName() != "cs355.models.Line" &&
			   shape.getClass().getName() != "cs355.models.Circle" ){
				AffineTransform t;
				if(shape.getClass().getName() != "cs355.models.Triangle"){
					t = tfm.obejctToView(shapeManager.getSelectedShape());
				}
				else{
					t = tfm.worldToView();
				}
				Point2D cp = new Point2D.Double();
				t.transform(shape.getCenter(), cp);			
				double angle = getRotateAngle(cp,end) - Math.PI/2;
				shape.setRotation(angle);
				shapeManager.updateSelectedShape(shape);
			}
		}
		
		if(dragging){
			
			AffineTransform t = tfm.viewToWorld();
			Point2D endp = new Point2D.Double();
			t.transform(end, endp);
			
			double d_deltaX = (endp.getX() - d_dragStartX);
			double d_deltaY = (endp.getY() - d_dragStartY);
			
			d_dragStartX = endp.getX();
			d_dragStartY = endp.getY();

			Shape shape = shapeManager.getSelectedShape();
			String shapeType = shape.getClass().getName();
			switch(shapeType){
				case "cs355.models.Line" :			Line line = (Line) shape;
													line.move(d_deltaX,d_deltaY);
													break;
				case "cs355.models.Square" :		Square square = (Square) shape;
													square.move(d_deltaX,d_deltaY);
													break;
				case "cs355.models.Rectangle" :		Rectangle rectangle = (Rectangle) shape;
													rectangle.move(d_deltaX,d_deltaY);
													break;
				case "cs355.models.Circle" :		Circle circle = (Circle) shape;
													circle.move(d_deltaX,d_deltaY);
													break;
				case "cs355.models.Ellipse" :		Ellipse ellipse = (Ellipse) shape;
													ellipse.move(d_deltaX,d_deltaY);
													break;
				case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
													triangle.move(d_deltaX,d_deltaY);
													break;		
				default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
	            									break;
			}
			shapeManager.updateSelectedShape(shape);
		}
		
		if (resizing) {	
			
			AffineTransform t = tfm.viewToWorld();
			Point2D endp = new Point2D.Double();
			t.transform(end, endp);
			
			double delta_x = 0;
			double delta_y = 0;
			try{
				delta_x = endp.getX() - start.getX();
				delta_y = endp.getY() - start.getY();
			}
			catch(java.lang.NullPointerException ex){
				
			}
			
			if(shapeManager.getCurrentMode() == Mode.SELECT){
				Shape shape = shapeManager.getSelectedShape();
				String shapeType = shape.getClass().getName();
				switch(shapeType){
					case "cs355.models.Line" :			Line line = (Line) shape;
														line.setEndPoint(endp);
														break;
					case "cs355.models.Square" :		Square square = (Square) shape;
														square = consturctSquare(square,delta_x,delta_y);
														break;
					case "cs355.models.Rectangle" :		Rectangle rectangle = (Rectangle) shape;
														rectangle = consturctRectangle(rectangle,delta_x,delta_y);
														break;
					case "cs355.models.Circle" :		Circle circle = (Circle) shape;
														circle = consturctCircle(circle,delta_x,delta_y);
														break;
					case "cs355.models.Ellipse" :		Ellipse ellipse = (Ellipse) shape;
														ellipse = consturctEllipse(ellipse,delta_x,delta_y);
														break;
					case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
														triangle.updateSelectedPoint(endp);
														break;		
					default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
	                									break;
				}
				shapeManager.updateSelectedShape(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.LINE){
				Line shape = (Line) shapeManager.getCurrentShape();
				shape.setEndPoint(endp);
				shapeManager.updateDrawingShape(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.RECTANGLE){
				Rectangle shape = (Rectangle) shapeManager.getCurrentShape();
				shape = consturctRectangle(shape,delta_x,delta_y);
				shapeManager.updateDrawingShape(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.SQUARE){
				Square shape = (Square) shapeManager.getCurrentShape();
				shape = consturctSquare(shape,delta_x,delta_y);
				shapeManager.updateDrawingShape(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.ELLIPSE){
				Ellipse shape = (Ellipse) shapeManager.getCurrentShape();
				shape = consturctEllipse(shape,delta_x,delta_y);
				shapeManager.updateDrawingShape(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.CIRCLE){
				Circle shape = (Circle) shapeManager.getCurrentShape();
				shape = consturctCircle(shape,delta_x,delta_y);
				shapeManager.updateDrawingShape(shape);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Point p = new Point(e.getX(),e.getY());
		
		AffineTransform t = tfm.viewToWorld();
		Point2D cp = new Point2D.Double();
		t.transform(p, cp);
		
		if (shapeManager.getCurrentMode() == Mode.TRIANGLE){
			
			if(!shapeManager.isTriangleStarted()){
				Triangle triangle = new Triangle(cp);
				shapeManager.add(triangle);
				//System.out.println("start triangle");
			}
			else{
				Triangle triangle = (Triangle) shapeManager.getCurrentShape();
				if(triangle.getPointsSize() == 1){
					triangle.addPoint(cp);
					shapeManager.updateDrawingShape(triangle);
					//System.out.println("continue triangle");
				}
				else if(triangle.getPointsSize() == 2){
					triangle.addPoint(cp);
					shapeManager.moveOn();
					//System.out.println("done triangle");
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.printf("press: %d %d\n", e.getX(),e.getY());
		
		Point2D p = new Point2D.Double(e.getX(),e.getY());
		
		if(shapeManager.getCurrentMode() == Mode.SELECT){
			if(shapeManager.getSelectedIndex() != -1){
				System.out.println("something selected");
				Shape shape = shapeManager.getSelectedShape();
				ArrayList<Point2D> resizePoints = shape.getResizePoints();
				boolean foundResizePoint = false;
				for(int i = 0; i < resizePoints.size(); i++){
					AffineTransform t;
					if(shape.getClass().getName() != "cs355.models.Line" &&
						shape.getClass().getName() != "cs355.models.Triangle"){
						t = tfm.viewToObejct(shapeManager.getSelectedShape());
					}
					else{
						t = tfm.viewToWorld();
					}
					Point2D cp = new Point2D.Double();
					t.transform(p, cp);
					if(containsPoint(cp,resizePoints.get(i),shape)){
						foundResizePoint = true;
						String shapeType = shape.getClass().getName();
						switch(shapeType){
							case "cs355.models.Line" :			Line line = (Line) shape;
																line.setStart(cp);
																shapeManager.updateSelectedShape(line);
																break;
							case "cs355.models.Square" :		pickStart(cp,resizePoints,shape);
																break;
							case "cs355.models.Rectangle" :		pickStart(cp,resizePoints,shape);
																break;
							case "cs355.models.Circle" :		pickStart(cp,resizePoints,shape);
																break;
							case "cs355.models.Ellipse" :		pickStart(cp,resizePoints,shape);
																break;
							case "cs355.models.Triangle" :		Triangle triangle = (Triangle) shape;
																triangle.setSelectedPoint(p);
																shapeManager.updateSelectedShape(triangle);
																break;		
							default:							System.out.printf("Invalid Shape : %s\n" ,shapeType);
			                									break;
						}
						resizing = true;
						System.out.println("resize");
						break;
					}
				}
				System.out.printf("selected index is %d\n",shapeManager.getSelectedIndex());
				if(!foundResizePoint){
					AffineTransform t;
					if(shape.getClass().getName() != "cs355.models.Line" &&
						shape.getClass().getName() != "cs355.models.Triangle"){
						t = tfm.viewToObejct(shapeManager.getSelectedShape());
					}
					else{
						t = tfm.viewToWorld();
					}
					Point2D cp = new Point2D.Double();
					t.transform(p, cp);
					if(shapeManager.getSelectedShape().contains(cp)){
						AffineTransform tt = tfm.viewToWorld();
						Point2D dp = new Point2D.Double();
						tt.transform(p, dp);
						System.out.println("drag");
						dragging = true;
						d_dragStartX = dp.getX();
						d_dragStartY = dp.getY();		
					}
					else if(shapeManager.getSelectedShape().getClass().getName() != "cs355.models.Circle" && 
							shapeManager.getSelectedShape().getClass().getName() != "cs355.models.Line" &&
							containsPoint(cp,shapeManager.getSelectedShape().getRotatePoint(),shape)){
						System.out.println("rotate");
						rotating = true;
					}	
				}
			}
			if(!resizing && !rotating && !dragging){
				System.out.println("nothing selected");
				ArrayList<Shape> shapes = shapeManager.getShapes();
				for(int i = 0; i < shapes.size(); i++){
					AffineTransform t;
					if(shapes.get(i).getClass().getName() != "cs355.models.Line" &&
					   shapes.get(i).getClass().getName() != "cs355.models.Triangle"){
						t = tfm.viewToObejct(shapes.get(i));
					}
					else{
						t = tfm.viewToWorld();
					}
					Point2D cp = new Point2D.Double();
					t.transform(p, cp);
					boolean contains = shapes.get(i).contains(cp);
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
			resizing = true;
			
			Point2D start = new Point(e.getX(),e.getY());
			
			AffineTransform t = tfm.viewToWorld();
			Point2D sp = new Point2D.Double();
			t.transform(start, sp);
			
			if (shapeManager.getCurrentMode() == Mode.LINE){
				Line shape = new Line(start);
				this.start = sp;
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.RECTANGLE){
				Rectangle shape = new Rectangle(start);
				this.start = sp;
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.SQUARE){
				Square shape = new Square(sp);
				this.start = sp;
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.ELLIPSE){
				Ellipse shape = new Ellipse(start);
				this.start = sp;
				shapeManager.add(shape);
			}
			
			if (shapeManager.getCurrentMode() == Mode.CIRCLE){
				Circle shape = new Circle(start);
				this.start = sp;
				shapeManager.add(shape);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if ((resizing || dragging || rotating) && (shapeManager.getCurrentMode() != Mode.TRIANGLE)){
			if(shapeManager.getCurrentMode() != Mode.SELECT){
				shapeManager.moveOn();
			}
			initDrag();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	public double getRotateAngle(Point2D start, Point2D end){
		double x = start.getX() - end.getX() ;
		double y = start.getY() - end.getY() ;
		return Math.atan2(y, x);
	}
	
	public boolean containsPoint(Point2D clickedPoint, Point2D p, Shape s) {
		if(Math.abs(clickedPoint.getY()-p.getY()) > (8/view.getScale()) ||
		   Math.abs(clickedPoint.getX()-p.getX()) > (8/view.getScale())){
			return false;
		}
		return true;
	}
	
	private int indexOf(Point2D s, ArrayList<Point2D> points){
		int result = -1;
		for(int i = 0; i < points.size(); i++){
			if(Math.abs(points.get(i).getX() - s.getX()) <= 2 &&
			   Math.abs(points.get(i).getY() - s.getY()) <= 2){
				result = i;
				break;
			}
		}
		return result;
	}
	
	private Square consturctSquare(Square shape, double delta_x, double delta_y){
		double size = Math.min(Math.abs(delta_x), Math.abs(delta_y));
		size = size * Math.cos(shape.getRotation());
		Point2D s = new Point2D.Double();
		if(delta_x <= 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX() - size, start.getY() - size); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			s = new Point2D.Double(start.getX() - size, start.getY()); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX(), start.getY() - size); 
		}
		else{
			s = new Point2D.Double(start.getX(), start.getY()); 
		}
		shape.setSize(size);
		shape.setOrigin(s);
		return shape;
	}
	
	private Circle consturctCircle(Circle shape, double delta_x, double delta_y){
		double size = Math.min(Math.abs(delta_x), Math.abs(delta_y));
		size = size * Math.cos(shape.getRotation());
		Point2D s = new Point2D.Double();
		if(delta_x <= 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX() - size, start.getY() - size); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			s = new Point2D.Double(start.getX() - size, start.getY()); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX(), start.getY() - size); 
		}
		else{
			s = new Point2D.Double(start.getX(), start.getY()); 
		}
		shape.setSize(size);
		shape.setOrigin(s);
		return shape;
	}
	
	private Rectangle consturctRectangle(Rectangle shape, double delta_x, double delta_y) {
		double width = Math.abs(delta_x);
		double height = Math.abs(delta_y);
		Point2D s = new Point2D.Double();
		if(delta_x <= 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX() - width, start.getY() - height); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			s = new Point2D.Double(start.getX() - width, start.getY()); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX(), start.getY() - height); 
		}
		else{
			s = new Point2D.Double(start.getX(), start.getY()); 
		}
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setOrigin(s);
		return shape;
	}
	
	private Ellipse consturctEllipse(Ellipse shape, double delta_x, double delta_y) {
		double width = Math.abs(delta_x);
		double height = Math.abs(delta_y);
		Point2D s = new Point2D.Double();
		if(delta_x <= 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX() - width, start.getY() - height); 
		}
		else if(delta_x <= 0 && delta_y > 0){
			s = new Point2D.Double(start.getX() - width, start.getY()); 
		}
		else if(delta_x > 0 && delta_y <= 0){
			s = new Point2D.Double(start.getX(), start.getY() - height); 
		}
		else{
			s = new Point2D.Double(start.getX(), start.getY()); 
		}
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setOrigin(s);
		return shape;
	}
}
