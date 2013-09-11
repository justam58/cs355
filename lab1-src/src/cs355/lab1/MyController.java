package cs355.lab1;

import java.awt.Color;

import cs355.CS355Controller;
import cs355.GUIFunctions;

public class MyController implements CS355Controller{
	
	private Color currentColor;

	@Override
	public void colorButtonHit(Color c) {
		currentColor = c;
		GUIFunctions.changeSelectedColor(c);
		GUIFunctions.refresh();
	}

	@Override
	public void triangleButtonHit() {
		// TODO Auto-generated method stub
		
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
