package drawboard;

/*
 * 主面板
 */

import java.awt.*;
import javax.swing.*;

import drawboard.DrawPane.DrawPane;
import drawboard.MenuPane.*;

public class DrawBoard {
	
	Container contentPane = null;

	public DrawBoard() {
		JFrame frame = new JFrame("假装自己是CAD ╮(╯▽╰)╭");
		contentPane = frame.getContentPane();
		
		contentPane.add(new MenuPane(), BorderLayout.NORTH);
		contentPane.add(new DrawPane(), BorderLayout.CENTER);
		
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
