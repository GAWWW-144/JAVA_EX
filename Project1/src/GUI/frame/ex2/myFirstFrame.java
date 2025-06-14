package GUI.frame.ex2;

import javax.swing.JFrame;

public class myFirstFrame {

	public static void main(String[] args) {
		JFrame f = new JFrame("123");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("내 프레임.");
		f.setLocationRelativeTo(null);
		f.setSize(300, 300);
		f.setVisible(true);
	}

}
