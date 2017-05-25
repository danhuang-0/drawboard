package drawboard;

import java.awt.*;

import javax.swing.JButton;

public class ColorPane extends Panel {
	private static final long serialVersionUID = 6645072377493486850L;
	
	private static final Color BG_COLOR = Color.LIGHT_GRAY;
	private static final Dimension P_Dimension = new Dimension(350, 50);
	private static final Dimension B_DIMENSION = new Dimension(30, 30);

	public ColorPane(Color[] colors) {
		
		this.setBackground(BG_COLOR);
		this.setPreferredSize(P_Dimension);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (Color color : colors) {
			JButton button = new JButton();
			button.setBackground(color);
			button.setPreferredSize(B_DIMENSION);
			this.add(button);
		}
	}
}
