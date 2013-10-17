package cs355.lab1;

import java.awt.Graphics2D;
import java.util.ArrayList;

import cs355.ViewRefresher;
import cs355.models.*;

public class MyViewRefresher implements ViewRefresher{

	private ShapeManager shapeManager = ShapeManager.getInstance();
	private Transformation tfm = Transformation.getInstance();
	
	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = shapeManager.getShapes();
		for(int i = 0; i < shapes.size(); i++){
			g2d.setColor(shapes.get(i).getColor());
			switch(shapes.get(i).getClass().getName()){
				case "cs355.models.Square" :		drawSquare(g2d,(Square) shapes.get(i));
													break;
				default:							System.out.printf("Invalid Shape : %s\n" ,shapes.get(i).getClass().getName());
                									break;
			}
		}
	}
	
	private void drawSquare(Graphics2D g2d, Square shape){
		double size = shape.getSize();
		g2d.setTransform(tfm.obejctToView(shape));
		g2d.fillRect(0, 0, (int)size, (int)size);
	}

}
