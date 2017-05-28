package drawboard;

import java.awt.Color;
import java.awt.Font;

/*
 * 单例，用于存储 画图相关的设置
 */

public class ConfigInstance {
	
	private static ConfigInstance instance = null;
	
	private String ShapeType = null;
	private Color ShapeColor = null;
	private int StrokeWidth = 20;
	private Font font = null;
	
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
		System.out.println(this.toString() + shapeType);
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public Font getFont() {
		return font;
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
