package drawPane.Model;

/**
 * 暂时没用上
 */

import java.awt.*;
import java.util.ArrayList;

public class CurveShape extends MyShape {
	
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

}
