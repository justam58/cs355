package cs355.lab1;

import java.awt.Color;

import cs355.*;
import cs355.lab1.ShapeManager.Mode;

public class MyController implements CS355Controller{
	
	private ShapeManager shapes = ShapeManager.getInstance();

	@Override
	public void colorButtonHit(Color c) {
		shapes.setCurrentColor(c);
		GUIFunctions.changeSelectedColor(c);
	}

	@Override
	public void triangleButtonHit() {
		shapes.setCurrentMode(Mode.TRIANGLE);
	}

	@Override
	public void squareButtonHit() {
		shapes.setCurrentMode(Mode.SQUARE);
	}

	@Override
	public void rectangleButtonHit() {
		shapes.setCurrentMode(Mode.RECTANGLE);
	}

	@Override
	public void circleButtonHit() {
		shapes.setCurrentMode(Mode.CIRCLE);
	}

	@Override
	public void ellipseButtonHit() {
		shapes.setCurrentMode(Mode.ELLIPSE);
	}

	@Override
	public void lineButtonHit() {
		shapes.setCurrentMode(Mode.LINE);
	}

	@Override
	public void selectButtonHit() {
		shapes.setCurrentMode(Mode.SELECT);
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
