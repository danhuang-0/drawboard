package menuPane;

/*
 * 文件操作面板
 * Creator: Owen
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import drawPane.Controller.Canvas;
import handler.ConfigInstance;

public class FileToolBar extends JToolBar implements ActionListener {
	private static final long serialVersionUID = -5699127170145353535L;
	
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
		button.setSize(new Dimension(60, 60));
		button.setBackground(null);
		button.setBorderPainted(false);
		button.setFont(new Font("Default", Font.PLAIN, 14));
		button.setFocusPainted(false);
		button.addActionListener(this);
		
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Canvas canvas = ConfigInstance.getInstance().getCanvas();
		switch (e.getActionCommand()) {
		case "新建":
			canvas.newCanvas();
			break;
		case "打开":
			canvas.openCanvas();
			break;
		case "保存":
			canvas.saveCanvas();
			break;
		case "另存为":
			canvas.saveAsCanvas();
			break;
		case "关闭":
			canvas.closeCanvas();
			break;
		}		
	}
	
	
}
