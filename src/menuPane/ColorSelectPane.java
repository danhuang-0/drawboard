package menuPane;

/*
 * 颜色选择面板
 * Creator: Owen
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import handler.ConfigInstance;

public class ColorSelectPane extends JPanel {
	private static final long serialVersionUID = -7060372174270416988L;
	
	private JPanel selectedColorPane = null;

	private Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.ORANGE, Color.CYAN};
	
	private final ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			Color c = btn.getBackground();
			selectedColorPane.setBackground(c);
			ConfigInstance.getInstance().setShapeColor(c);
		}
	};
	
	public ColorSelectPane() {

		selectedColorPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		selectedColorPane.setBackground(colors[0]);
		selectedColorPane.setPreferredSize(new Dimension(35, 35));
		selectedColorPane.setToolTipText("当前已选颜色");
		this.add(selectedColorPane);
		
		ConfigInstance.getInstance().setShapeColor(colors[0]);
		
		JPanel colorSelection = new JPanel(new GridLayout(2, 5, 2, 2));
		for (Color color : colors) {
			colorSelection.add(createButton(color));
		}
		this.add(colorSelection);
	}
	
	private JButton createButton(Color color) {
		JButton button = new JButton();
		
		button.setPreferredSize(new Dimension(25, 25));
		button.setBackground(color);
		button.setBorderPainted(false);
		
		button.addActionListener(action);
		return button;
	}
}
