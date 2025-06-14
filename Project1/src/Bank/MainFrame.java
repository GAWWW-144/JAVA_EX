package Bank;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

/**
 * 프로그램의 시작점입니다.
 * '회원가입'과 '로그인' 기능을 선택할 수 있습니다.
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * 애플리케이션을 실행합니다.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 프로그램 시작 시 DB 연결 초기화
                    DB.init();
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 프레임을 생성합니다.
     */
    public MainFrame() {
        setTitle("금융 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 280, 250);
        contentPane = new JPanel();
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 회원가입 버튼
        JButton btnSignUp = new JButton("회원가입");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 창을 새로 열어 보여줍니다.
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
            }
        });
        btnSignUp.setFont(new Font("돋움체", Font.BOLD, 18));
        btnSignUp.setBounds(50, 30, 160, 60);
        contentPane.add(btnSignUp);

        // 로그인 버튼
        JButton btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인 창을 새로 열어 보여줍니다.
                 LoginFrame loginFrame = new LoginFrame();
                 loginFrame.setVisible(true);
            }
        });
        btnLogin.setFont(new Font("돋움체", Font.BOLD, 18));
        btnLogin.setBounds(50, 110, 160, 60);
        contentPane.add(btnLogin);
    }
}
