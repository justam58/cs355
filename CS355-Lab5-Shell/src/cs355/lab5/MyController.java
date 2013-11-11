package cs355.lab5;

import java.awt.Color;
import java.util.Iterator;

import cs355.*;
import cs355.lab5.ShapeManager.Mode;

public class MyController implements CS355Controller{
	
	private ShapeManager shapes = ShapeManager.getInstance();
	private ViewState view = ViewState.getInstance();
	private ThreeDState threeD = ThreeDState.getInstance();

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
		view.zoomIn();
	}

	@Override
	public void zoomOutButtonHit() {
		view.zoomOut();
	}

	@Override
	public void hScrollbarChanged(int value) {
		view.sethScroll(value);
	}

	@Override
	public void vScrollbarChanged(int value) {
		view.setvScroll(value);
	}

	@Override
	public void toggle3DModelDisplay() {
		threeD.toggle();
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		while(iterator.hasNext()){
			Integer key = iterator.next();
			switch(key){
				case 65 :		threeD.setCameraX(threeD.getCameraX()+1);
								break;
				case 68 :		threeD.setCameraX(threeD.getCameraX()-1);
								break;
				case 87 :		threeD.setCameraZ(threeD.getCameraZ()+1);
								break;
				case 83 :		threeD.setCameraZ(threeD.getCameraZ()-1);
								break;
				case 81 :		threeD.setCameraDirection(threeD.getCameraDirection()-1);
								break;
				case 69 :		threeD.setCameraDirection(threeD.getCameraDirection()+1);
								break;
				case 82 :		threeD.setCameraY(threeD.getCameraY()-1);
								break;
				case 70 :		threeD.setCameraY(threeD.getCameraY()+1);
								break;
				case 72 :		threeD.reset();
								break;		
				default:		System.out.printf("Invalid key\n");
            					break;
			}
		}
	}

}
