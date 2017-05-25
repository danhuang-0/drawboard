package drawboard;

import java.awt.*;
import javax.swing.*;

public class ButtonPane extends JPanel {
	private static final long serialVersionUID = 3239994320584510516L;
	
	/*
	 * 配置
	 */
	private static final Color BG_COLOR 	= 	Color.lightGray;
	private static final Dimension P_DIMENSION = 	new Dimension(350, 50);
	private static final int 	Btn_Font 	= 	15;

	
	public ButtonPane(String[] titleArray) {
		
		this.setBackground(BG_COLOR);
		this.setPreferredSize(P_DIMENSION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (String title : titleArray) {
			JButton button = new JButton(title);
			button.setFont(new Font("Default", 0, Btn_Font));
			this.add(button); 
		}
	}
}
