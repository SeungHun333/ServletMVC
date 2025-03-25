package org.kim.container.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionProtocol {
    public Connection getConnection () throws SQLException, ClassNotFoundException;
    public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn);
}
