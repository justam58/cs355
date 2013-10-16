package cs355.lab1;

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

	public double getScale() {
		return scale;
	}

	public void zoomIn() {
		if(scale > 0.25){
			scale = scale / 2;
		}
		System.out.println(scale);
	}

	public void zoomOut() {
		if(scale < 4){
			scale = scale * 2;
		}
		System.out.println(scale);
	}
	public int getvScroll() {
		return vScroll;
	}

	public void setvScroll(int vScroll) {
		this.vScroll = vScroll;
	}

	public int gethScroll() {
		return hScroll;
	}

	public void sethScroll(int hScroll) {
		this.hScroll = hScroll;
	}
   
}
