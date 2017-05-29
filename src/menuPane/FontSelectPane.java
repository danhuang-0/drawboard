package menuPane;

/*
 * 字体，字号选择面板
 * Creator: Owen
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import handler.ConfigInstance;

public class FontSelectPane extends JPanel {
	private static final long serialVersionUID = -4006429527505209932L;
	
	private String[] fontNames = {"微软雅黑", "黑体", "宋体"};
	private String[] fontSizes = new String[20];
	
	private JComboBox<String> fontComboBox;
	private JComboBox<String> fontSizeComboBox;
	
	private boolean isBold = false;
	private boolean isItalic = false;

	private final ActionListener action = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "B":
					isBold = !isBold;
					break;
				case "I":
					isItalic = !isItalic;
					break;
				default:
					break;
			}
			
			createFont();
		}
	};
	
	public FontSelectPane() {
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(12 + 2 * i);
		}
		
		// 字体选择框
		fontComboBox = new JComboBox<String>(fontNames);
		fontComboBox.setPreferredSize(new Dimension(120, 50));
		fontComboBox.setBorder(BorderFactory.createTitledBorder("选择字体"));
		this.add(fontComboBox);
		
		// 字号选择框
		fontSizeComboBox = new JComboBox<String>(fontSizes);
		fontSizeComboBox.setPreferredSize(new Dimension(120, 50));
		fontSizeComboBox.setBorder(BorderFactory.createTitledBorder("选择字号"));
		this.add(fontSizeComboBox);
		
		// 字体属性选择
		JPanel fontStylePane = new JPanel(new FlowLayout());
		fontStylePane.setBorder(BorderFactory.createTitledBorder("属性"));
		fontStylePane.setPreferredSize(new Dimension(80, 50));
		// 加粗
		JButton boldBtn = new JButton("B");
		boldBtn.setBorder(null);
		boldBtn.setPreferredSize(new Dimension(30, 20));
		boldBtn.setFocusPainted(false);
		boldBtn.addActionListener(action);
		fontStylePane.add(boldBtn);
		// 斜体
		JButton italicBtn = new JButton("I");
		italicBtn.setBorder(null);
		italicBtn.setPreferredSize(new Dimension(30, 20));
		italicBtn.setFocusPainted(false);
		italicBtn.addActionListener(action);
		fontStylePane.add(italicBtn);
		
		this.add(fontStylePane);
		
		fontComboBox.addActionListener(action);
		fontSizeComboBox.addActionListener(action);

		createFont();
	}
	
	private void createFont() {
		
		int fontIndex = fontComboBox.getSelectedIndex();
		String fontName = fontNames[fontIndex];
		
		int fontSizeIndex = fontSizeComboBox.getSelectedIndex();
		String fontSize = fontSizes[fontSizeIndex];
		
		int fontBold = isBold ? Font.BOLD : Font.PLAIN;
		int fontItalic = isItalic ? Font.ITALIC : Font.PLAIN;
		
		Font font = new Font(fontName, fontBold | fontItalic, Integer.parseInt(fontSize));
		
		ConfigInstance.getInstance().setFont(font);
	}
	
}
