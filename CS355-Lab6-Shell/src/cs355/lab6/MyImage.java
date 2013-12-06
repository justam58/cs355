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
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
		int[][] k = {{1,1,1},{1,1,1},{1,1,1}};
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			int ul = copy[i-1][j-1]*k[0][0];
    			int um = copy[i-1][j]*k[0][1];
    			int ur = copy[i-1][j+1]*k[0][2];
    			int ml = copy[i][j-1]*k[1][0];
    			int mm = copy[i][j]*k[1][1];
    			int mr = copy[i][j+1]*k[1][2];
    			int dl = copy[i+1][j-1]*k[2][0];
    			int dm = copy[i+1][j]*k[2][1];
    			int dr = copy[i+1][j+1]*k[2][2];
    			image[i][j]=(ul+um+ur+ml+mm+mr+dl+dm+dr)/9;
    		}
    	}
	}

	public void doMedianBlur() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
		int[][] k = {{1,1,1},{1,1,1},{1,1,1}};
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			ArrayList<Integer> ns = new ArrayList<Integer>();
    			ns.add(copy[i-1][j-1]*k[0][0]);
    			ns.add(copy[i-1][j]*k[0][1]);
    			ns.add(copy[i-1][j+1]*k[0][2]);
    			ns.add(copy[i][j-1]*k[1][0]);
    			ns.add(copy[i][j]*k[1][1]);
    			ns.add(copy[i][j+1]*k[1][2]);
    			ns.add(copy[i+1][j-1]*k[2][0]);
    			ns.add(copy[i+1][j]*k[2][1]);
    			ns.add(copy[i+1][j+1]*k[2][2]);
    			Collections.sort(ns);
    			image[i][j]=ns.get(4);
    		}
    	}
	}

	public void doSharpen() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
		int[][] k = {{0,-1/2,0},{-1/2,3,-1/2},{0,-1/2,0}};
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			int ul = copy[i-1][j-1]*k[0][0];
    			int um = copy[i-1][j]*k[0][1];
    			int ur = copy[i-1][j+1]*k[0][2];
    			int ml = copy[i][j-1]*k[1][0];
    			int mm = copy[i][j]*k[1][1];
    			int mr = copy[i][j+1]*k[1][2];
    			int dl = copy[i+1][j-1]*k[2][0];
    			int dm = copy[i+1][j]*k[2][1];
    			int dr = copy[i+1][j+1]*k[2][2];
    			image[i][j]=ul+um+ur+ml+mm+mr+dl+dm+dr;
    		}
    	}
	}

	public void doEdgeDetection() {
		MyImage copyImage = new MyImage(this);
		int[][] copy = copyImage.getRawImage();
		int[][] dx = {{-1,0,1},{-2,0,2},{-1,0,1}};
		int[][] dy = {{-1,-2,-1},{0,0,0},{-1,-2,-1}};
    	for (int i = 1; i < height-1; i++){
    		for (int j = 1; j < width-1; j++){
    			int ulx = copy[i-1][j-1]*dx[0][0];
    			int umx = copy[i-1][j]*dx[0][1];
    			int urx = copy[i-1][j+1]*dx[0][2];
    			int mlx = copy[i][j-1]*dx[1][0];
    			int mmx = copy[i][j]*dx[1][1];
    			int mrx = copy[i][j+1]*dx[1][2];
    			int dlx = copy[i+1][j-1]*dx[2][0];
    			int dmx = copy[i+1][j]*dx[2][1];
    			int drx = copy[i+1][j+1]*dx[2][2];
    			int x = ulx+umx+urx+mlx+mmx+mrx+dlx+dmx+drx;
    			
    			int uly = copy[i-1][j-1]*dy[0][0];
    			int umy = copy[i-1][j]*dy[0][1];
    			int ury = copy[i-1][j+1]*dy[0][2];
    			int mly = copy[i][j-1]*dy[1][0];
    			int mmy = copy[i][j]*dy[1][1];
    			int mry = copy[i][j+1]*dy[1][2];
    			int dly = copy[i+1][j-1]*dy[2][0];
    			int dmy = copy[i+1][j]*dy[2][1];
    			int dry = copy[i+1][j+1]*dy[2][2];
    			int y = uly+umy+ury+mly+mmy+mry+dly+dmy+dry;
    			image[i][j]=(int) Math.sqrt((Math.pow(x, 2)/8)+(Math.pow(y, 2)/8));
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
