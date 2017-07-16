package controller;


import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;


interface Action {
	void doCmd();
}

public class MenuPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = -7537842887441189852L;
	
	private Canvas canvas;
	
	private HashMap<String, Action> actions = new HashMap<String, Action>();
	
	/**
	 * 文件操作栏， 显示文件操作按钮
	 */
	String[] ToolBarTips = {"文件", "  |  "};
	String[] ToolBarTitles = {"新建", "打开", "保存", "另存为", "关闭"};

	
	
	
	private Font font = new Font("Default", Font.PLAIN, 12);
	


	public MenuPane() {
		
		this.setLayout(new BorderLayout());
		
		this.add(setUpFileToolBar(), BorderLayout.NORTH);
		
	}
	
	/**
	 * 生成 文件操作面板
	 * @return
	 */
	private JToolBar setUpFileToolBar() {
		JToolBar fileToolBar = new JToolBar();
		fileToolBar.setBackground(null);
		fileToolBar.setOrientation(JToolBar.HORIZONTAL);
		fileToolBar.setFloatable(false);
		fileToolBar.setMargin(new Insets(0, 10, 0, 0));
		fileToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// 标题
		for (String string : ToolBarTips) {
			JLabel label = new JLabel(string);
			label.setFont(new Font("Default", Font.BOLD, 14));
			fileToolBar.add(label);
		}
		
		// 文件操作按钮
		for (String string : ToolBarTitles) {
			JButton button = createButton(string, new Dimension(50, 20));
			fileToolBar.add(button);
		}
		
		// 添加动作
		actions.put("新建", new Action() 	{@Override public void doCmd() { canvas.newCanvas(); 	} });
		actions.put("打开", new Action() 	{@Override public void doCmd() { canvas.openCanvas(); 	} });
		actions.put("保存", new Action() 	{@Override public void doCmd() { canvas.saveCanvas(); 	} });
		actions.put("另存为", new Action() 	{@Override public void doCmd() { canvas.saveAsCanvas(); } }	);
		actions.put("关闭", new Action() 	{@Override public void doCmd() { canvas.closeCanvas(); } });
		
		return fileToolBar;
	}
	
	
	
	
	
	
	
	// 根据样式创建按钮
	private JButton createButton(String title, Dimension dimension) {
		JButton button = new JButton(title);
		button.setBorder(null);
		button.setPreferredSize(dimension);
		button.setSize(dimension);
		button.setFocusPainted(false);
		button.setFont(new Font("Default", Font.PLAIN, 14));
		button.addActionListener(this);
		
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Action act = actions.get(e.getActionCommand());
		if (act != null) {
			act.doCmd();
		}
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		
		canvas.setTextFont(font);
	}
	
	
	
}
