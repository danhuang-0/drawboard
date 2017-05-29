package drawboard.DrawPane;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import drawboard.*;
import drawboard.Shape.*;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 6517769642159055885L;
	
	// 所有形状的数组
	private ArrayList<MyShape> allShapes = new ArrayList<MyShape>();
	private HashMap<String, Class<? extends MyShape>> shapeTypes = new HashMap<String, Class<? extends MyShape>>();
	private MyShape newShape;
	private MyShape selectedShape;
	
	private Point startPoint = new Point(0, 0);
	private Point endPoint = new Point(0, 0);
	
	// 处理事件
	PaintHandler handler = new PaintHandler(this);
	
	private boolean isDrawing = false;

	public Canvas() {
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		this.addMouseWheelListener(handler);
		Toolkit.getDefaultToolkit().addAWTEventListener(handler, AWTEvent.KEY_EVENT_MASK);
		
		shapeTypes.put("直线", LineShape.class);
		shapeTypes.put("曲线", CurveShape.class);
		shapeTypes.put("圆形", OvalShape.class);
		shapeTypes.put("矩形", RectangleShape.class);
		shapeTypes.put("文字", TextShape.class);
		
		ConfigInstance.getInstance().setCanvas(this);
		sendShapeMsg();
	}
	
	public void mousePressed(Point point) {
		startPoint = point;		
		ConfigInstance instance = ConfigInstance.getInstance();
		Color shapeColor = instance.getShapeColor();
		int strokeWidth = instance.getStrokeWidth();
		
		// 获取按钮按下的图形形状类型
		String shapeType = instance.getShapeType();
		Class<? extends MyShape> shapeClass = this.shapeTypes.get(shapeType);
		
		isDrawing = shapeClass == null ? false : true;
		
		// 鼠标点击开始
		if (isDrawing) {
			// 处于绘图工具选项下的操作
			try {
				Constructor<? extends MyShape> constructor = shapeClass.getConstructor(new Class[]{Point.class, Point.class, Color.class, int.class});
				newShape = (MyShape) constructor.newInstance(new Object[]{startPoint, endPoint, shapeColor, strokeWidth});
				
				if (newShape != null) {
					allShapes.add(newShape);
					selectedShape = newShape;
				}
			} catch (Exception e) {
				System.out.println("----------------------------");
				e.printStackTrace();
				System.out.println("----------------------------");
			}
		} else {
			for (int i = allShapes.size(); i > 0; i--) {
				MyShape myShape = allShapes.get(i-1);
				if (myShape.isContainPoint(point)) {
					selectedShape = myShape;
					break;
				}
				selectedShape = null;
			}
		}
		sendShapeMsg();
	}
	
	// 鼠标拖动
	public void mouseDragged(Point point) {
		if (isDrawing) {
			endPoint = point;
			if (newShape != null) {
				newShape.setEndPoint(endPoint);
				repaint();
			}
		} else {
			if (selectedShape != null) {
				Point p = new Point(point.x - startPoint.x, point.y - startPoint.y);
				selectedShape.setOffsetPoint(p);
				repaint();
				startPoint = point;
			}
		}
		sendShapeMsg();
	}
	
	// 鼠标松开
	public void mouseReleased(Point point) {
		if (isDrawing) {
			endPoint = point;
			if (newShape != null) {
				newShape.setEndPoint(endPoint);
				repaint();
			}
		}
		
		ConfigInstance.getInstance().getCallBack().action();
		sendShapeMsg();
	}
	
	public void mouseWheelMoved(int strokeWidth) {
		if (selectedShape != null) {
			selectedShape.setStrokeWidth(strokeWidth);
			repaint();
		}
		sendShapeMsg();
	}
	
	// 颜色类型变化
	public void ColorChanged() {
		if (selectedShape != null) {
			selectedShape.setStrokeColor(ConfigInstance.getInstance().getShapeColor());
			repaint();
		}
		sendShapeMsg();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (MyShape myShape : allShapes) {
			myShape.draw((Graphics2D)g);
		}
	}
	
	private void sendShapeMsg() {
		TipPaneInstance tipPane = TipPaneInstance.getInstance();
		StringBuffer msg = new StringBuffer();
		
		if (this.selectedShape != null) {
			msg.append("已选择图形: " + this.selectedShape.getShapeType());
		} else {
			msg.append("未选择图形");
		}
		msg.append(";    ");
		
		if (endPoint.x != 0 && endPoint.y != 0) {
			msg.append(" 鼠标结束位置: (" + endPoint.x + "," + endPoint.y + ")")
				.append(";    ");
		}
		
		tipPane.setShapeMsg(msg.toString());
	}
	
}


