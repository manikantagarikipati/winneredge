package com.winneredge.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.winneredge.model.User;

public class Dao {


	   
	   public Connection openConnection()
	   {
		   Connection connection = null;
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/WinnerEdge_DB", "root", "root");
		   	} 
		   catch (SQLException e)
		   {
			e.printStackTrace();
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   return connection;
	   }
	   
	   public void closeConnection(Connection connection)
	   {
		   
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
		 
	   
	public boolean registerUser(User user)
	{
		
		String name = user.getUserName();
		String email = user.getEmail();
		String password = user.getPassword();
		
		Connection connection = openConnection();
		if(connection!=null){
		try {
			Statement stmt = connection.createStatement();
			String CREATE_USER = "INSERT INTO USER(USERNAME,PASSWORD,EMAIL) VALUES('"+name+"','"+password+"','"+email+"')";
			stmt.executeUpdate(CREATE_USER);
			closeConnection(connection);
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		}
		return false;
	}

	public boolean validateUser(String username, String password) {
		
		Connection connection = openConnection();
		
		if(connection!= null){
			try {
				Statement stmt = connection.createStatement();
				String	SQL="SELECT USERNAME FROM USER WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'";
				ResultSet rs = stmt.executeQuery(SQL);
				rs.next();
				String obtained_username = rs.getString("USERNAME");
				if(obtained_username.equalsIgnoreCase(obtained_username)){
				return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}
}
