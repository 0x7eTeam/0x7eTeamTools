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

public class MySQLController {


    public static boolean connect(String ip, String  port, String username, String password) {
        // JDBC连接URL
        String url = "jdbc:mysql://" + ip + ":" + port;

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

    public static String[] getDBNAME(String ip, String  port, String username, String password) {
        // JDBC连接URL
        String url = "jdbc:mysql://" + ip + ":" + port;

        // 设置 SOCKS 代理
        Tools.setProxy();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();
            List<String> databases = new ArrayList<>();
            while (resultSet.next()) {
                String dbName = resultSet.getString("TABLE_CAT");
                databases.add(dbName);
            }
            return databases.toArray(new String[0]);
        } catch (SQLException e) {
            return new String[0];
        }
    }

    public static String[] getTbaleName(String ip, String  port, String username, String password,String db_name) {
        // JDBC连接URL
        String url = "jdbc:mysql://" + ip + ":" + port;

        // 设置 SOCKS 代理
        Tools.setProxy();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(db_name, null, null, new String[]{"TABLE"});

            List<String> tableNames = new ArrayList<>();
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
            connection.close();
            return tableNames.toArray(new String[0]);
        } catch (SQLException e) {
            return new String[0];
        }
    }
    public static String getTableData(String ip, String  port, String username, String password, String databaseName, String tableName) {
        String url = "jdbc:mysql://" + ip + ":" + port;
        // 设置 SOCKS 代理
        Tools.setProxy();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + databaseName + "." + tableName);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder tableData = new StringBuilder();
            int rowCount = 0;
            while (rs.next() && rowCount < 5000) {
                for (int i=1; i<=columnCount; i++) {
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

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String executeSQL(String ip, String  port, String username, String password, String databaseName, String sql) {
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;
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
