package cs355.lab6;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class MyImage {
	// singleton
    private static MyImage instance = new MyImage();

    public static MyImage getInstance() {
        return instance;
    }
    
    private int[][] image; 
    private int height;
    private int width;
    private boolean on;
    
    public MyImage(){
    	on = true;
    }
    
    public MyImage(BufferedImage image,int height,int width,boolean on){
    	load(image);
    	this.height = height;
    	this.width = width;
    	this.on = on;
    }
    
    public MyImage(MyImage myImage) {
        this(myImage.getImage(), myImage.getHeight(), myImage.getWidth(), myImage.isOn());
    }
    
    public BufferedImage getImage(){
    	BufferedImage bimage = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			bimage.getRaster().setSample(j, i, 0, image[i][j]);
    		}
    	}
    	return bimage;
    }
    
    public void load(BufferedImage openImage){
    	WritableRaster data = openImage.getRaster();
    	height = data.getHeight();
    	width = data.getWidth();
    	image = new int [height][width];
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			image[i][j] = cap(data.getSample(j, i, 0));
    		}
    	}
    }
    
	public void doChangeBrightness(int brightnessAmountNum) {
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			image[i][j] = cap(image[i][j] + brightnessAmountNum);
    		}
    	}
	}

	public void doChangeContrast(int contrastAmountNum) {
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			image[i][j] = cap((int)Math.pow((contrastAmountNum+100)/100,4)*(image[i][j]-128)+128);
    		}
    	}
	}

	public void doUniformBlur() {
		// TODO Auto-generated method stub
		
	}

	public void doMedianBlur() {
		// TODO Auto-generated method stub
		
	}

	public void doSharpen() {
		// TODO Auto-generated method stub
		
	}

	public void doEdgeDetection() {
		// TODO Auto-generated method stub
		
	}
    
	public boolean isOn() {
		return on && (image != null);
	}

	public void toggle() {
		on = !on;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	private int cap(int v){
		if(v > 255){
			return 255;
		}
		if(v < 0){
			return 0;
		}
		return v;
	}
	
}
