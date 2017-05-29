package menuPane;

/*
 * 画图工具选择面板
 * Creator: Owen
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import handler.*;

public class DrawToolPane extends JPanel {
	private static final long serialVersionUID = -30717208852257554L;
	
	private JButton selectedButton = null;
	private JButton defaultButton = null;
	
	private final ActionListener action = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			selectedButton.setBorder(null);
			selectedButton = btn;
			btn.setBorder(BorderFactory.createLoweredBevelBorder());
			
			ConfigInstance.getInstance().setShapeType(btn.getActionCommand());
			
			if (e.getActionCommand().equals("文字")) {
				JFrame newFrame = new JFrame("请输入内容");
				TextField textField = new TextField();
				newFrame.add(textField);
				
				newFrame.pack();
				newFrame.setVisible(true);
				
			}
		}
	};
	
	private String[] buttonTitles = {"鼠标", "直线", "圆形", "矩形", "文字"};
//	private String[] buttonTitles = {"鼠标", "直线", "曲线", "圆形", "矩形", "文字"};

	public DrawToolPane() {
		
		ConfigInstance.getInstance().addCallBack(this.getClass().getName(), new CallBackHandlerAdapter() {
			@Override
			public void action() {
				resetButtonStyle();
			}
			
		});
		
		for (String string : buttonTitles) {
			JButton button = createButton(string);
			this.add(button);
		}
				
	}

	private JButton createButton (String title) {
		JButton button = new JButton(title);
		button.setFont(new Font("Default", Font.PLAIN, 14));
		button.setBackground(null);
		button.setPreferredSize(new Dimension(70, 50));
		button.setMargin(new Insets(0, 20, 0, 20));
		button.addActionListener(action);
		button.setBorder(null);
		button.setFocusPainted(false);
		
		if (defaultButton == null) {
			defaultButton = button;
			selectedButton = button;
			button.setBorder(BorderFactory.createLoweredBevelBorder());
		}
		
		return button;
	}
	
	public void resetButtonStyle() {
		selectedButton.setBorder(null);
		defaultButton.setBorder(BorderFactory.createLoweredBevelBorder());
		ConfigInstance.getInstance().setShapeType(defaultButton.getActionCommand());
	}
}
