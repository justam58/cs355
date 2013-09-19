package cs355.models;

import java.awt.Color;
import java.awt.Point;

public abstract class Shape {

	private Color color;
	private double rotation;
	
	public Shape(){}
	
	public Shape(Color color){
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public abstract boolean contains(Point p);
	
	public abstract Point getCenter();
	
}
