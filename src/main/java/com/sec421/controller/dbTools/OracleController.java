package com.sec421.controller.dbTools;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import com.sec421.tools.Tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleController {


    public static boolean connect(String ip, String  port, String username, String password,String serviceName) {
        // JDBC连接URL
        String url = "jdbc:oracle:thin:@" + ip + ":" + port + "/" + serviceName;

        // 设置 SOCKS 代理
        Tools.setProxy();
        // 连接MySQL数据库
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // 如果连接成功，返回true
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static String[] getTbaleName(String ip, String  port, String username, String password,String serviceName) {
        List<String> tableNames = new ArrayList<>();
        // JDBC连接URL
        String url = "jdbc:oracle:thin:@" + ip + ":" + port + "/" + serviceName;

        // 设置 SOCKS 代理
        Tools.setProxy();
        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 获取当前用户的表
            DatabaseMetaData metaData = connection.getMetaData();
            String[] tableTypes = {"TABLE"};
            ResultSet tables = metaData.getTables(null, username.toUpperCase(), null, tableTypes);

            // 打印当前用户的表名
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
            // 获取除当前用户以外的表
            tables = metaData.getTables(null, null, null, tableTypes);
            // 打印除当前用户以外的表名
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
            // 关闭连接
            connection.close();
            return tableNames.toArray(new String[0]);
        } catch (SQLException e) {
            return new String[0];
        }

    }

    public static String getTableData(String ip, String  port, String username, String password, String serviceName, String tableName) {
        String url = "jdbc:oracle:thin:@" + ip + ":" + port + "/" + serviceName;
        // 设置 SOCKS 代理
        Tools.setProxy();
        try {
            // 建立数据库连接
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // 查询指定表的数据
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder tableData = new StringBuilder();
            int rowCount = 0;
            while (rs.next() && rowCount < 5000) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);

                    tableData.append(columnName).append(": ").append(columnValue).append("\n");
                }
                tableData.append("\n");
                rowCount++;
            }

            conn.close();

            if (rowCount < 5000) {
                return tableData.toString();
            } else {
                return "数据量过大，只返回前5000条记录。\n" + tableData.toString();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String executeSQL(String ip, String  port, String username, String password, String serviceName, String sql) {
        String url = "jdbc:oracle:thin:@" + ip + ":" + port + "/" + serviceName;
        // 设置 SOCKS 代理
        Tools.setProxy();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            boolean isResultSet = stmt.execute(sql);

            if (isResultSet) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                StringBuilder tableData = new StringBuilder();
                int rowCount = 0;
                while (rs.next() && rowCount < 5000) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String columnValue = rs.getString(i);

                        tableData.append(columnName).append(": ").append(columnValue).append("\n");
                    }
                    tableData.append("\n");
                    rowCount++;
                }

                conn.close();
                if (rowCount < 5000) {
                    return tableData.toString();
                } else {
                    return "数据量过大，只返回前5000条记录。" + tableData.toString();
                }
            } else {
                int updateCount = stmt.getUpdateCount();
                conn.close();

                return "操作成功，受影响的记录数: " + updateCount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "操作失败：" + e.getMessage();
        }
    }
}
