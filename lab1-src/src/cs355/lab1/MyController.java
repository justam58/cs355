package cs355.lab1;

import java.awt.Color;

import cs355.*;
import cs355.lab1.ShapeManager.ShapeMode;

public class MyController implements CS355Controller{
	
	private ShapeManager shapes = ShapeManager.getInstance();

	@Override
	public void colorButtonHit(Color c) {
		shapes.setCurrentColor(c);
		GUIFunctions.changeSelectedColor(c);
	}

	@Override
	public void triangleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.TRIANGLE);
	}

	@Override
	public void squareButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.SQUARE);
	}

	@Override
	public void rectangleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.RECTANGLE);
	}

	@Override
	public void circleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.CIRCLE);
	}

	@Override
	public void ellipseButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.ELLIPSE);
	}

	@Override
	public void lineButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.LINE);
	}

	@Override
	public void selectButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zoomInButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zoomOutButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub
		
	}

}
