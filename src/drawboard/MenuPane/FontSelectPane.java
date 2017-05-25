package drawboard.MenuPane;

import java.awt.Dimension;

import javax.swing.*;

public class FontSelectPane extends JPanel {
	private static final long serialVersionUID = -4006429527505209932L;
	
	private String[] fontNames = {"微软雅黑", "黑体", "宋体"};
	private String[] fontSizes = new String[20];
	
	public FontSelectPane() {
		
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(12 + 2 * i);
		}
		
		JComboBox<String> fontComboBox = new JComboBox<>(fontNames);
		fontComboBox.setPreferredSize(new Dimension(120, 50));
		fontComboBox.setBorder(BorderFactory.createTitledBorder("选择字体"));
		this.add(fontComboBox);
		
		JComboBox<String> fontSizeComboBox = new JComboBox<String>(fontSizes);
		fontSizeComboBox.setPreferredSize(new Dimension(120, 50));
		fontSizeComboBox.setBorder(BorderFactory.createTitledBorder("选择字号"));
		this.add(fontSizeComboBox);
		
	}
	
}
