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

/**
 * 아이디와 비밀번호를 입력하여 로그인하는 화면입니다.
 */
public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUserId;
    private JPasswordField pfPassword;

    /**
     * 프레임을 생성합니다.
     */
    public LoginFrame() {
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 150, 350, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));

        JLabel lblUserId = new JLabel("아이디 :");
        lblUserId.setFont(new Font("굴림", Font.PLAIN, 14));
        lblUserId.setBounds(40, 40, 80, 25);
        contentPane.add(lblUserId);

        tfUserId = new JTextField();
        tfUserId.setBounds(130, 40, 150, 25);
        contentPane.add(tfUserId);
        tfUserId.setColumns(10);
        
        JLabel lblPassword = new JLabel("비밀번호 :");
        lblPassword.setFont(new Font("굴림", Font.PLAIN, 14));
        lblPassword.setBounds(40, 90, 80, 25);
        contentPane.add(lblPassword);
        
        pfPassword = new JPasswordField();
        pfPassword.setBounds(130, 90, 150, 25);
        contentPane.add(pfPassword);

        JButton btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnLogin.setFont(new Font("굴림", Font.BOLD, 16));
        btnLogin.setBounds(100, 150, 120, 40);
        contentPane.add(btnLogin);
    }
    
    /**
     * 로그인(계좌 조회) 로직을 처리하는 메소드
     */
    private void login() {
        String userId = tfUserId.getText();
        String password = new String(pfPassword.getPassword());

        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // users와 accounts 테이블을 JOIN하고, 아이디와 비밀번호가 일치하는지 확인합니다.
        String sql = "SELECT u.name, u.address, u.id, a.account_number, a.balance "
                   + "FROM users u "
                   + "JOIN accounts a ON u.account_number = a.account_number "
                   + "WHERE u.id = ? AND u.password = ?";
        
        ResultSet rs = DB.executeQuery(sql, userId, password);
        
        try {
            if (rs != null && rs.next()) {
                // 로그인 성공 시 결과 데이터를 AccountFrame으로 전달하여 보여줍니다.
                String name = rs.getString("name");
                String address = rs.getString("address");
                String id = rs.getString("id");
                String accountNumber = rs.getString("account_number");
                long balance = rs.getLong("balance");

                AccountFrame accountFrame = new AccountFrame(id, name, address, accountNumber, balance);
                accountFrame.setVisible(true);
                
                dispose(); // 현재 로그인 창 닫기
                
            } else {
                JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 조회 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
