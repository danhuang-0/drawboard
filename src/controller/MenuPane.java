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
	 * 画笔选择区域，显示所有可绘制的图形按钮
	 */
//	private String[] buttonTitles = {"鼠标", "直线", "圆形", "矩形", "文字"};
	private String[] buttonTitles = {"鼠标", "直线", "曲线", "圆形", "矩形", "文字"};
	
	/**
	 * 字体选择区域，设置字体，字号，加粗，斜体属性
	 */
	private String[] fontNames = {"微软雅黑", "黑体", "宋体", "华文行楷"};
	private String[] fontSizes = new String[20];
	private JComboBox<String> fontComboBox;
	private JComboBox<String> fontSizeComboBox;
	private Font font = new Font("Default", Font.PLAIN, 12);

	
	/**
	 * 颜色选择区域
	 */
	private Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.ORANGE, Color.CYAN};
	private JPanel selectedColorPane;

	
	ArrayList<ArrayList<JButton>> allButtons = new ArrayList<ArrayList<JButton>>();


	public MenuPane() {
		
		this.setLayout(new BorderLayout());
		
		this.add(setUpFileToolBar(), BorderLayout.NORTH);
		this.add(setUpDrawToolPane(), BorderLayout.WEST);
		this.add(setUpFontSelectPane(), BorderLayout.CENTER);
		this.add(setUpColorSelectPane(), BorderLayout.EAST);
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
	 * 生成绘图按钮面板
	 * @return
	 */
	private JPanel setUpDrawToolPane() {
		JPanel toolPane = new JPanel();
		
		ArrayList<JButton> toolButtons = new ArrayList<JButton>();
		for (String string : buttonTitles) {
			JButton button = createButton(string, new Dimension(70, 50));
			// 添加绘图动作
			actions.put(string, new Action() {@Override public void doCmd() {canvas.setShapeType(string);}});
			toolPane.add(button);
			
			toolButtons.add(button);
			
		}
		allButtons.add(toolButtons);
		
		return toolPane;
	}
	
	
	/**
	 * 生成字体选择面板
	 * @return
	 */
	private JPanel setUpFontSelectPane() {
		JPanel fontSelectPane = new JPanel();
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(12 + 2 * i);
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
		
		ArrayList<JButton> fontStyleBtns = new ArrayList<JButton>();
		fontStyleBtns.add(boldBtn);
		fontStyleBtns.add(italicBtn);
		allButtons.add(fontStyleBtns);
		
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
				System.out.println(fontName);
			}
		};
		
		actions.put(fontComboBox.getActionCommand(), action);
		actions.put(boldBtn.getActionCommand(), action);
		actions.put(italicBtn.getActionCommand(), action);
		
		return fontSelectPane;
	}

	
	/**
	 * 生成颜色选择面板
	 * @return
	 */
	private JPanel setUpColorSelectPane() {
		JPanel colorSelectPane = new JPanel();
		
		selectedColorPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		selectedColorPane.setBackground(colors[0]);
		selectedColorPane.setPreferredSize(new Dimension(35, 35));
		selectedColorPane.setToolTipText("当前已选颜色");
		colorSelectPane.add(selectedColorPane);
				
		JPanel colorSelection = new JPanel(new GridLayout(2, 5, 2, 2));
		ArrayList<JButton> colorButtons = new ArrayList<>();
		for (Color color : colors) {
			JButton button = createButton("", new Dimension(25, 25));
			button.setActionCommand(color.toString());
			button.setBackground(color);
			colorSelection.add(button);
			colorButtons.add(button);
			actions.put(color.toString(), new Action() { @Override public void doCmd() { canvas.setShapeColor(color); } });
		}
		colorSelectPane.add(colorSelection);
		allButtons.add(colorButtons);
		
		return colorSelectPane;
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
		
		Object source = e.getSource();
		if (source.getClass().equals(JButton.class)) {
			changeButtonStyle((JButton)source);
		}
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		
		canvas.setTextFont(font);
	}
	
	// 更改按钮样式
	private void changeButtonStyle(JButton btn) {
		for (int i = 0; i < allButtons.size(); i++) {
			ArrayList<JButton> arrayList = allButtons.get(i);
			if (!arrayList.contains(btn)) {
				continue;
			}
			
			if (i == allButtons.size() - 1) {
				selectedColorPane.setBackground(btn.getBackground());
				continue;
			}
			
			for (JButton jButton : arrayList) {
				if (jButton.equals(btn)) {
					jButton.setBorder(BorderFactory.createLoweredBevelBorder());
				} else {
					jButton.setBorder(null);
				}
			}
		}
	}
	
}
