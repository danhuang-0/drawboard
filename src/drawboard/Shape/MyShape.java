package drawboard.Shape;

import java.awt.*;

public class MyShape  {

	private Point startPoint;
	private Point endPoint;
	
	private Color 	strokeColor;
	private int 	strokeWidth;
	
	private String shapeType;

	public MyShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.strokeColor = strokeColor;
		this.strokeWidth = strokeWidth;
	}
	
	public static MyShape createShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		return new MyShape(startPoint, endPoint, strokeColor, strokeWidth);
	}

	public String getShapeType() {
		return shapeType;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public void draw(Graphics g) { }
	
}
