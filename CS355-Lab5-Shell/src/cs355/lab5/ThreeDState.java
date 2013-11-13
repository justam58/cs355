package cs355.lab5;

public class ThreeDState {
	
	// singleton
    private static ThreeDState instance = new ThreeDState();

    public static ThreeDState getInstance() {
        return instance;
    }
    
    //mode
    private boolean on;
    
	//camera
	private double cameraX;
	private double cameraY;
	private double cameraZ;
	private int cameraDirection;
    
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

	public double getCameraX() {
		return cameraX;
	}

	public void setCameraX(double cameraX) {
		this.cameraX = cameraX;
	}

	public double getCameraY() {
		return cameraY;
	}

	public void setCameraY(double cameraY) {
		this.cameraY = cameraY;
	}

	public double getCameraZ() {
		return cameraZ;
	}

	public void setCameraZ(double cameraZ) {
		this.cameraZ = cameraZ;
	}

	public int getCameraDirection() {
		return cameraDirection;
	}

	public void setCameraDirection(int cameraDirection) {
		this.cameraDirection = cameraDirection;
	}

}
