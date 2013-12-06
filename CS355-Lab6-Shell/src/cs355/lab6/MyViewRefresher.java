package cs355.lab6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.HouseModel;
import cs355.Line3D;
import cs355.Point3D;
import cs355.ViewRefresher;
import cs355.WireFrame;
import cs355.models.*;

public class MyViewRefresher implements ViewRefresher{

	private ShapeManager shapeManager = ShapeManager.getInstance();
	private Transformation tfm = Transformation.getInstance();
	private ViewState view = ViewState.getInstance();
	private ThreeDState threeD = ThreeDState.getInstance();
	private ThreeDTest tdt = ThreeDTest.getInstance();
	private MyImage image = MyImage.getInstance();
	private double stroke;
	
	@Override
	public void refreshView(Graphics2D g2d) {
		if(image.isOn()){
			g2d.setTransform(tfm.worldToView());
			int h = image.getHeight();
			int w = image.getWidth();
			g2d.drawImage(image.getImage(), null, 1024-(w/2), 1024-(h/2));
		}
		stroke = 4/view.getScale();
		ArrayList<Shape> shapes = shapeManager.getShapes();
		int index = shapeManager.getSelectedIndex();
		for(int i = 0; i < shapes.size(); i++){
			g2d.setColor(shapes.get(i).getColor());
			boolean selected = (i==index);
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
				default:							System.out.printf("Invalid Shape : %s\n" ,shapes.get(i).getClass().getName());
                									break;
			}
		}
		if(threeD.isOn()){			
			g2d.setTransform(tfm.worldToView());
			g2d.setColor(Color.WHITE);
			WireFrame model = new HouseModel();
			Iterator<Line3D> linesItr = model.getLines();
	        while(linesItr.hasNext()) {
	        	Line3D line = linesItr.next();
	        	Point3D start = new cs355.Point3D(line.start.x,line.start.y,line.start.z);
	        	Point3D end = new cs355.Point3D(line.end.x,line.end.y,line.end.z);
	    		Line2D l = tdt.test(start,end);
	        	if(l != null){
	        		g2d.drawLine((int)l.getX1(), (int)l.getY1(), (int)l.getX2(), (int)l.getY2());
	        	}
	        }
		}
	}
	
	private void drawTrianagle(Graphics2D g2d, Triangle shape, boolean selected) {
		if(shape.getPointsSize() != 3){
			return;
		}
		
		g2d.setTransform(tfm.worldToView());
		
		int[] xs = new int[3];
		int[] ys = new int[3];
		ArrayList<Point2D> points = shape.getPoints();
		for(int i = 0; i < 3; i++){
			xs[i] = (int)points.get(i).getX();
			ys[i] = (int)points.get(i).getY();
		}

		g2d.fillPolygon(xs,ys,3);
		
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
			g2d.fillRect((int)(shape.getRotatePoint().getX()-stroke), (int)(shape.getRotatePoint().getY()-stroke), (int)stroke*2, (int)stroke*2);
		}
	}

	private void drawLine(Graphics2D g2d, Line shape, boolean selected) {
		g2d.setTransform(tfm.worldToView());
		ArrayList<Point2D> points = shape.getPoints();
		Point2D a = points.get(0);
		Point2D b = points.get(1);
		g2d.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
		}
	}

	private void drawEllipse(Graphics2D g2d, Ellipse shape, boolean selected) {
		double height = shape.getHeight();
		double width = shape.getWidth();
		g2d.setTransform(tfm.obejctToView(shape));
		g2d.fillOval(0, 0, (int)width, (int)height);
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
			g2d.fillRect((int)(shape.getRotatePoint().getX()-stroke), (int)(shape.getRotatePoint().getY()-stroke), (int)stroke*2, (int)stroke*2);
		}
	}

	private void drawRectangle(Graphics2D g2d, Rectangle shape, boolean selected) {
		double height = shape.getHeight();
		double width = shape.getWidth();
		g2d.setTransform(tfm.obejctToView(shape));
		g2d.fillRect(0, 0, (int)width, (int)height);
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
			g2d.fillRect((int)(shape.getRotatePoint().getX()-stroke), (int)(shape.getRotatePoint().getY()-stroke), (int)stroke*2, (int)stroke*2);
		}
	}

	private void drawCircle(Graphics2D g2d, Circle shape, boolean selected) {
		double size = shape.getSize();
		g2d.setTransform(tfm.obejctToView(shape));
		g2d.fillOval(0, 0, (int)size, (int)size);
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
		}
	}

	private void drawSquare(Graphics2D g2d, Square shape, boolean selected){
		double size = shape.getSize();
		g2d.setTransform(tfm.obejctToView(shape));
		g2d.fillRect(0, 0, (int)size, (int)size);
		if(selected){
			drawResizePoints(g2d,shape.getResizePoints());
			g2d.fillRect((int)(shape.getRotatePoint().getX()-stroke), (int)(shape.getRotatePoint().getY()-stroke), (int)stroke*2, (int)stroke*2);
		}
	}
	
	private void drawResizePoints(Graphics2D g2d, ArrayList<Point2D> points){
		g2d.setColor(Color.orange);
		for(int i = 0; i < points.size(); i++){
			g2d.fillRect((int)(points.get(i).getX()-stroke), (int)(points.get(i).getY()-stroke), (int)stroke*2, (int)stroke*2);
		}
	}

}
