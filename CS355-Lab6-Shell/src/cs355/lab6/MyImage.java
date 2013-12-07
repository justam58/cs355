package cs355.lab6;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;

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
    
	public void doChangeBrightness(int b) {
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			image[i][j] = cap(image[i][j] + b);
    		}
    	}
	}

	public void doChangeContrast(int c) {
		double factor = Math.pow((c+100)/100.0,4);
    	for (int i = 0; i < height; i++){
    		for (int j = 0; j < width; j++){
    			int temp = (int) ((factor*(image[i][j])-128)+128);
    			image[i][j] = cap(temp);
    		}
    	}
	}

	public void doUniformBlur() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			image[i][j] = copy[i-1][j-1]+copy[i-1][j]+copy[i-1][j+1];
    			image[i][j] += copy[i][j-1]+copy[i][j]+copy[i][j+1];
    			image[i][j] += copy[i+1][j-1]+copy[i+1][j]+copy[i+1][j+1];
    			image[i][j] /= 9;
    		}
    	}
	}

	public void doMedianBlur() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			ArrayList<Integer> ns = new ArrayList<Integer>();
    			ns.add(copy[i-1][j-1]);
    			ns.add(copy[i-1][j]);
    			ns.add(copy[i-1][j+1]);
    			ns.add(copy[i][j-1]);
    			ns.add(copy[i][j]);
    			ns.add(copy[i][j+1]);
    			ns.add(copy[i+1][j-1]);
    			ns.add(copy[i+1][j]);
    			ns.add(copy[i+1][j+1]);
    			Collections.sort(ns);
    			image[i][j]=ns.get(4);
    		}
    	}
	}

	public void doSharpen() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
     			double um = -copy[i-1][j]*0.5;
     			double ml = -copy[i][j-1]*0.5;
    			int mm = copy[i][j]*3;
    			double dl = -copy[i][j-1]*0.5;
    			double dm = -copy[i+1][j]*0.5;
    			image[i][j]=cap((int)(um+ml+mm+dl+dm));
    		}
    	}
	}

	public void doEdgeDetection() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
		for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			double xa = -copy[i-1][j-1]+copy[i-1][j+1];
    			double xb = -2*copy[i][j-1]+(2*copy[i][j+1]);
        		double xc = -copy[i+1][j-1]+copy[i+1][j+1];
    			double x = (xa+xb+xc)/8;
    			
    			double ya = -copy[i-1][j-1]+(-2*copy[i-1][j])-copy[i-1][j+1];
    			double yb = copy[i+1][j-1]+(2*copy[i+1][j])+copy[i+1][j+1];
    			double y = (ya+yb)/8;
    			image[i][j]=cap((int) Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
    		}
    	}
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
	
    public int[][] getRawImage(){
    	return image;
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
