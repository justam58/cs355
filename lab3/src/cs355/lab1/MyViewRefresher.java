package cs355.lab1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import cs355.ViewRefresher;
import cs355.models.*;

public class MyViewRefresher implements ViewRefresher{

	private ShapeManager shapeManager = ShapeManager.getInstance();
	private Transformation tfm = Transformation.getInstance();
	private ViewManager view = ViewManager.getInstance();
	private double stroke;
	
	@Override
	public void refreshView(Graphics2D g2d) {
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
