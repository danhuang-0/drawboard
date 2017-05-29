package drawPane.Model;

import java.awt.*;

public class LineShape extends MyShape {

	public LineShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		setShapeType("直线");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		
		g.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine(getStartPoint().x, getStartPoint().y, getEndPoint().x, getEndPoint().y);
		
	}
	
	@Override
	public boolean isContainPoint(Point point) {		
		double inAreaDistance = getStrokeWidth() / 2 + 4;
		
		double lineLength = getStartPoint().distance(getEndPoint());
		double distance2start = point.distance(getStartPoint());
		double distance2end = point.distance(getEndPoint());
		
		double maxDistance = Math.max(distance2start, distance2end);
		double minDistance = Math.min(distance2start, distance2end);
		
		if (maxDistance > lineLength && minDistance < inAreaDistance) {
			return true;
		} else if (maxDistance < lineLength) {
			double p = (lineLength + distance2start + distance2end) / 2;
			double area = Math.sqrt(p * (p-lineLength) * (p-distance2start) * (p-distance2end));
			
			double distance = area * 2 / lineLength;
			if (distance < inAreaDistance) {
				return true;
			}
		}
		
		return false;
	}
}
