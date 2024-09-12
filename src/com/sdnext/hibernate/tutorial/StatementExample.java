package com.sdnext.hibernate.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementExample {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/aaa";
        String userName = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcURL, userName, password)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String fullName = resultSet.getString("fullname");
                System.out.println("FirstName: " + firstName + " LastName: " + lastName + " FullName: " + fullName);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
