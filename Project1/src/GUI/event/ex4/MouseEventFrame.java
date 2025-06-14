package GUI.event.ex4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MouseEventFrame {

	private JFrame frame;
	private JLabel LblName;
	private final int STEP = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouseEventFrame window = new MouseEventFrame();
					window.frame.setLocationRelativeTo(null);  //직접타이핑
					
					window.frame.setVisible(true);
					window.frame.setFocusable(true);
					window.frame.getContentPane().requestFocus();   //visible 나타난이후에 포커스를잡아야 포커스를 뺏기지않음
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MouseEventFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				int keyCode = e.getKeyCode();
				
				System.out.println(keyChar + "," + keyCode);
				
				if(keyCode == KeyEvent.VK_RIGHT) 
					LblName.setLocation(LblName.getX() + 10 , LblName.getY());
					else if (keyCode == KeyEvent.VK_LEFT) 
						LblName.setLocation(LblName.getX()- 10, LblName.getY());
				
					else if (keyCode == KeyEvent.VK_UP) 
						LblName.setLocation(LblName.getX() , LblName.getY() - 10);
				
					else if (keyCode == KeyEvent.VK_DOWN) 
						LblName.setLocation(LblName.getX() , LblName.getY() + 10);
					
					
					
				
			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				LblName.setLocation(e.getPoint());
				
				
			}
		});
		frame.setTitle("마우스 이벤트");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LblName = new JLabel("홍길동");
		LblName.setBounds(83, 100, 48, 19);
		LblName.setFont(new Font("굴림", Font.PLAIN, 16));
		frame.getContentPane().add(LblName);
	}
}


//시험범위 *****클래스 상송 제네릭 만들수있도록 
