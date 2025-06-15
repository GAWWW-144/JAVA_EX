package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQL 데이터베이스 연결 및 쿼리 실행을 담당하는 유틸리티 클래스입니다.
 * MainFrame이 실행될 때 init() 메소드가 호출되어 DB 연결을 설정합니다.
 */
public class DB {

    // 모든 클래스에서 공유할 Connection 객체
    private static Connection conn;

    /**
     * 데이터베이스 연결을 초기화합니다.
     */
    public static void init() {
        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 데이터베이스 연결
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank_db?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8",
                    "root", // MySQL 사용자 이름
                    "rootroot"  // MySQL 비밀번호
            );
            System.out.println("DB 연결 성공!!");

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("DB 연결 중 SQL 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    /**
     * SELECT 쿼리를 실행하고 결과를 ResultSet으로 반환합니다.
     * @param sql 실행할 SELECT SQL 문
     * @param params SQL의 '?'에 바인딩될 파라미터
     * @return 쿼리 결과인 ResultSet 객체
     */
    public static ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * INSERT, UPDATE, DELETE 쿼리를 실행합니다.
     * @param sql 실행할 INSERT, UPDATE, DELETE SQL 문
     * @param params SQL의 '?'에 바인딩될 파라미터
     * @return 영향을 받은 행(row)의 수
     */
    public static int executeUpdate(String sql, Object... params) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setParams(pstmt, params);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * PreparedStatement에 파라미터를 설정하는 보조 메소드입니다.
     * @param pstmt 파라미터를 설정할 PreparedStatement 객체
     * @param params 설정할 파라미터 목록
     * @throws SQLException
     */
    private static void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
}
