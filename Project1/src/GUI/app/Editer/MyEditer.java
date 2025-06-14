package GUI.app.Editer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import GUI.compnants.ex1.ChatForm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;

public class MyEditer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyEditer frame = new ChatForm("",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyEditer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);s
		setBounds(100, 100, 546, 476);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitFunc();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Options");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Font");
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_3 = new JMenu("Apps");
		menuBar.add(mnNewMenu_3);
		
		mItemChat = new JMenuItem("My Chat");
		mItemChat.addActionListener(this);
//		MenuItemChat.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ChatForm chat = new ChatForm("홍길동 채팅",this);
//				chat.getTfInput().setText("홍길동");
//				chat.getFrame().setVisible(true);
//			}
//		});
//		mnNewMenu_3.add(MenuItemChat);

		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Info");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "myEditor\n Program by 이승훈", "프로그램정보", JOptionPane.ERROR_MESSAGE);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(192, 192, 192));
		scrollPane.setColumnHeaderView(toolBar);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(
				"C:\\Users\\PC\\Downloads\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250407\\Project1\\src\\GUI\\img\\new.png"));
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(
				"C:\\Users\\PC\\Downloads\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250407\\Project1\\src\\GUI\\img\\open.png"));
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(
				"C:\\Users\\PC\\Downloads\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250407\\Project1\\src\\GUI\\img\\save.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitFunc();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(
				"C:\\Users\\PC\\Downloads\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250428_202145071\\java_ws_LSH_20250407\\Project1\\src\\GUI\\img\\exit.png"));
		toolBar.add(btnNewButton_3);
		
		//JTextArea textArea = new JTextArea();
		//	scrollPane.setViewportView(textArea);
			// JTextArea textArea = new JTextArea();
			RSyntaxTextArea taEditor = new RSyntaxTextArea();
			taEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
			taEditor.setCodeFoldingEnabled(true);
			RTextScrollPane sp = new RTextScrollPane(taEditor);
			
			contentPane.add(sp);
			// scrollPane.setViewportView(textArea);

		//scrollPane.setViewportView(textArea);

		
	}

	private void exitFunc() {
		int res = JOptionPane.showConfirmDialog(this, "정말 종료할까요?", "나가기", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	@override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mItemChat) {
			ChatForm chat = new ChatForm("홍길동채팅", this);
			chat.getFrame().setVisible(true);
			chat.getTfInput().setText("홍길동",this);
			
			
		}
	}

}
