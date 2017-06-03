package controller;

import java.awt.*;
import javax.swing.*;

public class TipPaneInstance extends JPanel {
	private static final long serialVersionUID = 2970469861021160235L;
	
	private static TipPaneInstance tipPane = null;

	private JLabel tipLabel;
	
	private String configMsg = "";
	private String shapeMsg = "";
	
	public static TipPaneInstance getInstance() {
		if (tipPane == null) {
			tipPane = new TipPaneInstance();
		}
		return tipPane;
	}
	
	public TipPaneInstance() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(100, 30));
		tipLabel = new JLabel();
		tipLabel.setFont(new Font("Default", Font.BOLD, 15));
		this.add(tipLabel);
	}
	
	public void setShapeMsg(String msg) {
		this.shapeMsg = msg;
		update();
	}
	
	public void setConfigMsg(String msg) {
		this.configMsg = msg;
		update();
	}
	
	private void update() {
		this.tipLabel.setText(shapeMsg +"    "+ configMsg);
	}
}
