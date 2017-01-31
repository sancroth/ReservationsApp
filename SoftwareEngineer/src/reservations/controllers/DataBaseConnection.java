package reservations.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection {

	static final String dbConnectionURL="jdbc:mysql://localhost:3306/reservations";
 	static final String dbUsername="root";
 	static final String dbPassword="5389";
	
 	
 	public static Connection getDBConnection(){
 			
 	 		try{
 	 			
 				Class.forName("com.mysql.jdbc.Driver").newInstance();
 				System.out.println("Connecting to database...");
 				//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
 				Connection conn=DriverManager.getConnection(dbConnectionURL, dbUsername, dbPassword);
 				conn.setAutoCommit(false);
 				System.out.println("Creating statement...");
 				
 		
 				return conn;
 	 		}catch(Exception se){
 			     //Handle errors for JDBC
 			     se.printStackTrace();
 			}
			return null;
 	}

	


 	public static Statement getDBStatement(Connection conn){
 		
 		try{
 			
 			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 			return stmt;
 			
 		}catch(Exception se){
	      //Handle errors for JDBC
	      se.printStackTrace();
 		}
 		return null;
 	}
 	
 	
 	
 	
 	public static ResultSet getDBResultSet(Statement stmt, String rsQuery){
 	
 		try{
			ResultSet rs=stmt.executeQuery(rsQuery);
 			return rs;
 		}catch(Exception se){
 			//Handle errors for JDBC
 			se.printStackTrace();
		}
		return null;
 	}
 	
 	
 	
 	
 	public static void dbClose(Connection conn, Statement stmt, ResultSet rs){
 		
 		try{
 			
 			rs.close();
		    stmt.close();
		    conn.close();
		    System.out.println("DataBase Connection closed successfully!");
 		}catch(Exception se){
 			//Handle errors for JDBC
 			se.printStackTrace();
		}
 	}
 	
 	
 	
}
