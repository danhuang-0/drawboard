package drawboard.Shape;

import java.awt.*;

public class CurveShape extends MyShape {

	public CurveShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		setShapeType(this.getClass().getName());
	}

}
