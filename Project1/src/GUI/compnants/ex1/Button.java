package GUI.compnants.ex1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;

public class Button {

	private JFrame frame;
	private JLabel lblResult;
	private int sum;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Button window = new Button();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Button() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sum = 0;
		frame = new JFrame();
		frame.setTitle("다양한 컴포넌트");
		frame.setBounds(100, 100, 458, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("다양한 컴포넌트 다루기");
		lblNewLabel.setFont(new Font("HY엽서L", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("종료!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setIcon(new ImageIcon("images/normalIcon.gif"));
		btnNewButton.setRolloverIcon(new ImageIcon("images/RolloverIcon.gif"));
		btnNewButton.setPressedIcon(new ImageIcon("images/PressedIcon.gif"));
		btnNewButton.setBounds(271, 437, 163, 74);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("사과 100원, 배 500원, 체리 2000원");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(94, 24, 263, 20);
		panel_1.add(lblNewLabel_1);
		
		JCheckBox CkApple = new JCheckBox("사과");
		CkApple.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				int i = e.getStateChange();
				
				
				if (i == 1) {
					sum += 100;
				} 
				else 
				{
					sum -= 100;
				}
				lblResult.setText("현재" + sum + "원입니다.");
			
			}
		});
		CkApple.setFont(new Font("굴림", Font.PLAIN, 16));
		CkApple.setBounds(68, 74, 64, 32);
		CkApple.setBorderPainted(true);
		panel_1.add(CkApple);
		
		JCheckBox CkPear = new JCheckBox("배");
		CkPear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = e.getStateChange();
				if (i == ItemEvent.SELECTED) {
					sum += 500;
				}	else {
					sum -= 500;
				}
				lblResult.setText("현재" + sum + "원입니다.");
			}
		});
		CkPear.setFont(new Font("굴림", Font.PLAIN, 16));
		CkPear.setBounds(196, 74, 55, 32);
		CkPear.setBorderPainted(true);
		panel_1.add(CkPear);
		
		JCheckBox CkCherry = new JCheckBox("체리");
		CkCherry.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = e.getStateChange();
				if (i == ItemEvent.SELECTED) {
					sum += 2000;
				}	else {
					sum -= 2000;
				}
				lblResult.setText("현재" + sum + "원입니다.");
			}
		});
		
		CkCherry.setFont(new Font("굴림", Font.PLAIN, 16));
		CkCherry.setBounds(315, 74, 64, 32);
		CkCherry.setBorderPainted(true);
		panel_1.add(CkCherry);
		
	    lblResult = new JLabel("현재 0원 입니다.");
		lblResult.setFont(new Font("굴림", Font.PLAIN, 20));
		lblResult.setBounds(105, 122, 235, 27);
		panel_1.add(lblResult);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("남자");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblResult.setText("남자선택");
				}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(108, 180, 55, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("여자");
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblResult.setText("여자선택");
				}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(271, 180, 61, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		textField = new JTextField();
		textField.setBounds(90, 239, 125, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("추가");
		btnNewButton_1.setBounds(243, 238, 97, 23);
		panel_1.add(btnNewButton_1);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"홍길동 ", "김길동", "이승훈"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(20, 271, 192, 165);
		panel_1.add(list);
		
		JButton btnNewButton_1_1 = new JButton(">>");
		btnNewButton_1_1.setBounds(232, 319, 64, 38);
		panel_1.add(btnNewButton_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(316, 271, 105, 23);
		panel_1.add(comboBox);
	}
}
