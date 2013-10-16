package cs355.models;

import java.awt.Color;

public class Square extends Shape {

	private double size;
	
	public Square(Color color) {
		super(color);
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}
