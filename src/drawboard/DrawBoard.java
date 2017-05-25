package drawboard;

import java.awt.*;
import javax.swing.*;

import drawboard.MenuPane.*;

public class DrawBoard {
	
	Container contentPane = null;
	String[] buttons = {"直线", "曲线", "矩形", "三角形"};
	Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

	public DrawBoard() {
		JFrame frame = new JFrame("画板程序");
		contentPane = frame.getContentPane();
		
		contentPane.setBackground(Color.lightGray);

		// 往frame中添加组件: 菜单组件，包括 文件操作， 绘图工具面板， 字体面板， 颜色面板
		JPanel menuPane = new JPanel(new BorderLayout());
		menuPane.add(new FileToolBar(), BorderLayout.NORTH);
		menuPane.add(new DrawToolPane(), BorderLayout.WEST);
		menuPane.add(new FontSelectPane(), BorderLayout.CENTER);
		menuPane.add(new ColorSelectPane(), BorderLayout.EAST);
		contentPane.add(menuPane, BorderLayout.NORTH);
		
		JPanel drawPane = new JPanel();
		drawPane.setBackground(Color.WHITE);
		contentPane.add(drawPane, BorderLayout.WEST);
		
		frame.setMinimumSize(new Dimension(1000, 600));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new DrawBoard();
	}
}
