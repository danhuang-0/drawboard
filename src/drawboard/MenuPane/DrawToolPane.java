package drawboard.MenuPane;

/*
 * 画图工具选择面板
 * Creator: Owen
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import drawboard.ConfigInstance;

public class DrawToolPane extends JPanel {
	private static final long serialVersionUID = -30717208852257554L;
	
	private JButton selectedButton = new JButton();
	
	private final ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			selectedButton.setBorder(null);
			button.setBorder(BorderFactory.createLoweredBevelBorder());
			
			selectedButton = button;
			
			ConfigInstance.getInstance().setShapeType(e.getActionCommand());
		}
	};
	
	private String[] buttonTitles = {"鼠标", "直线", "曲线", "圆形", "矩形", "文字"};

	public DrawToolPane() {
		ConfigInstance.getInstance().setShapeType(buttonTitles[0]);
		
		for (String string : buttonTitles) {
			JButton button = createButton(string);
			this.add(button);
		}
		
	}

	private JButton createButton (String title) {
		JButton button = new JButton(title);
		button.setFont(new Font("Default", Font.PLAIN, 14));
		button.setBackground(null);
		button.setPreferredSize(new Dimension(80, 50));
		button.setMargin(new Insets(0, 20, 0, 20));
		button.addActionListener(action);
		return button;
	}
}
