package drawboard;

import java.awt.Color;

/*
 * 单例，用于存储 画图相关的设置
 */

public class ConfigInstance {
	
	private static ConfigInstance instance = null;
	
	private String ShapeType = null;
	private String TextFont = null;
	private String TextFontSize = null;
	private Color ShapeColor = null;
	private int StrokeWidth = 1;
	
	public static ConfigInstance getInstance() {
		if (instance == null) {
			instance = new ConfigInstance();
		}
		return instance;
	}

	public String getShapeType() {
		return ShapeType;
	}

	public void setShapeType(String shapeType) {
		ShapeType = shapeType;
//		System.out.println(this.toString() + shapeType);
	}

	public String getTextFont() {
		return TextFont;
	}

	public void setTextFont(String textFont) {
//		System.out.println(this.toString() + textFont);
		TextFont = textFont;
	}

	public String getTextFontSize() {
		return TextFontSize;
	}

	public void setTextFontSize(String textFontSize) {
//		System.out.println(this.toString() + textFontSize);
		TextFontSize = textFontSize;
	}

	public Color getShapeColor() {
		return ShapeColor;
	}

	public void setShapeColor(Color c) {
		ShapeColor = c;
	}

	public int getStrokeWidth() {
		return StrokeWidth;
	}

	public void setStrokeWidth(int StrokeWidth) {
		this.StrokeWidth = StrokeWidth;
	}

	
}
