package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TextShape extends MyShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6719906404804354309L;
	
	private Rectangle2D stringBounds;

	public TextShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		
		setShapeType("文字");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		g.setFont(getTextFont());
		g.drawString(getTextContent(), getStartPoint().x, getStartPoint().y);
		
		int width = g.getFontMetrics().stringWidth(getTextContent());
		int height = g.getFontMetrics().getHeight();
		int startX = getStartPoint().x;
		int startY = getStartPoint().y - height;
		stringBounds = new Rectangle2D.Double(startX, startY, width, height);
	}
	
	@Override
	public boolean isContainPoint(Point point) {
		boolean isContain = stringBounds.contains(point);
		System.out.println(stringBounds.toString());
		System.out.println(point.toString());
		
		System.out.println(isContain);
		
		return isContain;
	}
	
	@Override
	public void setOffsetPoint(Point offsetPoint) {
		super.setOffsetPoint(offsetPoint);
		System.out.println(offsetPoint.toString());
	}
	
}
