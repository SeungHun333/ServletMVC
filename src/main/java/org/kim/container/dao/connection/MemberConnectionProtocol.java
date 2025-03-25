package org.kim.container.dao.connection;

import java.sql.*;

public class MemberConnectionProtocol implements ConnectionProtocol {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "hun", "3469");
    }

    @Override
    public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
