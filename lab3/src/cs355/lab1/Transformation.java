package cs355.lab1;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.models.Shape;

public class Transformation {
	
	// singleton
    private static Transformation instance = new Transformation();

    public static Transformation getInstance() {
        return instance;
    }
    
    private ViewManager view = ViewManager.getInstance();
    
    public AffineTransform viewToObejct(Shape s){
    	AffineTransform a = worldToObject(s);
    	a.concatenate(viewToWorld());
    	return a;
    }
    
    public AffineTransform obejctToView(Shape s){
    	AffineTransform a = worldToView();
    	a.concatenate(objectToWorld(s));
    	return a;
    }
    
    public AffineTransform objectToWorld(Shape s){
    	double tt = s.getRotation();
    	double ox = s.getOrigin().getX();
    	double oy = s.getOrigin().getY();
    	double cx = s.getCenter().getX();
    	double cy = s.getCenter().getY();
    	AffineTransform r1 = new AffineTransform(1,0,0,1,-cx,-cy);
    	AffineTransform r = new AffineTransform(cos(tt),sin(tt),-sin(tt),cos(tt),0,0);
    	AffineTransform r2 = new AffineTransform(1,0,0,1,cx,cy);
    	AffineTransform t = new AffineTransform(1,0,0,1,ox,oy);
    	t.concatenate(r2);
    	t.concatenate(r);
    	t.concatenate(r1);
    	return t;
    }
    
    public AffineTransform worldToObject(Shape s){
    	double tt = s.getRotation();
    	double ox = s.getOrigin().getX();
    	double oy = s.getOrigin().getY();
    	double cx = s.getCenter().getX();
    	double cy = s.getCenter().getY();
    	AffineTransform t = new AffineTransform(1,0,0,1,-ox,-oy);
    	AffineTransform r1 = new AffineTransform(1,0,0,1,-cx,-cy);
    	AffineTransform r = new AffineTransform(cos(tt),-sin(tt),sin(tt),cos(tt),0,0);
    	AffineTransform r2 = new AffineTransform(1,0,0,1,cx,cy);
    	r2.concatenate(r);
    	r2.concatenate(r1);
    	r2.concatenate(t);
		return r2;
    }
    
    public AffineTransform viewToWorld(){
    	double f = view.getScale();
    	double px = view.gethScroll();
    	double py = view.getvScroll();
    	AffineTransform s = new AffineTransform(1/f,0,0,1/f,0,0);
    	AffineTransform t = new AffineTransform(1,0,0,1,px,py);
    	t.concatenate(s);
		return t;
    }
    
    public AffineTransform worldToView(){
    	double f = view.getScale();
    	double px = view.gethScroll();
    	double py = view.getvScroll();
    	AffineTransform t = new AffineTransform(1,0,0,1,-px,-py);
    	AffineTransform s = new AffineTransform(f,0,0,f,0,0);
    	s.concatenate(t);
		return s;
    }
    
    private double cos(double theta){
    	return Math.cos(theta);
    }
    
    private double sin(double theta){
    	return Math.sin(theta);
    }

}
