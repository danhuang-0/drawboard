package drawboard.DrawPane;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;

import drawboard.*;
import drawboard.Shape.*;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 6517769642159055885L;
	
	// 所有形状的数组
	private HashSet<MyShape> allShapes = new HashSet<MyShape>();
	private HashMap<String, Class<? extends MyShape>> shapeTypes = new HashMap<String, Class<? extends MyShape>>();
	private MyShape newShape;
	
	private Point startPoint = new Point(0, 0);
	private Point endPoint = new Point(0, 0);
	
	// 处理事件
	PaintHandler handler = new PaintHandler(this);

	public Canvas() {
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		
		shapeTypes.put("直线", LineShape.class);
		shapeTypes.put("曲线", CurveShape.class);
		shapeTypes.put("圆形", OvalShape.class);
		shapeTypes.put("矩形", RectangleShape.class);
	}
	
	public void mousePressed(Point point) {
		startPoint = point;		
		ConfigInstance instance = ConfigInstance.getInstance();
		Color shapeColor = instance.getShapeColor();
		int strokeWidth = instance.getStrokeWidth();
		
		// 获取按钮按下的图形形状类型
		String shapeType = instance.getShapeType();
		
		try {
			Class<? extends MyShape> shapeClass = this.shapeTypes.get(shapeType);
			Constructor<? extends MyShape> constructor = shapeClass.getConstructor(new Class[]{Point.class, Point.class, Color.class, int.class});
			newShape = (MyShape) constructor.newInstance(new Object[]{startPoint, endPoint, shapeColor, strokeWidth});
			
			if (newShape != null) {
				this.allShapes.add(newShape);
			}
		} catch (Exception e) {
			System.out.println("----------------------------");
			e.printStackTrace();
			System.out.println("----------------------------");
		}
	}
	
	public void mouseDragged(Point point) {
		endPoint = point;
		if (newShape != null) {
			newShape.setEndPoint(endPoint);
			repaint();
		}
	}
	
	public void mouseReleased(Point point) {
		endPoint = point;
		if (newShape != null) {
			newShape.setEndPoint(endPoint);
			repaint();
		}
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
		
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (MyShape myShape : allShapes) {
			myShape.draw(g);
		}
		
	}
}
