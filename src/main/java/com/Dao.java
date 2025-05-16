package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    public void db_conn() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/web_alarm", "root", "1234");

            if (conn != null) {
                System.out.println("DB SUCCESS");
            } else {
                System.out.println("DB FAILED");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void db_close() {
        try {
            // ResultSet, PreparedStatement, Connection 객체가 null이 아니면 close() 호출
            if (rs != null)
                rs.close();
            if (psmt != null)
                psmt.close();
            if (conn != null)
                conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            System.out.println("DB END");
        }
    }

    public BaseMap select(String query) {
        System.out.println(query);
        BaseMap result = new BaseMap();

        try {
            psmt = conn.prepareStatement(query);

            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i); // 또는 getColumnName(i)
                    Object value = rs.getObject(i);
                    result.set(columnName, value);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<BaseMap> selectAll(String query) {
        System.out.println(query);
        List<BaseMap> result = new ArrayList<BaseMap>();
        try {
            psmt = conn.prepareStatement(query);

            ResultSet rs = psmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                BaseMap row = new BaseMap();
                for(int i = 1; i <= columnCount; i++) {
                    row.set(metaData.getColumnLabel(i), rs.getObject(i));
                }
                result.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
