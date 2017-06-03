package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class OvalShape extends MyShape {
	private static final long serialVersionUID = -6231462219370327352L;
	
	private int x;
	private int y;
	private int w;
	private int h;

	public OvalShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		setShapeType("圆形");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		

		g.setColor(getStrokeColor());
		
		g.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		x = Math.min(getStartPoint().x, getEndPoint().x);
		y = Math.min(getStartPoint().y, getEndPoint().y);
		w = Math.abs(getStartPoint().x - getEndPoint().x);
		h = Math.abs(getStartPoint().y - getEndPoint().y);
		g.drawOval(x, y, w, h);
	}
	
	@Override
	public boolean isContainPoint(Point point) {
		int lineWidth = getStrokeWidth() / 2 + 4;
		Ellipse2D.Double outEllipse = new Ellipse2D.Double(x-lineWidth, y-lineWidth, w+2*lineWidth, h+2*lineWidth);
		Ellipse2D.Double inEllipse = new Ellipse2D.Double(x+lineWidth, y+lineWidth, w-2*lineWidth, h-2*lineWidth);
		
		return outEllipse.contains(point) && (!inEllipse.contains(point));
	}
}
