package Bank;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * 계좌 정보 화면입니다.
 * '거래내역' 버튼이 추가되었습니다.
 */
public class AccountFrame extends JFrame {

    // ... (기존 멤버 변수들)
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfAccountNumber;
    private JTextField tfBalance;
    private JTextField tfAddress;
    private JTextField tfUserId;
    private String currentUserId;
    private String currentAccountNumber;


    public AccountFrame(String userId, String name, String address, String accountNumber, long balance) {
        this.currentUserId = userId;
        this.currentAccountNumber = accountNumber;
        
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        
        setTitle("계좌 정보");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 메인 창이므로 종료 시 프로세스도 닫히도록 변경
        setBounds(200, 200, 560, 420); // 가로 폭을 넓힘
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        setupComponents();
        populateData(userId, name, address, accountNumber, balance);
    }

    private void setupComponents() {
        // ... (기존 컴포넌트 설정)
        JLabel lblName = new JLabel("이름 :");
        lblName.setFont(new Font("굴림", Font.PLAIN, 14));
        lblName.setBounds(30, 30, 100, 25);
        contentPane.add(lblName);
        setTfName(new JTextField());
        getTfName().setEditable(false);
        getTfName().setBounds(140, 30, 220, 25);
        contentPane.add(getTfName());

        JLabel lblAccountNumber = new JLabel("계좌번호 :");
        lblAccountNumber.setFont(new Font("굴림", Font.PLAIN, 14));
        lblAccountNumber.setBounds(30, 80, 100, 25);
        contentPane.add(lblAccountNumber);
        tfAccountNumber = new JTextField();
        tfAccountNumber.setEditable(false);
        tfAccountNumber.setBounds(140, 80, 220, 25);
        contentPane.add(tfAccountNumber);
        
        JLabel lblBalance = new JLabel("잔액 :");
        lblBalance.setFont(new Font("굴림", Font.PLAIN, 14));
        lblBalance.setBounds(30, 130, 100, 25);
        contentPane.add(lblBalance);
        tfBalance = new JTextField();
        tfBalance.setEditable(false);
        tfBalance.setBounds(140, 130, 220, 25);
        contentPane.add(tfBalance);
        
        JLabel lblAddress = new JLabel("주소 :");
        lblAddress.setFont(new Font("굴림", Font.PLAIN, 14));
        lblAddress.setBounds(30, 180, 100, 25);
        contentPane.add(lblAddress);
        tfAddress = new JTextField();
        tfAddress.setEditable(false);
        tfAddress.setBounds(140, 180, 220, 25);
        contentPane.add(tfAddress);

        JLabel lblUserId = new JLabel("아이디 :");
        lblUserId.setFont(new Font("굴림", Font.PLAIN, 14));
        lblUserId.setBounds(30, 230, 100, 25);
        contentPane.add(lblUserId);
        tfUserId = new JTextField();
        tfUserId.setEditable(false);
        tfUserId.setBounds(140, 230, 220, 25);
        contentPane.add(tfUserId);

        // --- 버튼 레이아웃 수정 ---
        JButton btnDeposit = new JButton("입금");
        btnDeposit.addActionListener(e -> {
            TransactionFrame depositFrame = new TransactionFrame(this, currentAccountNumber, "deposit");
            depositFrame.setVisible(true);
        });
        btnDeposit.setFont(new Font("굴림", Font.BOLD, 16));
        btnDeposit.setBounds(30, 290, 100, 50);
        contentPane.add(btnDeposit);

        JButton btnWithdraw = new JButton("출금");
        btnWithdraw.addActionListener(e -> {
            TransactionFrame withdrawFrame = new TransactionFrame(this, currentAccountNumber, "withdraw");
            withdrawFrame.setVisible(true);
        });
        btnWithdraw.setFont(new Font("굴림", Font.BOLD, 16));
        btnWithdraw.setBounds(150, 290, 100, 50);
        contentPane.add(btnWithdraw);
        
        JButton btnTransfer = new JButton("이체");
        btnTransfer.addActionListener(e -> {
            TransferFrame transferFrame = new TransferFrame(this, currentAccountNumber);
            transferFrame.setVisible(true);
        });
        btnTransfer.setFont(new Font("굴림", Font.BOLD, 16));
        btnTransfer.setBounds(270, 290, 100, 50);
        contentPane.add(btnTransfer);
        
        // --- 거래내역 버튼 추가 ---
        JButton btnHistory = new JButton("거래내역");
        btnHistory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// HistoryFrame을 생성하여 보여준다.
        		HistoryFrame historyFrame = new HistoryFrame(currentAccountNumber);
        		historyFrame.setVisible(true);
        	}
        });
        btnHistory.setFont(new Font("굴림", Font.BOLD, 16));
        btnHistory.setBounds(390, 290, 120, 50);
        contentPane.add(btnHistory);
    }
    
    public String getSenderName() {
        return this.tfName.getText();
    }

    
    // ... (populateData, refreshBalance, updateBalance 메소드는 이전과 동일)
    private void populateData(String userId, String name, String address, String accountNumber, long balance) {
        tfUserId.setText(userId);
        getTfName().setText(name);
        tfAddress.setText(address);
        tfAccountNumber.setText(accountNumber);
        updateBalance(balance);
    }
    
    public void refreshBalance() {
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try {
            ResultSet rs = DB.executeQuery(sql, this.currentAccountNumber);
            if(rs != null && rs.next()) {
                long newBalance = rs.getLong("balance");
                updateBalance(newBalance);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "잔액 정보 갱신 중 오류 발생", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBalance(long balance) {
        NumberFormat formatter = NumberFormat.getInstance();
        tfBalance.setText(formatter.format(balance) + " 원");
    }

	public JTextField getTfName() {
		return tfName;
	}

	public void setTfName(JTextField tfName) {
		this.tfName = tfName;
	}
}
