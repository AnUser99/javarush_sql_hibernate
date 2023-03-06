package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

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

            while (results.next()) {
                int id = results.getInt(1);
                String name = results.getString(2);
                String surname = results.getString(3);
                String department = results.getString(4);
                int salary = results.getInt(5);
                int row = results.getRow();

                System.out.println(
                        row + "\t" +
                        id + "\t" +
                        name + "\t" +
                        surname + "\t" +
                        department + "\t" +
                        salary
                );
            }
        } catch (SQLException e) {
            System.out.println("Exception " + e);
//            throw new RuntimeException(e);
        }
    }
}