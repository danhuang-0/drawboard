package drawboard.MenuPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FileToolBar extends JToolBar {
	private static final long serialVersionUID = -5699127170145353535L;
	
	private ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("关闭")) {
				System.exit(0);
			}
		}
	};
	
	String[] ToolBarTips = {"文件", "  |  "};
	String[] ToolBarTitles = {"新建", "打开", "保存", "另存为", "关闭"};
	
	public FileToolBar() {
		this.setBackground(null);
		this.setOrientation(JToolBar.HORIZONTAL);
		this.setFloatable(false);
		this.setMargin(new Insets(0, 10, 0, 0));
		
		// 标题
		for (String string : ToolBarTips) {
			this.add(CreateTip(string));
		}
		
		// 文件操作按钮
		for (String string : ToolBarTitles) {
			this.add(createButton(string));
		}
	}
	
	private JLabel CreateTip(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("Default", Font.BOLD, 14));
		return label;
	}
	
	private JButton createButton(String title) {
		JButton button = new JButton(title);
//		button.setToolTipText(title);
		button.setSize(new Dimension(60, 60));
		button.setBackground(null);
		button.setBorderPainted(false);
		button.setFont(new Font("Default", 0, 14));
		button.addActionListener(action);
		
		return button;
	}
	
	
}
