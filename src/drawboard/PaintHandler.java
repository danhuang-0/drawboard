package drawboard;

import java.awt.AWTEvent;
import java.awt.event.*;

import drawboard.DrawPane.Canvas;

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
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		canvas.mousePressed(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		canvas.mouseReleased(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int strokeWidth = ConfigInstance.getInstance().getStrokeWidth() - e.getWheelRotation();
		ConfigInstance.getInstance().setStrokeWidth(strokeWidth);
		canvas.mouseWheelMoved(ConfigInstance.getInstance().getStrokeWidth());
	}

	@Override
	public void eventDispatched(AWTEvent event) {
		if (((KeyEvent)event).getID() == KeyEvent.KEY_PRESSED) {
			System.out.println(((KeyEvent)event).getKeyCode());
		}
	}

}
