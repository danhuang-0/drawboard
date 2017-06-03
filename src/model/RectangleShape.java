package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleShape extends MyShape {
	private static final long serialVersionUID = 1643924841896520455L;
	
	private Point topLeft = null;
//	private Point bottomRight = null;
	int width = 0;
	int height = 0;

	public RectangleShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		
		setShapeType("矩形");
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		super.draw(g);
		g.setColor(getStrokeColor());
		
		g.setStroke(new BasicStroke(getStrokeWidth()));
		
		int x1 = getStartPoint().x;
		int x2 = getEndPoint().x;
		int y1 = getStartPoint().y;
		int y2 = getEndPoint().y;
		
		topLeft = new Point(Math.min(x1, x2), Math.min(y1, y2));
		width = Math.abs(x1 - x2);
		height = Math.abs(y1 - y2);
		
		g.drawRect(topLeft.x, topLeft.y, width, height);
	}
	
	@Override
	public boolean isContainPoint(Point point) {
		int borderWidth = getStrokeWidth() / 2 + 4;
		
		Rectangle2D.Double outRect = new Rectangle2D.Double(topLeft.x-borderWidth, topLeft.y-borderWidth, width+2*borderWidth, height+2*borderWidth);
		Rectangle2D.Double inRect = new Rectangle2D.Double(topLeft.x+borderWidth, topLeft.y+borderWidth, width-2*borderWidth, height-2*borderWidth);
		
		return outRect.contains(point) && (!inRect.contains(point));
	}

}
