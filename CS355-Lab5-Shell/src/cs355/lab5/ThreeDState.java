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
	private float cameraX;
	private float cameraY;
	private float cameraZ;
	private float cameraDirection;
    
    public ThreeDState(){
    	on = true;
    	reset();
    }
    
	public void reset() {
		cameraX = 0;
		cameraY = 5;
		cameraZ = 20;
		cameraDirection = 180;
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

	public WireFrame getModel() {
		return model;
	}
}
