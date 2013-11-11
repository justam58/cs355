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
		Vector v2 = getWorldToCameraRotationMatrix().mutiplyByVector(v);
		System.out.println(v1.toString());
		System.out.println(v2.toString());
		return null;
	}
	
	public Matrix getWorldToCameraTranslationMatrix(){
		double[][] m = new double[][]{
			{1,0,0,-state.getCameraX()},
			{0,1,0,-state.getCameraY()},
			{0,0,1,-state.getCameraZ()},
			{0,0,0,1}
		};
		return new Matrix(m);
	}
	
	public Matrix getWorldToCameraRotationMatrix(){
		double[][] m = new double[][]{
			{1,0,0,0},
			{0,1,0,0},
			{0,0,1,0},
			{0,0,0,1}
		};
		return new Matrix(m);
	}
	
	public class Matrix{

		double[][] m;
		
		public Matrix(double[][] m){
			this.m = m;
		}
		
		public Matrix mutiplyByMatrix(Matrix m2){
			double[][] result = new double[4][4];
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					for (int k = 0; k < 4; k++)
						result[i][j] += m[i][k] * m2.getM()[k][j];
			return new Matrix(result); 
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

		public void setM(double[][] m) {
			this.m = m;
		}

		@Override
		public String toString() {
			return "Matrix [m=" + Arrays.toString(m) + "]";
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

		public double[] getV() {
			return v;
		}

		public void setV(double[] v) {
			this.v = v;
		}

		@Override
		public String toString() {
			return "Vector [v=" + Arrays.toString(v) + "]";
		}
		
	}

}
