package Bank;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * 특정 계좌의 거래 내역을 테이블 형태로 보여주는 새로운 프레임입니다.
 */
public class HistoryFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private String accountNumber;

    /**
     * 프레임을 생성합니다.
     * @param accountNumber 조회할 계좌번호
     */
    public HistoryFrame(String accountNumber) {
        this.accountNumber = accountNumber;
        
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        setTitle(accountNumber + " 거래내역");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 700, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // 테이블 모델(데이터 관리)과 테이블(화면 표시) 생성
        // 이것이 바로 MVC(Model-View-Controller) 패턴의 Model과 View를 분리하는 과정입니다.
        String[] columnNames = {"거래일시", "거래유형", "거래금액", "거래후잔액", "메모"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            // 테이블의 셀을 직접 수정할 수 없도록 설정
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        table = new JTable(tableModel);
        
        // JTable을 JScrollPane에 넣어야 스크롤이 가능하고, 컬럼 헤더가 보입니다.
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // 테이블의 각 컬럼 너비 설정
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        // DB에서 거래 내역을 로드하여 테이블에 채운다.
        loadTransactionHistory();
    }
    
    /**
     * DB에서 거래 내역을 조회하고 JTable에 데이터를 채우는 메소드입니다.
     */
    private void loadTransactionHistory() {
        String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";
        
        try (ResultSet rs = DB.executeQuery(sql, this.accountNumber)) {
            
            // 날짜와 숫자 포맷 준비
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            
            while (rs.next()) {
                Object[] rowData = {
                    dateFormat.format(rs.getTimestamp("transaction_date")),
                    rs.getString("transaction_type"),
                    numberFormat.format(rs.getLong("amount")) + " 원",
                    numberFormat.format(rs.getLong("balance_after")) + " 원",
                    rs.getString("memo")
                };
                // 모델에 데이터 행(row)을 추가하면, JTable 뷰가 자동으로 갱신됩니다.
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "거래 내역 조회 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
