package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/my_db";
        final String LOGIN = "root";
        final String PASSWORD = "pass";

        final String table1 = "employees";

        final String query1 = "SELECT * FROM ";

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            ResultSet results = statement.executeQuery(query1 + table1);
            showData(results);
            System.out.println();
            ResultSetMetaData metaData = results.getMetaData();
            showMetaData(metaData);

        } catch (SQLException e) {
            System.out.println("Exception " + e);
//            throw new RuntimeException(e);
        }
    }
    
    public static void showData(ResultSet results) throws SQLException {
        while (results.next()) {
            int id = results.getInt(1);
            String name = results.getString(2);
            String surname = results.getString(3);
            String department = results.getString(4);
            int salary = results.getInt(5);
            int row = results.getRow();

            System.out.println(
                    row + " " +
                            id + " " +
                            name + " " +
                            surname + " " +
                            department + " " +
                            salary
            );
        }
    }

    public static void showMetaData(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++)
        {
            String name = metaData.getColumnName(column);
            String className = metaData.getColumnClassName(column);
            String typeName = metaData.getColumnTypeName(column);
            int type = metaData.getColumnType(column);

            System.out.println(name + " " + className + " " + typeName + " " + type);
        }
    }
}