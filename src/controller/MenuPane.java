package controller;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

	
	
	
	/**
	 * 字体选择区域，设置字体，字号，加粗，斜体属性
	 */
	private String[] fontNames = {"微软雅黑", "黑体", "宋体", "华文行楷"};
	private String[] fontSizes = new String[20];
	private JComboBox<String> fontComboBox;
	private JComboBox<String> fontSizeComboBox;
	private Font font = new Font("Default", Font.PLAIN, 12);
	private ArrayList<JButton> fontStyleBtns = new ArrayList<JButton>();


	public MenuPane() {
		
		this.setLayout(new BorderLayout());
		
		this.add(setUpFileToolBar(), BorderLayout.NORTH);
		this.add(setUpFontSelectPane(), BorderLayout.CENTER);
		
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
	
	
	
	
	
	/**
	 * 生成字体选择面板
	 * @return
	 */
	private JPanel setUpFontSelectPane() {
		JPanel fontSelectPane = new JPanel();
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(20 + 4 * i);
		}
		
		// 字体选择框
		fontComboBox = new JComboBox<String>(fontNames);
		fontComboBox.setPreferredSize(new Dimension(120, 50));
		fontComboBox.setBorder(BorderFactory.createTitledBorder("选择字体"));
		fontSelectPane.add(fontComboBox);
		
		// 字号选择框
		fontSizeComboBox = new JComboBox<String>(fontSizes);
		fontSizeComboBox.setPreferredSize(new Dimension(120, 50));
		fontSizeComboBox.setBorder(BorderFactory.createTitledBorder("选择字号"));
		fontSelectPane.add(fontSizeComboBox);
		
		// 字体属性选择
		JPanel fontStylePane = new JPanel(new FlowLayout());
		fontStylePane.setBorder(BorderFactory.createTitledBorder("属性"));
		fontStylePane.setPreferredSize(new Dimension(80, 50));

		// 加粗， 斜体按钮
		JButton boldBtn = createButton("B", new Dimension(30, 20));
		fontStylePane.add(boldBtn);
		JButton italicBtn = createButton("I", new Dimension(30, 20));
		fontStylePane.add(italicBtn);
		
		fontStyleBtns.add(boldBtn);
		fontStyleBtns.add(italicBtn);
		
		fontSelectPane.add(fontStylePane);

		fontComboBox.addActionListener(this);
		fontSizeComboBox.addActionListener(this);
		
		Action action = new Action() {
			@Override
			public void doCmd() {
				String fontName = (String)fontComboBox.getSelectedItem();
				String fontSize = (String)fontSizeComboBox.getSelectedItem();
				int fontBold = font.isBold() ? Font.BOLD : Font.PLAIN;
				int fontItalic = font.isItalic() ? Font.ITALIC : Font.PLAIN;
				
				font = new Font(fontName, fontBold | fontItalic, Integer.parseInt(fontSize));
				canvas.setTextFont(font);
			}
		};
		
		actions.put(fontComboBox.getActionCommand(), action);
		actions.put(boldBtn.getActionCommand(), action);
		actions.put(italicBtn.getActionCommand(), action);
		
		return fontSelectPane;
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
