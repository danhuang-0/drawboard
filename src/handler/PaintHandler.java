package handler;

/**
 * 监听鼠标的点击，滚动事件
 * 监听键盘的点击事件
 * 
 * 将事件交给 ConfigInstance 处理
 * 
 * @author Qi
 */

import java.awt.AWTEvent;
import java.awt.event.*;

import drawPane.Controller.Canvas;
import menuPane.DrawToolPane;

public class PaintHandler implements 
	MouseListener, MouseMotionListener, MouseWheelListener, AWTEventListener {
	Canvas canvas = null;
	
	
	public PaintHandler(Canvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		canvas.mouseDragged(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		canvas.mousePressed(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		canvas.mouseReleased(e.getPoint());
		ConfigInstance.getInstance().getCallBack(DrawToolPane.class.getName()).action();
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	
	/**
	 * 鼠标滚轮事件
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int strokeWidth = ConfigInstance.getInstance().getStrokeWidth() - e.getWheelRotation();
		ConfigInstance.getInstance().setStrokeWidth(strokeWidth);
		canvas.mouseWheelMoved(ConfigInstance.getInstance().getStrokeWidth());
	}

	
	/**
	 * 监听键盘点击事件
	 */
	@Override
	public void eventDispatched(AWTEvent event) {
		if (((KeyEvent)event).getID() == KeyEvent.KEY_PRESSED) {
			System.out.println(((KeyEvent)event).getKeyCode());
		}
	}

}
