package drawboard.DrawPane;

import java.awt.*;

import javax.swing.*;

public class DrawPane extends JPanel {
	private static final long serialVersionUID = 4417620664081557822L;

	public DrawPane() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		this.setBackground(Color.GRAY);
		
		JPanel canvas = new JPanel();
		canvas.setBackground(Color.WHITE);
		canvas.setPreferredSize(new Dimension(800, 600));
		this.add(canvas);
	}

}
