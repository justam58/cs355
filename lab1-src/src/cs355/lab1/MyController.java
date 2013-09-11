package cs355.lab1;

import java.awt.Color;
import java.util.ArrayList;

import cs355.*;
import cs355.models.*;

public class MyController implements CS355Controller{
	
	private Color currentColor;
	private ShapeMode  currentShape;
	private ShapeManager shapes = ShapeManager.getInstance();
	
	public enum ShapeMode { TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE }

	@Override
	public void colorButtonHit(Color c) {
		currentColor = c;
		GUIFunctions.changeSelectedColor(c);
	}

	@Override
	public void triangleButtonHit() {
		// TODO
	}

	@Override
	public void squareButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rectangleButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void circleButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ellipseButtonHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lineButtonHit() {
		// TODO Auto-generated method stub
		
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
