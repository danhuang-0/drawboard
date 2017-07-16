package handler;

/**
 * 监听鼠标的点击，滚动事件
 * 监听键盘的点击事件
 * 
 * 将事件交给 ConfigInstance 处理
 * 
 * 
 */

import java.awt.AWTEvent;
import java.awt.event.*;

import controller.Canvas;

public class PaintHandler implements 
	MouseListener, MouseMotionListener, AWTEventListener {
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
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	
	
	
	/**
	 * 监听键盘点击事件
	 */
	@Override
	public void eventDispatched(AWTEvent event) {
		KeyEvent keyEvent = (KeyEvent)event;
		if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
			if (keyEvent.getKeyCode() == 'R') {
				canvas.removeSelectedObject();
			}
			if (keyEvent.getKeyCode() == '.') {
				canvas.setShapeStrokeWidthOffset(1);
			}
			if (keyEvent.getKeyCode() == ',') {
				canvas.setShapeStrokeWidthOffset(-1);
			}
			if (keyEvent.getKeyCode() == '='){
				canvas.setShapeLongOffset(1);
			}
			if (keyEvent.getKeyCode() == '-'){
				canvas.setShapeLongOffset(-1);
			}
			
		}
	}

}
