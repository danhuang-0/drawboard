package drawPane.Model;

/**
 * 暂时没用上
 */

import java.awt.*;
import java.util.ArrayList;

public class CurveShape extends MyShape {
	private static final long serialVersionUID = 8296374809914130628L;
	
	ArrayList<Point> allPoints = new ArrayList<Point>();
	Point lastPoint = null;

	public CurveShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		setShapeType("曲线");
	}
	
	@Override
	public void setEndPoint(Point endPoint) {
		super.setEndPoint(endPoint);
		this.allPoints.add(endPoint);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		
		g.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		lastPoint = allPoints.get(0);
		for (Point point : allPoints) {
			g.drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
			lastPoint = point;
		}
	}
	
	@Override
	public boolean isContainPoint(Point point) {
		double distance = getStrokeWidth() / 2 + 4;
		for (Point eachPoint : allPoints) {
			if (point.distance(eachPoint) < distance) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setOffsetPoint(Point offsetPoint) {
		for (Point point : allPoints) {
			point.x += offsetPoint.x;
			point.y += offsetPoint.y;
		}
	}

}
