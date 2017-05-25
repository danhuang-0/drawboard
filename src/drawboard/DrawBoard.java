package drawboard;

/*
 * 主面板
 * Creator: Owen
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
		
		frame.setMinimumSize(new Dimension(900, 800));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new DrawBoard();
	}
}
