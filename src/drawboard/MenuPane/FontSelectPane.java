package drawboard.MenuPane;

/*
 * 字体，字号选择面板
 * Creator: Owen
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import drawboard.ConfigInstance;

public class FontSelectPane extends JPanel {
	private static final long serialVersionUID = -4006429527505209932L;
	
	private String[] fontNames = {"微软雅黑", "黑体", "宋体"};
	private String[] fontSizes = new String[20];
	
	private JComboBox<String> fontComboBox;
	private JComboBox<String> fontSizeComboBox;

	private final ActionListener action = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			int fontIndex = fontComboBox.getSelectedIndex();
			String fontName = fontNames[fontIndex];
			
			int fontSizeIndex = fontSizeComboBox.getSelectedIndex();
			String fontSize = fontSizes[fontSizeIndex];
			
			ConfigInstance.getInstance().setTextFont(fontName);
			ConfigInstance.getInstance().setTextFontSize(fontSize);
		}
	};
	
	public FontSelectPane() {
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(12 + 2 * i);
		}
		
		ConfigInstance.getInstance().setTextFont(fontNames[0]);
		ConfigInstance.getInstance().setTextFontSize(fontSizes[0]);
		
		fontComboBox = new JComboBox<String>(fontNames);
		fontComboBox.setPreferredSize(new Dimension(120, 50));
		fontComboBox.setBorder(BorderFactory.createTitledBorder("选择字体"));
		this.add(fontComboBox);
		
		fontSizeComboBox = new JComboBox<String>(fontSizes);
		fontSizeComboBox.setPreferredSize(new Dimension(120, 50));
		fontSizeComboBox.setBorder(BorderFactory.createTitledBorder("选择字号"));
		this.add(fontSizeComboBox);
		
		fontComboBox.addActionListener(action);
		fontSizeComboBox.addActionListener(action);
		
	}
	
}
