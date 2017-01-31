package reservations.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogin {
	
	Connection connection = null;
	Statement statement =null;
	ResultSet resultset=null;
	String query=null;
	
	private String username;
	private String password;
	
	public UserLogin(String logInUsername, String logInPassword){
		
		this.username = logInUsername.trim();
		this.password = logInPassword.trim();
	}

	public Boolean connectUser(){
		
 		Boolean logInEstablished=false;
 		int isAdmin=-1;
 		int authFlag=0;
		int loggedId=0;
			
		
 	 	connection=DataBaseConnection.getDBConnection();
 	 	statement=DataBaseConnection.getDBStatement(connection);
 	 	query="SELECT username,userId FROM login";
 	 	resultset=DataBaseConnection.getDBResultSet(statement, query);
			
 	 	try{

			while(resultset.next()){
				String dbUsername=resultset.getString("username");
				if(username.equals(dbUsername)){
					loggedId=Integer.parseInt(resultset.getString("userId"));
					authFlag++;

				}
			}
			if(authFlag==0 || authFlag>1){
				
				System.out.println("Incorrect username.");
				
			}else{
				query="SELECT password, isAdmin FROM login WHERE userId = '"+loggedId+"'";
		 	 	resultset=DataBaseConnection.getDBResultSet(statement, query);

				while(resultset.next()){
					String dbPassword=resultset.getString("password");
					isAdmin=Integer.parseInt(resultset.getString("isAdmin"));
					if(password.equals(dbPassword)){
						logInEstablished=true;
						System.out.println("Log in successful.");
						if(isAdmin==0){
							System.out.print("Welcome User: ");System.out.print(username);System.out.println();
						}else{
							System.out.print("Welcome Admin: ");System.out.print(username);System.out.println();
						}
					}else{
						System.out.println("Incorrect password.");
					}
				}
				
			}
			
			//authFlag=0;
			//logInEstablished=false;
			DataBaseConnection.dbClose(connection, statement, resultset);	
			
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
		return logInEstablished;
	}
}
