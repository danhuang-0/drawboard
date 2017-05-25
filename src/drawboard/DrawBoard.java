package drawboard;

import java.awt.*;
import javax.swing.*;

public class DrawBoard {
	
	Container contentPane = null;
	String[] buttons = {"直线", "曲线", "矩形", "三角形"};
	Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

	public DrawBoard() {
		JFrame frame = new JFrame("画板程序");
		contentPane = frame.getContentPane();
//		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
//		contentPane.setLayout(null);

		// 往frame中添加组件
//		contentPane.add(new ButtonPane(buttons));
//		contentPane.add(new ColorPane(colors));
		JPanel manuePane = new JPanel(new BorderLayout());
		manuePane.add(new FileToolBar(), BorderLayout.NORTH);
		manuePane.add(new DrawToolBar());
		
		contentPane.add(manuePane, BorderLayout.NORTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new DrawBoard();
	}
}
