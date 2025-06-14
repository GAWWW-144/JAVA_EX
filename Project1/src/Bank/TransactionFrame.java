package Bank;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 입출금 화면입니다.
 * 성공 시 transactions 테이블에 거래 내역을 기록하는 기능이 추가되었습니다.
 */
public class TransactionFrame extends JFrame {

    // ... (기존 멤버 변수들)
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfAmount;
    private AccountFrame parentFrame;
    private String accountNumber;
    private String transactionType;

    public TransactionFrame(AccountFrame parentFrame, String accountNumber, String transactionType) {
        // ... (생성자 앞부분은 동일)
        this.parentFrame = parentFrame;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));

        String title = transactionType.equals("deposit") ? "입금" : "출금";
        setTitle(title);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 320, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAmount = new JLabel(title + " 금액 :");
        lblAmount.setFont(new Font("굴림", Font.PLAIN, 14));
        lblAmount.setBounds(20, 40, 100, 25);
        contentPane.add(lblAmount);

        tfAmount = new JTextField();
        tfAmount.setBounds(120, 40, 150, 25);
        contentPane.add(tfAmount);
        tfAmount.setColumns(10);

        JButton btnConfirm = new JButton("확인");
        btnConfirm.addActionListener(e -> processTransaction());
        btnConfirm.setFont(new Font("굴림", Font.BOLD, 16));
        btnConfirm.setBounds(90, 90, 120, 40);
        contentPane.add(btnConfirm);
    }
    
    private void processTransaction() {
        long amount;
        try {
            amount = Long.parseLong(tfAmount.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "거래 금액은 0보다 커야 합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "숫자만 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 현재 잔액 확인
            String selectSql = "SELECT balance FROM accounts WHERE account_number = ?";
            ResultSet rs = DB.executeQuery(selectSql, accountNumber);
            if (rs == null || !rs.next()) {
                JOptionPane.showMessageDialog(this, "계좌 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long currentBalance = rs.getLong("balance");

            // 거래 후 잔액 계산
            long newBalance;
            if (transactionType.equals("deposit")) {
                newBalance = currentBalance + amount;
            } else { // withdraw
                if (currentBalance < amount) {
                    JOptionPane.showMessageDialog(this, "잔액이 부족합니다.", "출금 실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                newBalance = currentBalance - amount;
            }

            // 1. 계좌 잔액 업데이트
            String updateSql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            int updateResult = DB.executeUpdate(updateSql, newBalance, accountNumber);

            if (updateResult > 0) {
                // 2. 거래 내역(transactions) 테이블에 기록
                String type = transactionType.equals("deposit") ? "입금" : "출금";
                String memo = type + " 완료";
                String insertHistorySql = "INSERT INTO transactions (account_number, transaction_type, amount, balance_after, memo) VALUES (?, ?, ?, ?, ?)";
                DB.executeUpdate(insertHistorySql, accountNumber, type, amount, newBalance, memo);

                JOptionPane.showMessageDialog(this, type + "이(가) 완료되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.refreshBalance();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "계좌 처리 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB 처리 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
