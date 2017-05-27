package drawboard.Shape;

import java.awt.*;

public class RectangleShape extends MyShape {

	public RectangleShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		// TODO Auto-generated constructor stub
		
		setShapeType(this.getClass().getName());
	}

}
