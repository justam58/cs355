package cs355.lab6;

import java.awt.geom.Point2D;

import cs355.GUIFunctions;

public class ViewState {
	
	// singleton
    private static ViewState instance = new ViewState();

    public static ViewState getInstance() {
        return instance;
    }
    
    private double scale;
    private int vScroll;
    private int hScroll;
    
    public ViewState(){
    	this.scale = 1;
    	this.vScroll = 0;
    	this.vScroll = 0;
    }
    
    public Point2D getOrigin(){
    	double x = vScroll + 512/scale/2;
    	double y = hScroll + 512/scale/2;
    	return new Point2D.Double(x,y);
    }

	public double getScale() {
		return scale;
	}

	public void zoomIn() {
		if(scale < 4){
			double os = scale;
			scale = scale * 2;
			updateScrollBar(os);
			GUIFunctions.refresh();
		}
	}

	public void zoomOut() {
		if(scale > 0.25){
			double os = scale;
			scale = scale / 2;
			updateScrollBar(os);
			GUIFunctions.refresh();
		}
	}
	public int getvScroll() {
		return vScroll;
	}

	public void setvScroll(int vScroll) {
		this.vScroll = vScroll;
		GUIFunctions.refresh();
	}

	public int gethScroll() {
		return hScroll;
	}

	public void sethScroll(int hScroll) {
		this.hScroll = hScroll;
		GUIFunctions.refresh();
	}
	
	private void updateScrollBar(double orgScale){
		int value = (int)(512/scale);
		int orgValue =(int)(512/orgScale);
//		System.out.printf("value %d\n",value);
//		System.out.printf("scale %f\n",scale);
//		System.out.printf("org value %d\n",orgValue);
//		System.out.printf("org scale %f\n",orgScale);
//		System.out.printf("h %d\n",hScroll);
//		System.out.printf("v %d\n",vScroll);
		if(orgValue > value){
//			System.out.println("Zoom in");
			if(orgScale == 0.25 && scale == 0.5){
				GUIFunctions.setHScrollBarKnob(value);
				GUIFunctions.setVScrollBarKnob(value);
				GUIFunctions.setHScrollBarPosit(512);
				GUIFunctions.setVScrollBarPosit(512);
				return;
			}

			GUIFunctions.setHScrollBarPosit(hScroll+(orgValue/4));
			GUIFunctions.setVScrollBarPosit(vScroll+(orgValue/4));

		}
		else{
//			System.out.println("Zoom out");
			if(hScroll-(value/4) < 0){
				GUIFunctions.setHScrollBarPosit(0);
			}
			else if(hScroll-(value/4)+value > 2048){
				GUIFunctions.setHScrollBarPosit(2048-value);
			}
			else{
				GUIFunctions.setHScrollBarPosit(hScroll-(value/4));
			}
			
			if(vScroll-(value/4) < 0){
				GUIFunctions.setVScrollBarPosit(0);
			}
			else if(vScroll-(value/4)+value > 2048){
				GUIFunctions.setVScrollBarPosit(2048-value);
			}
			else{
				GUIFunctions.setVScrollBarPosit(vScroll-(value/4));
			}
		}
		GUIFunctions.setHScrollBarKnob(value);
		GUIFunctions.setVScrollBarKnob(value);
	}
   
}
