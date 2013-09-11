package cs355.lab1;

import java.awt.Graphics2D;
import java.util.ArrayList;

import cs355.ViewRefresher;
import cs355.models.Shape;

public class MyViewRefresher implements ViewRefresher{

	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = ShapeManager.getInstance().getShapes();
		
		for(int i = 0; i < shapes.size(); i++){
			switch(shapes.get(i).getClass().getName()){
				case "Line" :		drawLine(g2d);
									break;
				case "Square" :		drawSquare(g2d);
									break;
				case "Rectangle" :	drawRectangle(g2d);
									break;
				case "Circle" :		drawCircle(g2d);
									break;
				case "Ellipse" :	drawEllipse(g2d);
									break;
				case "Triangle" :	drawTrianagle(g2d);
									break;		
				default:			System.out.println("Invalid Shape");
                					break;
			}
		}
	}
	
	private void drawLine(Graphics2D g2d){
		// TODO
	}
	
	private void drawSquare(Graphics2D g2d){
		// TODO
	}
	
	private void drawRectangle(Graphics2D g2d){
		// TODO
	}
	
	private void drawCircle(Graphics2D g2d){
		// TODO
	}
	
	private void drawEllipse(Graphics2D g2d){
		// TODO
	}
	
	private void drawTrianagle(Graphics2D g2d){
		// TODO
	}

}
