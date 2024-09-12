package com.sdnext.hibernate.tutorial;

import java.sql.DriverManager;

import java.sql.*;

public class HibernateTestDemo
{
	
	public static void main(String[] args) 
	{
		//Db details
		String jdbcURL = "jdbc:mysql://localhost:3306/aaa";
		String userName = "root";
		String password = "root";
		
		try 
		{
			//Establishing connection
			
			Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
			
			System.out.println("connected to the database");
			
//			creating a statement
			Statement statement = connection.createStatement();
			
			String sql = "SELECT * FROM student s;;";
//			String sql = "select * from demo;";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
//			processing the result set
			while(resultSet.next())
			{
//				int id = resultSet.getInt("id");
//				String name = resultSet.getString("name");
//				
//				System.out.println("ID "+id +" Name: "+name);
				
				String name1 = resultSet.getString("firstname");
				String name2 = resultSet.getString("lastname");
				String name3 = resultSet.getString("fullname");
				
				System.out.println("FirstName : "+name1 +" LastName :  "+name2+ " FullName "+name3);
				
			}
			
//			closing the connection
			connection.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
