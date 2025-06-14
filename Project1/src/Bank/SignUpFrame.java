package Bank;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import java.util.Random;

/**
 * 고객이 이름, 주소, 아이디, 비밀번호를 입력하여 회원가입을 진행하는 화면입니다.
 * 회원가입과 동시에 중복되지 않는 새로운 계좌가 자동으로 생성됩니다.
 */
public class SignUpFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfUserId;
    private JPasswordField pfPassword;
    private JPasswordField pfPasswordConfirm;

    /**
     * 프레임을 생성합니다.
     */
    public SignUpFrame() {
        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 150, 400, 400);
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // UI 컴포넌트 설정
        setupComponents();
    }
    
    private void setupComponents() {
        JLabel lblName = new JLabel("이름 :");
        lblName.setFont(new Font("굴림", Font.PLAIN, 14));
        lblName.setBounds(40, 30, 100, 25);
        contentPane.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150, 30, 180, 25);
        contentPane.add(tfName);
        tfName.setColumns(10);
        
        JLabel lblAddress = new JLabel("주소 :");
        lblAddress.setFont(new Font("굴림", Font.PLAIN, 14));
        lblAddress.setBounds(40, 80, 100, 25);
        contentPane.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(150, 80, 180, 25);
        contentPane.add(tfAddress);
        tfAddress.setColumns(10);
        
        JLabel lblUserId = new JLabel("아이디 :");
        lblUserId.setFont(new Font("굴림", Font.PLAIN, 14));
        lblUserId.setBounds(40, 130, 100, 25);
        contentPane.add(lblUserId);

        tfUserId = new JTextField();
        tfUserId.setBounds(150, 130, 180, 25);
        contentPane.add(tfUserId);
        tfUserId.setColumns(10);

        JLabel lblPassword = new JLabel("비밀번호 :");
        lblPassword.setFont(new Font("굴림", Font.PLAIN, 14));
        lblPassword.setBounds(40, 180, 100, 25);
        contentPane.add(lblPassword);
        
        pfPassword = new JPasswordField();
        pfPassword.setBounds(150, 180, 180, 25);
        contentPane.add(pfPassword);

        JLabel lblPasswordConfirm = new JLabel("비밀번호 확인 :");
        lblPasswordConfirm.setFont(new Font("굴림", Font.PLAIN, 14));
        lblPasswordConfirm.setBounds(40, 230, 100, 25);
        contentPane.add(lblPasswordConfirm);
        
        pfPasswordConfirm = new JPasswordField();
        pfPasswordConfirm.setBounds(150, 230, 180, 25);
        contentPane.add(pfPasswordConfirm);

        JButton btnConfirm = new JButton("확인");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
        btnConfirm.setFont(new Font("굴림", Font.BOLD, 16));
        btnConfirm.setBounds(130, 290, 120, 40);
        contentPane.add(btnConfirm);
    }
    
    /**
     * 회원가입 로직을 처리하는 메소드
     */
    private void signUp() {
        String name = tfName.getText();
        String address = tfAddress.getText();
        String userId = tfUserId.getText();
        String password = new String(pfPassword.getPassword());
        String passwordConfirm = new String(pfPasswordConfirm.getPassword());

        if (name.isEmpty() || address.isEmpty() || userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 1. 중복되지 않는 새로운 계좌번호 생성
        String accountNumber = createUniqueAccountNumber();
        if (accountNumber == null) {
            JOptionPane.showMessageDialog(this, "계좌번호 생성 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 2. users 테이블에 고객 정보 삽입 (비밀번호 포함)
        String userSql = "INSERT INTO users (id, password, name, address, account_number) VALUES (?, ?, ?, ?, ?)";
        int userResult = DB.executeUpdate(userSql, userId, password, name, address, accountNumber);
        
        if (userResult > 0) {
            // 3. accounts 테이블에 계좌 정보 삽입
            String accountSql = "INSERT INTO accounts (account_number, balance, user_id) VALUES (?, ?, ?)";
            int accountResult = DB.executeUpdate(accountSql, accountNumber, 0, userId);
            
            if(accountResult > 0) {
                JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.\n생성된 계좌번호: " + accountNumber, "성공", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // 성공 시 현재 창 닫기
            } else {
                 JOptionPane.showMessageDialog(this, "계좌 생성에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디이거나 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 데이터베이스를 확인하여 중복되지 않는 유니크한 계좌번호를 생성합니다.
     */
    private String createUniqueAccountNumber() {
        Random rand = new Random();
        String newAccountNumber;

        while (true) {
            int middle = rand.nextInt(900) + 100;
            int last = rand.nextInt(900000) + 100000;
            newAccountNumber = "110-" + middle + "-" + last;

            String sql = "SELECT COUNT(*) FROM accounts WHERE account_number = ?"; //DB
            try (ResultSet rs = DB.executeQuery(sql, newAccountNumber)) {
                if (rs != null && rs.next()) {
                    if (rs.getInt(1) == 0) {
                        return newAccountNumber;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
