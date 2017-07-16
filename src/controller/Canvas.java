package controller;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import controller.Canvas;
import handler.*;
import model.*;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 6517769642159055885L;
	
	// 所有形状相关属性
	private ArrayList<MyShape> allShapes = new ArrayList<MyShape>();
	private HashMap<String, Class<? extends MyShape>> shapeTypes = new HashMap<String, Class<? extends MyShape>>();
	private MyShape newShape;
	private MyShape selectedShape;
	
	// 开始绘画时的起始终止点
	private Point startPoint = new Point(0, 0);
	private Point endPoint = new Point(0, 0);
	
	// handler 处理鼠标和键盘的事件
	PaintHandler handler = new PaintHandler(this);
	
	// 是否处于绘画状态下
	private boolean isDrawing = false;
	
	// 文件操作
	private boolean isSaved = true;	// 是否保存
	private File file = null;			// 保存的文件对象
	private JFileChooser fileChooser = null;	// 文件选择器

	private ObjectOutputStream objOutStream;	// 对象输出流
	private ObjectInputStream objInputStream;	// 对象输入流
	
	 // 图像的各种属性
	private String shapeType;
	private Color shapeColor;
	private int shapeStrokeWidth = 10; // 初始比划宽度
	private Font textFont;
	private String textContent;
	
	
	/**
	 * Canva 的初始化操作
	 */
	public Canvas() {
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1000, 600));
		
		// 设置监听对象
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		Toolkit.getDefaultToolkit().addAWTEventListener(handler, AWTEvent.KEY_EVENT_MASK);
		
		// 添加图形样式
		shapeTypes.put("直线", LineShape.class);
		shapeTypes.put("曲线", CurveShape.class);
		shapeTypes.put("圆形", OvalShape.class);
		shapeTypes.put("矩形", RectangleShape.class);
		shapeTypes.put("文字", TextShape.class);
		
		// 给消息提示窗体发送提示内容
		sendShapeMsg();
		
		// 文件选择窗口初始化
		fileChooser = new JFileChooser("D:\\");
		fileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".CAD 图像格式";
			}
			
			@Override
			public boolean accept(File f) {
				return true;
			}
		});
		fileChooser.setDialogTitle("打开文件");
	}
	
	
	/**
	 * 所有鼠标的操作的回掉
	 * @param point 鼠标动作返回的Point对象
	 * 
	 */
	public void mousePressed(Point point) {
		startPoint = point;		
		
		Class<? extends MyShape> shapeClass = this.shapeTypes.get(shapeType);
		
		isDrawing = shapeClass == null ? false : true;
		
		// 鼠标点击开始
		if (isDrawing) {
			// 处于绘图工具选项下的操作
			endPoint = new Point(0, 0);
			
			try {
				Constructor<? extends MyShape> constructor = shapeClass.getConstructor(new Class[]{Point.class, Point.class, Color.class, int.class});
				newShape = (MyShape) constructor.newInstance(new Object[]{startPoint, endPoint, shapeColor, shapeStrokeWidth});
				
				if (newShape == null) {
					return;
				}
				
				allShapes.add(newShape);
				selectedShape = newShape;
				
				if (shapeType.equals("文字")) {
					showTextInputDialog();
					if (newShape != null) {
						newShape.setTextFont(textFont);
						newShape.setTextContent(textContent);
					}
					
					repaint();
				}
				
			} catch (Exception e) {
				System.out.println("----------------------------");
				e.printStackTrace();
				System.out.println("----------------------------");
			}
		} else {
			// 除了画图状态，还有移动和缩放
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
		isSaved = false;
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
				if (shapeType.equals("移动")) {
					// 移动时候的操作
					Point p = new Point(point.x - startPoint.x, point.y - startPoint.y);
					selectedShape.setOffsetPoint(p);
					startPoint = point;
				} else {
					// 缩放时候的操作
					selectedShape.setEndPoint(point);
					
				}
				repaint();
			}
		}
		
		sendShapeMsg();
		isSaved = false;
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

		sendShapeMsg();
		isSaved = false;
	}
	
	public void mouseWheelMoved(int strokeWidth) {
		if (selectedShape != null) {
			selectedShape.setStrokeWidth(strokeWidth);
			repaint();
		}
		sendShapeMsg();
		isSaved = false;
	}
	
	
	/**
	 * 菜单栏颜色修改后的回掉
	 */
	public void ColorChanged() {
		if (selectedShape != null) {
			repaint();
		}
		sendShapeMsg();
		isSaved = false;
	}
	
	/**
	 * 文件的操作
	 * 1. 新建
	 * 2. 打开
	 * 3. 保存
	 * 4. 另存 
	 * 5. 关闭
	 * 
	 * ps. 显示弹窗和文件选择操作
	 */
	
	public void newCanvas() {
		if (!isSaved) {
			int result = showMessage("图像未保存，是否继续操作？");
			if (result != JOptionPane.YES_OPTION) {
				return; 
			}
		}
		
		reset();
	}
	
	public void openCanvas() {
		if (!isSaved) {
			int msgResult = showMessage("图像未保存，是否先进行保存操作？");
			if (msgResult == JOptionPane.YES_OPTION) {
				// 如果保存，调用保存函数
				saveCanvas();
			}
		}
		
		// 进行打开操作
		int openResult = showFileChooser("Open");
		if (openResult == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (file.exists()) {
				// 打开文件之前进行重置操作
				reset();
				try {
					objInputStream = new ObjectInputStream(
							new FileInputStream(file)
							);
					
					int shapeCount = objInputStream.readInt();
					for (int i = 0; i < shapeCount; i++) {
						MyShape shape = (MyShape)objInputStream.readObject();
						allShapes.add(shape);
					}
					repaint();
				} 
				catch (IOException e) {
//					e.printStackTrace();
					System.out.println("ERROR: openCanvas  " + e.getMessage());
				} 
				catch (ClassNotFoundException e) {
//					e.printStackTrace();
					System.out.println("ERROR: openCanvas  " + e.getMessage());
				}			
			}
		}
	}
	
	public void saveCanvas() {
		if (file == null) {
			int saveResult = showFileChooser("Save");
			if (saveResult == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
			
			// 文件选择窗口中点击了取消的时候
			if (file == null) {
				System.out.println("点击取消按钮");
				return;
			}
		}
		
		try {
			objOutStream = new ObjectOutputStream(
					new FileOutputStream(file)
					);
			// 对象个数
			objOutStream.writeInt(allShapes.size());
			for (MyShape myShape : allShapes) {
				objOutStream.writeObject(myShape);
			}
			
			isSaved = true;
		} catch (Exception e) { 
			System.out.println("ERROR: saveCanvas  " + e.getMessage());
		}
		
		sendShapeMsg();
	}
	
	public void saveAsCanvas() {
		file = null;
		saveCanvas();
	}
	
	public void closeCanvas() {
		StringBuffer strBuf = new StringBuffer();
		if (!isSaved) {
			strBuf.append("图像未保存，");
		}
		strBuf.append("确认退出？");
		
		int result = showMessage(strBuf.toString());
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	private int showMessage(String msg) {
		return JOptionPane.showConfirmDialog(this, msg, "提示", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private int showFileChooser(String approveText) {
		if (approveText == null) {
			approveText = "Choose";
		}
		fileChooser.setApproveButtonText(approveText);
		
		return fileChooser.showOpenDialog(this);
	}
	
	
	/**
	 * 弹出文本输入框
	 */
	private void showTextInputDialog() {
		String text = JOptionPane.showInputDialog(this, "", "请输入文本内容", JOptionPane.QUESTION_MESSAGE);
		
		if (text == null) {
			allShapes.remove(newShape);
			newShape = null;
		} else {
			this.textContent = text;
		}
	}
	
	
	/**
	 * 给窗口最底部的提示窗体提供消息内容
	 */
	private void sendShapeMsg() {
		TipPaneInstance tipPane = TipPaneInstance.getInstance();
		StringBuffer msg = new StringBuffer();
		
		if (isSaved && (file != null)) {
			msg.append("文件已保存:")
				.append(file.getName());
		} else {
			msg.append("文件未保存");
		}
		msg.append(";    ");
		
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
	
	/**
	 * 重画
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		for (MyShape myShape : allShapes) {
			myShape.draw(g2d);
		}
	}
	
	private void reset() {
		allShapes = new ArrayList<MyShape>();
		isSaved = true;
		file = null;
		repaint();
	}


	public void removeSelectedObject() {
		if (selectedShape != null) {
			allShapes.remove(selectedShape);
			repaint();
		}
	}
	
	/**
	 * 各种属性的设置
	 * @param shapeType
	 */
	
	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
		repaint();
	}
	public String getShapeType() {
		return shapeType;
	}
	
	
	public void setTextFont(Font textFont) {
		this.textFont = textFont;
		if (selectedShape != null) {
			selectedShape.setTextFont(textFont);
			repaint();
		}
	}
	public Font getTextFont() {
		return textFont;
	}


	public void setShapeColor(Color color) {
		this.shapeColor = color;
		if (selectedShape != null) {
			selectedShape.setStrokeColor(color);
		}
		repaint();
	}
	public Color getShapeColor() {
		return shapeColor;
	}
	
	
	public void setShapeStrokeWidthOffset(int offset) {
		if (selectedShape != null) {
			selectedShape.setStrokeWidth(selectedShape.getStrokeWidth() + offset);
		}
		repaint();
	}
	
	public void setShapeLongOffset(int offset) {
		if (selectedShape != null) {
			selectedShape.changeEndPoint(offset);
		}
		repaint();
	}
	
	public int getShapeStrokeWidth() {
		return shapeStrokeWidth;
	}
	
}


