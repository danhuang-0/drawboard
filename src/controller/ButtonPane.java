package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ButtonPane extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1770528142531236861L;
	/**
	 * 画笔选择区域，显示所有可绘制的图形按钮
	 */
	private Canvas canvas;
	private HashMap<String, Action> actions = new HashMap<String, Action>();
	private String[] buttonTitles = {"移动", "直线", "曲线", "圆形", "矩形", "文字"};
	ArrayList<JButton> toolButtons = new ArrayList<JButton>();
	/**
	 * 颜色选择区域
	 */
	private Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.ORANGE, Color.CYAN};
	private JPanel selectedColorPane;
	private ArrayList<JButton> colorBtns = new ArrayList<JButton>();
	private ArrayList<JButton> fontStyleBtns = new ArrayList<JButton>();
	private Font font = new Font("Default", Font.PLAIN, 12);
	public ButtonPane(){
		this.setLayout(new BorderLayout());
		this.add(setUpDrawToolPane(), BorderLayout.WEST);
		this.add(setUpColorSelectPane(), BorderLayout.SOUTH);
	}
	/**
	 * 生成绘图按钮面板
	 * @return
	 */
	private JPanel setUpDrawToolPane() {
		JPanel toolPane = new JPanel(new GridLayout(6, 1, 10, 10));
		
		for (String string : buttonTitles) {
			JButton button = createButton(string, new Dimension(100, 50));
			// 添加绘图动作
			actions.put(string, new Action() {@Override public void doCmd() {canvas.setShapeType(string);}});
			toolPane.add(button);
			
			toolButtons.add(button);
			
		}
		
		return toolPane;
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
				
		JPanel colorSelection = new JPanel(new GridLayout(5, 2, 2, 2));
		for (Color color : colors) {
			JButton button = createButton("", new Dimension(25, 25));
			button.setActionCommand(color.toString());
			button.setBackground(color);
			colorSelection.add(button);
			colorBtns.add(button);
			actions.put(color.toString(), new Action() { @Override public void doCmd() { canvas.setShapeColor(color); } });
		}
		colorSelectPane.add(colorSelection);
		
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Action act = actions.get(arg0.getActionCommand());
		if (act != null) {
			act.doCmd();
		}
		
		Object source = arg0.getSource();
		if (source.getClass().equals(JButton.class)) {
			changeButtonStyle((JButton)source);
		}
	}
	// 更改按钮样式
		private void changeButtonStyle(JButton btn) {
			// 画笔按钮
			if (toolButtons.contains(btn)) {
				for (JButton jButton : toolButtons) {
					if (jButton.equals(btn)) {
						jButton.setBorder(BorderFactory.createLoweredBevelBorder());
					} else {
						jButton.setBorder(null);
					}
				}
			}
			
			if (fontStyleBtns.contains(btn)) {
				Border border = btn.getBorder() == null ? BorderFactory.createLoweredBevelBorder() : null;
				btn.setBorder(border);
			}
			
			if (colorBtns.contains(btn)) {
				selectedColorPane.setBackground(btn.getBackground());
			}
		}
		public void setCanvas(Canvas canvas) {
			this.canvas = canvas;
			
			canvas.setTextFont(font);
		}
}
