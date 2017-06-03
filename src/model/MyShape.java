package model;

import java.awt.*;
import java.io.Serializable;

public class MyShape implements Serializable  {
	private static final long serialVersionUID = -3766997127384112770L;
	
	private Point startPoint;
	private Point endPoint;
	private Point offsetPoint = new Point(0, 0);
	
	private Color 	strokeColor;
	private int 	strokeWidth;
	
	private String shapeType;
	
	private Font 	textFont;
	private String textContent = "";

	public MyShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.strokeColor = strokeColor;
		this.strokeWidth = strokeWidth;
	}
	
	public boolean isContainPoint(Point point) {
		return false;
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
		if (strokeWidth < 1) {
			strokeWidth = 1;
		}
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	
	public Point getOffsetPoint() {
		return offsetPoint;
	}
	
	public void setOffsetPoint(Point offsetPoint) {
		this.offsetPoint = offsetPoint;
		startPoint.x += offsetPoint.x;
		startPoint.y += offsetPoint.y;
		endPoint.x += offsetPoint.x;
		endPoint.y += offsetPoint.y;
	}
	
	public void setTextFont(Font textFont) {
		this.textFont = textFont;
	}
	public Font getTextFont() {
		return textFont;
	}
	
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getTextContent() {
		return textContent;
	}

	public void draw(Graphics2D g) {}
	
}
