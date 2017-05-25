package drawboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class DrawToolBar extends JToolBar {
	private static final long serialVersionUID = -30717208852257554L;
	
	private static final ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
		}
	};
	
	private String[] buttonTitles = {"直线", "曲线", "圆形", "矩形", "文字"};

	public DrawToolBar() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setFloatable(true);
		this.setOrientation(JToolBar.HORIZONTAL);
		this.setPreferredSize(new Dimension(600, 50));
		
		for (String string : buttonTitles) {
			this.add(createButton(string));
		}
		
	}

	private JButton createButton (String title) {
		JButton button = new JButton(title);
		button.setFont(new Font("Default", Font.PLAIN, 14));
		button.setBackground(null);
//		button.setSize(new Dimension(160, 60));
		button.setSize(200, 50);
//		button.setPreferredSize(new Dimension(200, 60));
		button.setMargin(new Insets(0, 20, 0, 20));
		button.addActionListener(action);
		return button;
	}
}
