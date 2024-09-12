package com.sdnext.hibernate.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementExample 
{
	
	/*
	 git fetch origin master 
	 git merge origin/master --allow-unrelated-histories
	 git push origin master
	 
	 */

	
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/aaa";
        String userName = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcURL, userName, password)) {
            String sql = "SELECT * FROM student WHERE firstname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "aa vvv");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String fullName = resultSet.getString("fullname");
                System.out.println("FirstName: " + firstName + " LastName: " + lastName + " FullName: " + fullName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
