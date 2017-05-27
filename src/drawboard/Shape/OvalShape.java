package drawboard.Shape;

import java.awt.*;

public class OvalShape extends MyShape {

	public OvalShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		// TODO Auto-generated constructor stub
		setShapeType(this.getClass().getName());
	}

}
