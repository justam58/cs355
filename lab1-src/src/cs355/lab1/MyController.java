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
		shapes.setCurrentShape(null);
	}

	@Override
	public void triangleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.TRIANGLE);
		shapes.setCurrentShape(null);
	}

	@Override
	public void squareButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.SQUARE);
		shapes.setCurrentShape(null);
	}

	@Override
	public void rectangleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.RECTANGLE);
		shapes.setCurrentShape(null);
	}

	@Override
	public void circleButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.CIRCLE);
		shapes.setCurrentShape(null);
	}

	@Override
	public void ellipseButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.ELLIPSE);
		shapes.setCurrentShape(null);
	}

	@Override
	public void lineButtonHit() {
		shapes.setCurrentShapeMode(ShapeMode.LINE);
		shapes.setCurrentShape(null);
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
