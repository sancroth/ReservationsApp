package reservations.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonnelManagement {
	

	
	Connection connection = null;
	Statement statement =null;
	ResultSet resultset=null;
	String query=null;
	Boolean checkFlag=false;
	
 	private  String name;
 	private  String password;
 	private  int admin;
 	private  int editedUserId;
 	private  int eventEditUser;
 	
	
 	public PersonnelManagement(String name , String pass, int admin, int editId,int editEvent){
 		
 		this.name = name.trim();
 		this.password = pass.trim();
 		this.admin= admin;
 		this.editedUserId = editId;
 		this.eventEditUser = editEvent;
 		
 	}
 	
 	
 	public Boolean addPersonnel(){		
		
		query="SELECT username FROM login";
 		connection=DataBaseConnection.getDBConnection();
 		statement=DataBaseConnection.getDBStatement(connection);
 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 		Boolean added = false;
 		
 		try{
 			
 			while(resultset.next() && checkFlag==false){
				String username=resultset.getString("username");
				if(name.equals(username)){
					checkFlag=true;
				}
			}
			if(!checkFlag){
				query="INSERT INTO login (username, password, isAdmin) VALUES ('"+name+"','"+password+"','"+admin+"')";
				statement.executeUpdate(query);
				connection.commit();
				added = true;
				System.out.println("Account created successfully.");
				
			}else{
				System.out.println("Username already in use");
			}
			DataBaseConnection.dbClose(connection, statement, resultset);
			
 		}catch(SQLException se){
 			//Handle errors for JDBC
	        se.printStackTrace();
	        // If there is an error then rollback the changes.
	        System.out.println("Rolling back data here....");
		    try{
		    	if(connection!=null)
		    		connection.rollback();
		    }catch(SQLException se2){
	         se2.printStackTrace();
		    }//end try
 			}catch(Exception e){
 				//Handle errors for Class.forName
 				e.printStackTrace();
 			}finally{
 				//finally block used to close resources
 				try{
 				if(statement!=null)
 					statement.close();
 				}catch(SQLException se2){
 				}// nothing we can do
 				try{
 					if(connection!=null)
 						connection.close();
 				}catch(SQLException se){
 					se.printStackTrace();
 				}//end finally try
 			}//end try
 		return added;
 		}
 		
 		public void deletePersonnel(){
		
 			query="SELECT userId FROM login";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		
 	 		try{
 	 			
 	 			while(resultset.next() && checkFlag==false){
					int id=Integer.parseInt(resultset.getString("userId"));
					if(editedUserId==id){
						checkFlag=true;
					}
				}
				if(checkFlag){
					query="DELETE FROM login WHERE userId = '"+editedUserId+"'";
					statement.executeUpdate(query);
					connection.commit();
					System.out.println("User was deleted successfully.");
					
				}else{
					System.out.print("The user with the id :");System.out.print(editedUserId);System.out.print("doesn't excist!");System.out.println();
				}
				DataBaseConnection.dbClose(connection,statement,resultset);
 	 		
 	 		}catch(SQLException se){
 	 			//Handle errors for JDBC
 		        se.printStackTrace();
 		        // If there is an error then rollback the changes.
 		        System.out.println("Rolling back data here....");
 			    try{
 			    	if(connection!=null)
 			    		connection.rollback();
 			    }catch(SQLException se2){
 		         se2.printStackTrace();
 			    }//end try
 	 			}catch(Exception e){
 	 				//Handle errors for Class.forName
 	 				e.printStackTrace();
 	 			}finally{
 	 				//finally block used to close resources
 	 				try{
 	 				if(statement!=null)
 	 					statement.close();
 	 				}catch(SQLException se2){
 	 				}// nothing we can do
 	 				try{
 	 					if(connection!=null)
 	 						connection.close();
 	 				}catch(SQLException se){
 	 					se.printStackTrace();
 	 				}//end finally try
 	 			}//end try
 			
 		}
 	
 		public int editPersonnel(){
 			
 			query="SELECT userId FROM login";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		
 	 		try{
 	 			
 	 			switch(eventEditUser){
 	 			
 	 			case 1://change username
 	 				
 	 					while(resultset.next() && checkFlag==false){
	 						int id=Integer.parseInt(resultset.getString("userId"));
	 						if(editedUserId==id){
	 							checkFlag=true;
	 						}
	 					}
	 					if(checkFlag){
	 						
	 						Boolean usernameFlag=false;
	 						query="SELECT username FROM login";
	 						resultset=DataBaseConnection.getDBResultSet(statement, query);
	 						while(resultset.next()){
	 							String dbUsername=resultset.getString("username");
	 							if(name.equals(dbUsername)){
	 								usernameFlag=true;
	 							}
	 						}
	 						
	 						
	 						if(!usernameFlag){
	 							query="UPDATE login SET username='"+name+"' WHERE userId='"+editedUserId+"'";
	 							statement.executeUpdate(query);
	 							connection.commit();
	 							System.out.println("Username Changed.");
	 			 				DataBaseConnection.dbClose(connection, statement, resultset);	
	 							return 1;
	 						}else{
	 							System.out.println("Username already in use");
	 						}
				
	 					}else{
	 						System.out.print("The user with the id :");System.out.print(editedUserId);System.out.print("doesn't excist!");System.out.println();
	 					}
	 					
	 					break;
 	 			
 	 			
 	 			case 2://change password
 	 			
 	 					while(resultset.next() && checkFlag==false){
 	 						int id=Integer.parseInt(resultset.getString("userId"));
 	 						if(editedUserId==id){
 	 							checkFlag=true;
 	 						}
 	 					}
 	 					if(checkFlag){
 	 						query="UPDATE login SET password='"+password+"' WHERE userId='"+editedUserId+"'";
 	 						statement.executeUpdate(query);
 	 						connection.commit();
 	 						System.out.println("Password Changed.");
					
 	 					}else{
 	 						System.out.print("The user with the id :");System.out.print(editedUserId);System.out.print("doesn't excist!");System.out.println();
 	 					}
 	 					break;
 	 			
 	 			case 3:
 	 					if(admin==0 || admin==1){
 	 						while(resultset.next() && checkFlag==false){
 	 							int id=Integer.parseInt(resultset.getString("userId"));
 	 							if(editedUserId==id){
 	 								checkFlag=true;
 	 							}
 	 						}
 	 						if(checkFlag){
 	 							query="UPDATE login SET isAdmin='"+admin+"' WHERE userId='"+editedUserId+"'";
 	 							statement.executeUpdate(query);
 	 							connection.commit();
 	 							System.out.println("Privileges Changed.");
				
 	 						}else{
 	 							System.out.print("The user with the id :");System.out.print(editedUserId);System.out.print("doesn't excist!");System.out.println();
 	 						}
 	 					}else{
 	 						System.out.println("Wrong privilage.");
 	 					}
	 					break;
 	 			
	 			default:
	 				System.out.println("Error, unknown eventEditUser provided.");
 	 				break;
 	 				
 	 			}
 				DataBaseConnection.dbClose(connection, statement, resultset);	
 	 		
 	 		}catch(SQLException se){
 	 			//Handle errors for JDBC
 		        se.printStackTrace();
 		        // If there is an error then rollback the changes.
 		        System.out.println("Rolling back data here....");
 			    try{
 			    	if(connection!=null)
 			    		connection.rollback();
 			    }catch(SQLException se2){
 		         se2.printStackTrace();
 			    }//end try
 	 			}catch(Exception e){
 	 				//Handle errors for Class.forName
 	 				e.printStackTrace();
 	 			}finally{
 	 				//finally block used to close resources
 	 				try{
 	 				if(statement!=null)
 	 					statement.close();
 	 				}catch(SQLException se2){
 	 				}// nothing we can do
 	 				try{
 	 					if(connection!=null)
 	 						connection.close();
 	 				}catch(SQLException se){
 	 					se.printStackTrace();
 	 				}//end finally try
 	 			}//end try
			return 0;
 			
 		}
 		
 		public ObservableList<Personel> printPersonnel(){
 			
 			Connection connection = null;
 			Statement statement =null;
 			ResultSet resultset=null;
 			String query=null;
 			
 			
 			query="SELECT * FROM login";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 	    ObservableList<Personel> personel = FXCollections.observableArrayList();
 	 		try{	
 	 			while(resultset.next()){
 	 				int userId=resultset.getInt("userId");
 	 				String username=resultset.getString("username");
 	 				String password=resultset.getString("password");
 	 				//int admin=resultset.getInt("isAdmin");
 	 				Personel user = new Personel(username,password,userId);
 	 				//System.out.println(user.firstNameProperty().get());
 	 				personel.add(user);
 	 				//System.out.println("|"+userId + "|"+username + "|" +password + "|" +admin );
 	 			}
 	 			DataBaseConnection.dbClose(connection, statement, resultset);	
 	 		}catch (Exception se){
 	 			se.printStackTrace();
 	 		}
 	 		//System.out.println(personel.size());
			return personel;
 	 			
 	 	}

}
