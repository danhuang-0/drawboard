package drawboard.Shape;

import java.awt.*;

import drawboard.ConfigInstance;

public class TextShape extends MyShape {

	public TextShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		g.setFont(ConfigInstance.getInstance().getFont());
		g.drawString("你好", 100, 100);
		
	}
}
