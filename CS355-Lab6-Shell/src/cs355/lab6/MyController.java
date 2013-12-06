package cs355.lab6;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.*;
import cs355.lab6.ShapeManager.Mode;

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
		GUIFunctions.refresh();
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		while(iterator.hasNext()){
			Integer key = iterator.next();
			char c = Character.toChars(key)[0];
			switch(c){
				case 'A' :		threeD.setCameraX(threeD.getCameraX()-Math.cos(Math.toRadians(threeD.getCameraDirection())));
								threeD.setCameraZ(threeD.getCameraZ()-Math.sin(Math.toRadians(threeD.getCameraDirection())));
								break;
				case 'D' :		threeD.setCameraX(threeD.getCameraX()+Math.cos(Math.toRadians(threeD.getCameraDirection())));
								threeD.setCameraZ(threeD.getCameraZ()+Math.sin(Math.toRadians(threeD.getCameraDirection())));
								break;
				case 'W' :		threeD.setCameraX(threeD.getCameraX()-Math.sin(Math.toRadians(threeD.getCameraDirection())));
								threeD.setCameraZ(threeD.getCameraZ()+Math.cos(Math.toRadians(threeD.getCameraDirection())));
								break;
				case 'S' :		threeD.setCameraX(threeD.getCameraX()+Math.sin(Math.toRadians(threeD.getCameraDirection())));
								threeD.setCameraZ(threeD.getCameraZ()-Math.cos(Math.toRadians(threeD.getCameraDirection())));
								break;
				case 'Q' :		threeD.setCameraDirection(threeD.getCameraDirection()+1);
								break;
				case 'E' :		threeD.setCameraDirection(threeD.getCameraDirection()-1);
								break;
				case 'R' :		threeD.setCameraY(threeD.getCameraY()+1);
								break;
				case 'F' :		threeD.setCameraY(threeD.getCameraY()-1);
								break;
				case 'H' :		threeD.reset();
								break;		
				default:		System.out.printf("Invalid key\n");
            					break;
			}
			System.out.println("draw");
			GUIFunctions.refresh();
//			System.out.println(threeD.getCameraDirection());
//			System.out.printf("cos %f\n",Math.cos(Math.toRadians(threeD.getCameraDirection())));
//			System.out.printf("sin %f\n",Math.sin(Math.toRadians(threeD.getCameraDirection())));
//			System.out.printf("%d %d %d\n",(int)threeD.getCameraX(),(int)threeD.getCameraY(),(int)threeD.getCameraZ());
		}
	}

	@Override
	public void doEdgeDetection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSharpen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doMedianBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUniformBlur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeContrast(int contrastAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doLoadImage(BufferedImage openImage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleBackgroundDisplay() {
		// TODO Auto-generated method stub
		
	}

}
