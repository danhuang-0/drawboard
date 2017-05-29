package drawPane.Model;

import java.awt.*;

import handler.ConfigInstance;

public class TextShape extends MyShape {

	public TextShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		
		setShapeType("文字");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		g.setFont(ConfigInstance.getInstance().getFont());
		g.drawString("你好", 100, 100);
		
	}
}
