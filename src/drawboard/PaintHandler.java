package drawboard;

import java.awt.event.*;

import drawboard.DrawPane.Canvas;

public class PaintHandler implements MouseListener, MouseMotionListener {
	Canvas canvas = null;
	
	public PaintHandler(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("mouse dragged.");
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
//		System.out.println("mouse pressed.");
		canvas.mousePressed(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouse released.");
		canvas.mouseReleased(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
