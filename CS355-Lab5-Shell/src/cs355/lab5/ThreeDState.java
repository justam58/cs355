package cs355.lab5;

import cs355.HouseModel;
import cs355.WireFrame;

public class ThreeDState {
	
	// singleton
    private static ThreeDState instance = new ThreeDState();

    public static ThreeDState getInstance() {
        return instance;
    }
    
    private boolean on;
    private WireFrame model = new HouseModel();
    
	//camera
	private float cameraX = 0;
	private float cameraY = -5;
	private float cameraZ = -20;
	private float cameraDirection = 0;
	private float aspect = 640 / 480;
    
    public ThreeDState(){
    	on = false;
    	reset();
    }
    
	public void reset() {
		cameraX = 0;
		cameraY = -5;
		cameraZ = 100;
		cameraDirection = 0;
	}

	public void toggle() {
		on = !on;
	}

	public boolean isOn() {
		return on;
	}

	public float getCameraX() {
		return cameraX;
	}

	public void setCameraX(float cameraX) {
		this.cameraX = cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}

	public void setCameraY(float cameraY) {
		this.cameraY = cameraY;
	}

	public float getCameraZ() {
		return cameraZ;
	}

	public void setCameraZ(float cameraZ) {
		this.cameraZ = cameraZ;
	}

	public float getCameraDirection() {
		return cameraDirection;
	}

	public void setCameraDirection(float cameraDirection) {
		this.cameraDirection = cameraDirection;
	}

	public float getAspect() {
		return aspect;
	}

	public void setAspect(float aspect) {
		this.aspect = aspect;
	}

	public WireFrame getModel() {
		return model;
	}
}
