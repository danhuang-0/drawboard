package drawPane.Model;

import java.awt.*;

import javax.swing.JOptionPane;

import handler.ConfigInstance;

public class TextShape extends MyShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6719906404804354309L;
	
	private String text = null;

	public TextShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		
		setShapeType("文字");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.setColor(getStrokeColor());
		g.setFont(ConfigInstance.getInstance().getFont());
		
		showInputPane();
//		if (text == null) {
//			showInputPane();
//			ConfigInstance.getInstance().getCanvas().repaint();
//		}
//		
//		if (text != null) {
//			g.drawString(text, getStartPoint().x, getStartPoint().y);
//		}
	}
	
	private void showInputPane() {
		String title = "请输入文字";
		int messageType = JOptionPane.QUESTION_MESSAGE;
		
		String result = JOptionPane.showInputDialog(ConfigInstance.getInstance().getCanvas(), "", title, messageType);
		text = result;
	}
	
	
}
