package GUI.event.ex1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventFrame extends JFrame implements ActionListener, MouseListener{
	
	
	private JButton btn;  //멤버 필드로 이동, 지역변수 와 인스턴스 변수의차이..

	public EventFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(null);
		
		btn = new JButton("버튼");
		btn.addActionListener(this);
		btn.addMouseListener(this);
	
		add(btn);
		
		setLayout(new FlowLayout());
		
	
	}
	
	public static void main(String[] args) {
		EventFrame frame = new EventFrame("이벤트 이해하기");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(btn.getText().equals("버튼") ) {   //.equals 방식말고 == 주소를 직접비교하는방식으로하면안됨
			btn.setText("Button");
		} else {
			btn.setText("버튼");
		}

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		btn.setBackground(Color.yellow);
		System.out.println("e.getPoint()");
		System.out.println("IN");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("OUT");
		btn.setBackground(Color.red);
		// TODO Auto-generated method stub
		
	}

}