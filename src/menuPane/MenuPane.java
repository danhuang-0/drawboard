package menuPane;

import java.awt.*;

import javax.swing.*;

public class MenuPane extends JPanel {
	private static final long serialVersionUID = -7537842887441189852L;

	public MenuPane() {
		this.setLayout(new BorderLayout());
		this.add(new FileToolBar(), BorderLayout.NORTH);
		this.add(new DrawToolPane(), BorderLayout.WEST);
		this.add(new FontSelectPane(), BorderLayout.CENTER);
		this.add(new ColorSelectPane(), BorderLayout.EAST);
	}
}
