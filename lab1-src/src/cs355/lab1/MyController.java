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
		shapes.setCurrentShape(ShapeMode.TRIANGLE);
	}

	@Override
	public void squareButtonHit() {
		shapes.setCurrentShape(ShapeMode.SQUARE);
	}

	@Override
	public void rectangleButtonHit() {
		shapes.setCurrentShape(ShapeMode.RECTANGLE);
	}

	@Override
	public void circleButtonHit() {
		shapes.setCurrentShape(ShapeMode.CIRCLE);
	}

	@Override
	public void ellipseButtonHit() {
		shapes.setCurrentShape(ShapeMode.ELLIPSE);
	}

	@Override
	public void lineButtonHit() {
		shapes.setCurrentShape(ShapeMode.LINE);
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
