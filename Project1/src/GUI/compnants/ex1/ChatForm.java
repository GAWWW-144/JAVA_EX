package GUI.compnants.ex1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatForm {

	private JFrame frame;
	private JTextField TfInput;
	private JTextArea TaChat;
	
	

	public JFrame getFrame() {
		return frame;
	}


	public JTextField getTfInput() {
		return TfInput;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatForm window = new ChatForm("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param title 
	 */
	public ChatForm(String title) {
		initialize(title);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title) {
		frame = new JFrame();
		frame.setTitle("제목");
		frame.setBounds(100, 100, 395, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("My Chat");
		lblNewLabel.setFont(new Font("굴림체", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 255, 255));
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		TfInput = new JTextField();
		TfInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeMsg();
			}

			
		});
		
		
		panel_1.add(TfInput);
		TfInput.setColumns(25);
		
		JButton ButtonSend = new JButton("Send");
		ButtonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeMsg();
				
			}
		});
		panel_1.add(ButtonSend);
		
		TaChat = new JTextArea();
		TaChat.setWrapStyleWord(true);
		TaChat.setLineWrap(true);
		
		JScrollPane sp = new JScrollPane(TaChat);
		frame.getContentPane().add(TaChat, BorderLayout.CENTER);
		
	}
	
	private void typeMsg() {
		String input = TfInput.getText();
		
		TaChat.append("{msg} :" + input + "\n");
		TfInput.setText("");
		TfInput.requestFocus();
	}

}
