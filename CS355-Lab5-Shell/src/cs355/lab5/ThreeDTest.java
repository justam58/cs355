package cs355.lab5;

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
    

	public Point2D test(Point3D p) {
		Vector v = new Vector(p);
		Vector v1 = getWorldToCameraTranslationMatrix().mutiplyByVector(v);
		Vector v2 = getWorldToCameraRotationMatrix().mutiplyByVector(v1);
		Vector v3 = getClipMatrix().mutiplyByVector(v2);
		Vector v4 = v3.normalize();
		Vector v5 = v4.clip();
		System.out.print("translate  ");
		System.out.println(v1.toString());
		System.out.print("rotate  ");
		System.out.println(v2.toString());
		System.out.print("clip  ");
		System.out.println(v3.toString());
		System.out.print("normalize  ");
		System.out.println(v4.toString());
		if(v5 == null){
			return null;
		}
		System.out.print("test  ");
		System.out.println(v5.toString());
		double[] result = v.getV();
		double x = (result[0]*256)+(256);
		double y = (-result[1]*256)+(256);
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
		double theta = state.getCameraDirection();
		double[][] m = new double[][]{
			{Math.cos(theta),0,Math.sin(theta),0},
			{0,1,0,0},
			{-Math.sin(theta),0,Math.cos(theta),0},
			{0,0,0,1}
		};
		return new Matrix(m);
	}
	
	private Matrix getClipMatrix(){
		double zoom = Math.pow(3, 0.5);
		double f = 1000;
		double n = 1;
		double[][] m = new double[][]{
			{zoom,0,0,0},
			{0,zoom,0,0},
			{0,0,(f+n)/(f-n),(-2*n*f)/(f-n)},
			{0,0,1,0}
		};
		return new Matrix(m);
	}
	
	public class Matrix{

		double[][] m;
		
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
		
		double[] v = new double[4];
		
		public Vector(Point3D p){
			v[0] = p.x;
			v[1] = p.y;
			v[2] = p.z;
			v[3] = 1;
		}
		
		public Vector(double[] v){
			this.v = v;
		}
		
		public Vector clip(){
			if(Math.abs(v[0]) >= 1 || 
			   Math.abs(v[1]) >= 1 ||
			   Math.abs(v[2]) >= 1){
				return null;
			}
			return this;
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

		@Override
		public String toString() {
			return "Vector [v=" + Arrays.toString(v) + "]";
		}
		
	}

}
