package cs355.lab5;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

import cs355.Point3D;

public class ThreeDTest {
	
	// singleton
    private static ThreeDTest instance = new ThreeDTest();

    public static ThreeDTest getInstance() {
        return instance;
    }
    
    private ThreeDState state = ThreeDState.getInstance();
    
    private double zoom = Math.pow(3,0.5);
    private double f = 1000;
    private double n = 1;
    private double size = 2048;
    

	public Line2D test(Point3D p1, Point3D p2) {
		Vector v11 = testOnePoint(p1);
		Vector v22 = testOnePoint(p2);
		double[] v1 = v11.getV();
		double[] v2 = v22.getV();
		if(v1[2] < -1 || v2[2] < -1){
			return null;
		}
		if(v1[2] > 1 || v2[2] > 1){
			return null;
		}
		if((v1[0] < -1 && v2[0] < -1) ||
		   (v1[1] < -1 && v2[1] < -1) ||
		   (v1[0] > 1 && v2[0] > 1) ||
		   (v1[1] > 1 && v2[1] > 1)){
			return null;
		}
		return new Line2D.Double(map(v11),map(v22));
	}
	
	private Vector testOnePoint(Point3D p){
		Vector v = new Vector(p);
		Vector v1 = getWorldToCameraTranslationMatrix().mutiplyByVector(v);
		Vector v2 = getWorldToCameraRotationMatrix().mutiplyByVector(v1);
		Vector v3 = getClipMatrix().mutiplyByVector(v2);
		Vector v4 = v3.normalize();
		return v4;
	}
	
	private Point2D map(Vector v){
		double[] result = v.getV();
		double x = (result[0]+1)*size/2;
		double y = (-result[1]+1)*size/2;
		return new Point2D.Double(x,y);
	}
	
	private Matrix getWorldToCameraTranslationMatrix(){
		double[][] m = new double[][]{
			{1,0,0,-state.getCameraX()},
			{0,1,0,-state.getCameraY()},
			{0,0,1,-state.getCameraZ()},
			{0,0,0,1}
		};
		return new Matrix(m);
	}
	
	private Matrix getWorldToCameraRotationMatrix(){
		double theta = Math.toRadians(state.getCameraDirection());
		double[][] m = new double[][]{
			{Math.cos(theta),0,Math.sin(theta),0},
			{0,1,0,0},
			{-Math.sin(theta),0,Math.cos(theta),0},
			{0,0,0,1}
		};
		return new Matrix(m);
	}
	
	private Matrix getClipMatrix(){
		double[][] m = new double[][]{
			{zoom,0,0,0},
			{0,zoom,0,0},
			{0,0,(f+n)/(f-n),-2*n*f/(f-n)},
			{0,0,1,0}
		};
		return new Matrix(m);
	}
	
	public class Matrix{

		private double[][] m;
		
		public Matrix(double[][] m){
			this.m = m;
		}
		
		public Vector mutiplyByVector(Vector v){
			double[] result = new double[4];
			for (int i = 0; i < 4; i++){
				for (int j = 0; j < 4; j++){
					result[i] += m[i][j] * v.getV()[j];
				}
			}
			return new Vector(result); 
		}

		public double[][] getM() {
			return m;
		}
		
	}
	
	public class Vector{
		
		private double[] v = new double[4];
		
		public Vector(Point3D p){
			v[0] = p.x;
			v[1] = p.y;
			v[2] = p.z;
			v[3] = 1;
		}
		
		public Vector(double[] v){
			this.v = v;
		}
		
		public Vector normalize(){
			double[] result = new double[4];
			result[0] = v[0] / v[3];
			result[1] = v[1] / v[3];
			result[2] = v[2] / v[3];
			result[3] = v[3] / v[3];
			return new Vector(result);
		}

		public double[] getV() {
			return v;
		}

	}

}
