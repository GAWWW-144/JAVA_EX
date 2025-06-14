package GUI.frame.ex2;

import javax.swing.JFrame;

public class MyTempleteFrame extends JFrame{
	
	public MyTempleteFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MyTempleteFrame frame = new  MyTempleteFrame("Swing");


	}

}