package handler;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import drawPane.Controller.Canvas;

/**
 * 单例模式，存储图形相关属性
 * @author Qi
 *
 */

public class ConfigInstance {
	
	private static ConfigInstance instance = null;
	
	private String ShapeType = null;
	private Color ShapeColor = null;
	private int StrokeWidth = 20;
	private Font font = null;
	
	private Canvas canvas = null;
	
	private String drawContent = null;

	private HashMap<String, CallBackHandlerListener> callBacks = new HashMap<String, CallBackHandlerListener>();
	
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
		sendConfigMsg();
	}

	public void setFont(Font font) {
		this.font = font;
		sendConfigMsg();
	}
	
	public Font getFont() {
		return font;
	}

	public Color getShapeColor() {
		return ShapeColor;
	}

	public void setShapeColor(Color c) {
		ShapeColor = c;
		if (canvas != null) {
			canvas.ColorChanged();
		}
		sendConfigMsg();
	}

	public int getStrokeWidth() {
		return StrokeWidth;
	}

	public void setStrokeWidth(int StrokeWidth) {
		if (StrokeWidth < 2) {
			StrokeWidth = 1;
		}
		this.StrokeWidth = StrokeWidth;
		sendConfigMsg();
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	private void sendConfigMsg() {
		TipPaneInstance tipPane = TipPaneInstance.getInstance();
		
		StringBuffer strBuf = new StringBuffer();
		if (ShapeType != null) {
			strBuf.append("画笔: ")
				.append(ShapeType)
				.append(";    ");
		}
		if (ShapeColor != null) {
			strBuf.append("颜色: ")
				.append(String.format("(R:%d,G%d,B%d)", ShapeColor.getRed(), ShapeColor.getGreen(), ShapeColor.getBlue()))
				.append(";    ");
		}

		strBuf.append("画笔粗细: ")
			.append(StrokeWidth)
			.append(";    ");
		tipPane.setConfigMsg(strBuf.toString());
	}
	
	public void setDrawContent(String drawContent) {
		this.drawContent = drawContent;
	}
	
	public String getDrawContent() {
		return drawContent;
	}
	
	public void addCallBack(String className, CallBackHandlerListener handler) {
		this.callBacks.put(className, handler);
	}
	
	public CallBackHandlerListener getCallBack(String className) {
		return this.callBacks.get(className);
	}

}
