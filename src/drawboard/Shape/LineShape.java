package drawboard.Shape;

import java.awt.*;

public class LineShape extends MyShape {

	public LineShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		setShapeType(this.getClass().getName());
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
		g.setColor(getStrokeColor());
		g.drawLine(getStartPoint().x, getStartPoint().y, getEndPoint().x, getEndPoint().y);
	}
}
