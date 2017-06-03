package drawboard;

import java.awt.*;

import javax.swing.*;

import controller.Canvas;
import controller.MenuPane;
import controller.TipPaneInstance;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("假装自己是CAD ╮(╯▽╰)╭");
		Container contentPane = frame.getContentPane();
		
		// 绘图区域
		JPanel backPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPane.setBackground(Color.GRAY);
		contentPane.add(backPane, BorderLayout.CENTER);
		controller.Canvas canvas = new Canvas();
		backPane.add(canvas);
		
		// 菜单栏
		MenuPane menuPane = new MenuPane();
		contentPane.add(menuPane, BorderLayout.NORTH);
		menuPane.setCanvas(canvas);
		
//		contentPane.add(new DrawPane(), BorderLayout.CENTER);
		contentPane.add(TipPaneInstance.getInstance(), BorderLayout.SOUTH);
		
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}