package cs355.lab1;

import java.awt.geom.Point2D;

import cs355.GUIFunctions;

public class ViewManager {
	
	// singleton
    private static ViewManager instance = new ViewManager();

    public static ViewManager getInstance() {
        return instance;
    }
    
    private double scale;
    private int vScroll;
    private int hScroll;
    
    public ViewManager(){
    	this.scale = 1;
    	this.vScroll = 0;
    	this.vScroll = 0;
    }
    
    public Point2D getViewOrigin(){
    	double x = vScroll + 256;
    	double y = hScroll + 256;
    	return new Point2D.Double(x,y);
    }

	public double getScale() {
		return scale;
	}

	public void zoomIn() {
		if(scale > 0.25){
			scale = scale / 2;
		}
		System.out.println("scale");
		System.out.println(scale);
	}

	public void zoomOut() {
		if(scale < 4){
			scale = scale * 2;
		}
		System.out.println("scale");
		System.out.println(scale);
	}
	public int getvScroll() {
		return vScroll;
	}

	public void setvScroll(int vScroll) {
		this.vScroll = vScroll;
		System.out.println(vScroll);
	}

	public int gethScroll() {
		return hScroll;
	}

	public void sethScroll(int hScroll) {
		this.hScroll = hScroll;
		System.out.println(hScroll);
	}
   
}
