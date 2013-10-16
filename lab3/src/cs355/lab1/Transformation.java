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
    
    public Point2D viewToObejctPoint(Point2D p, Shape s){
    	Point2D r = new Point2D.Double();
    	viewToObejct(s).transform(p, r);
		return r;
    }
    
    public Point2D obejctToViewPoint(Point2D p, Shape s){
    	Point2D r = new Point2D.Double();
    	obejctToView(s).transform(p, r);
		return r;
    }
    
    private AffineTransform objectToWorld(Shape s){
    	double tt = s.getRotation();
    	double cx = s.getCenter().getX();
    	double cy = s.getCenter().getY();
    	AffineTransform r = new AffineTransform(cos(tt),sin(tt),-sin(tt),cos(tt),0,0);
    	AffineTransform t = new AffineTransform(1,0,0,1,cx,cy);
//    	System.out.println("r");
//    	System.out.println(r);
//    	System.out.println("t");
//    	System.out.println(t);
    	t.concatenate(r);
//    	System.out.println("result");
//    	System.out.println(t);
    	return t;
    }
    
    private AffineTransform worldToObject(Shape s){
    	double tt = s.getRotation();
    	double cx = s.getCenter().getX();
    	double cy = s.getCenter().getY();
    	AffineTransform t = new AffineTransform(1,0,0,1,-cx,-cy);
    	AffineTransform r = new AffineTransform(cos(tt),-sin(tt),sin(tt),cos(tt),0,0);
    	r.concatenate(t);
		return r;
    }
    
    private AffineTransform viewToWorld(){
    	double f = view.getScale();
    	double px = view.gethScroll();
    	double py = view.getvScroll();
    	double ox = view.getViewOrigin().getX();
    	double oy = view.getViewOrigin().getY();
    	AffineTransform s1 = new AffineTransform(1,0,0,1,-ox,-oy);
    	AffineTransform s2 = new AffineTransform(1/f,0,0,1/f,0,0);
    	AffineTransform s3 = new AffineTransform(1,0,0,1,ox,oy);
    	AffineTransform t = new AffineTransform(1,0,0,1,px,py);
    	s1.concatenate(s2);
    	s1.concatenate(s3);
    	t.concatenate(s1);
		return t;
    }
    
    private AffineTransform worldToView(){
    	double f = view.getScale();
    	double px = view.gethScroll();
    	double py = view.getvScroll();
//    	Point2D wo = new Point2D.Double();
//    	viewToWorld().transform(view.getViewOrigin(), wo);
//    	double ox = wo.getX();
//    	double oy = wo.getY();
//    	AffineTransform t = new AffineTransform(1,0,0,1,-px,-py);
//    	AffineTransform s1 = new AffineTransform(1,0,0,1,-ox,-oy);
//    	AffineTransform s2 = new AffineTransform(f,0,0,f,0,0);
//    	AffineTransform s3 = new AffineTransform(1,0,0,1,ox,oy);
//    	System.out.println("s1");
//    	System.out.println(s1);
//    	System.out.println("s2");
//    	System.out.println(s2);
//    	System.out.println("s3");
//    	System.out.println(s3);
//    	System.out.println("t");
//    	System.out.println(t);
//    	s1.concatenate(s2);
//    	System.out.println("results2");
//    	System.out.println(s1);
//    	s1.concatenate(s3);
//    	System.out.println("results3");
//    	System.out.println(s1);
//    	s1.concatenate(t);
//    	System.out.println("resultt");
//    	System.out.println(s1);
//    	return s1;
    	
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
