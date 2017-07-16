package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TextShape extends MyShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6719906404804354309L;
	
	private Rectangle2D stringBounds;

	public TextShape(Point startPoint, Point endPoint, Color strokeColor, int strokeWidth) {
		super(startPoint, endPoint, strokeColor, strokeWidth);
		
		setShapeType("文字");
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		int length = getWordCount(getTextContent());
		double Long = this.getEndPoint().x-this.getStartPoint().x;
		double Width = this.getEndPoint().y-this.getStartPoint().y;
		double size = Math.max(Math.abs(Long)/length,Math.abs(Width)/2);
		
		g.setColor(getStrokeColor());
		g.setFont(new Font("Default", Font.PLAIN, (int)(size*2)));
		g.drawString(getTextContent(), getStartPoint().x, getStartPoint().y);
		
		int width = g.getFontMetrics().stringWidth(getTextContent());
		int height = g.getFontMetrics().getHeight();
		int startX = getStartPoint().x;
		int startY = getStartPoint().y - height;
		stringBounds = new Rectangle2D.Double(startX, startY, width, height);
	}
	
	@Override
	public boolean isContainPoint(Point point) {
		boolean isContain = stringBounds.contains(point);
		return isContain;
	}
	
	@Override
	public void setOffsetPoint(Point offsetPoint) {
		super.setOffsetPoint(offsetPoint);
	}
	
	//计算字符串的字节数soul
	public int getWordCount(String s)
    {
        int length = 0;
        for(int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)
                length++;
            else
                length += 2;
                
        }
        return length;
        
    }
}
