package reservations.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomManagement {
	
	Connection connection = null;
	Statement statement =null;
	ResultSet resultset=null;
	String query=null;
	Boolean checkFlag=false;
	
	
	
 	private  String roomName;
 	private  float roomDefaultPrice;
 	private  float roomCurrentPrice;
	private  int numberOfRooms;
	private  int numberOfSingleBeds;
	private  int numberOfDoubleBeds;
	private  int hasBalcony;
	private  int hasBreakfast;
	private  int isLuxury;
	private  int editedRoomId;
	private  int eventEditRoom;
	private  int roomSale;
	private  Boolean saleOption;
	private  int availability;
	
 	public RoomManagement(String name , float price, float currentPrice, int roomNum, int singleBedNum,int doubleBedNum, int balcony, int breakfast, int luxury, int eventId, int editEvent, int sale, Boolean saleOption, int isAvailable){
 		
 	    this.roomName = name.trim();
 		this.roomDefaultPrice = price;
 		this.roomCurrentPrice = currentPrice;
 		this.numberOfRooms = roomNum;
 		this.numberOfSingleBeds= singleBedNum;
 		this.numberOfDoubleBeds = doubleBedNum;
 		this.hasBalcony = balcony;
 		this.hasBreakfast = breakfast;
 		this.isLuxury = luxury;
 		this.editedRoomId = eventId;
 		this.eventEditRoom = editEvent;
 		this.roomSale = sale;
 		this.saleOption = saleOption;
 		this.availability = isAvailable;

 	}
 	
 	
 	public void addRoom(){		
		
		query="SELECT roomName FROM rooms";
 		connection=DataBaseConnection.getDBConnection();
 		statement=DataBaseConnection.getDBStatement(connection);
 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 		
 		try{
 			
 			while(resultset.next() && checkFlag==false){
				String name=resultset.getString("roomName");
				if(roomName.equals(name)){
					checkFlag=true;
				}
			}
			if(!checkFlag){
				query="INSERT INTO rooms (roomName, defaultPrice, currentPrice, numOfRooms, numOfSingleBeds, numOfDoubleBeds, hasBalcony, hasBreakfast, isLuxury, isAvailable) "
						+ "VALUES ('"+roomName+"','"+roomDefaultPrice+"', '"+roomCurrentPrice+"', '"+numberOfRooms+"','"+numberOfSingleBeds+"','"+numberOfDoubleBeds+"','"+hasBalcony+"','"+hasBreakfast+"','"+isLuxury+"', '0')";
				statement.executeUpdate(query);
				connection.commit();
				System.out.println("Room registered successfully.");
				
			}else{
				System.out.println("Room Name already in use!");
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
 		
 		public void deleteRoom(){
		
 			query="SELECT roomId FROM rooms";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		
 	 		try{
 	 			
 	 			while(resultset.next() && checkFlag==false){
					int id=Integer.parseInt(resultset.getString("roomId"));
					if(editedRoomId==id){
						checkFlag=true;
					}
				}
				if(checkFlag){
					query="DELETE FROM rooms WHERE roomId = '"+editedRoomId+"'";
					statement.executeUpdate(query);
					connection.commit();
					System.out.println("Room was deleted successfully.");
					
				}else{
					System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
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
 	
 		public int editRoom(){
 			
 			query="SELECT roomId FROM rooms";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		
 	 		try{
 	 			
 	 			switch(eventEditRoom){
 	 			
 	 			case 1://name change
 	 				
 	 					while(resultset.next() && checkFlag==false){
	 						int id=Integer.parseInt(resultset.getString("roomId"));
	 						if(editedRoomId==id){
	 							checkFlag=true;
	 						}
	 					}
	 					if(checkFlag){
	 						
	 						Boolean roomNameFlag=false;
	 						query="SELECT roomName FROM rooms";
	 			 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
	 			 	 		while(resultset.next()){
	 			 	 			String dbRoomName=resultset.getString("roomName");
	 			 	 			if(roomName.equals(dbRoomName)){
	 			 	 				roomNameFlag=true;
	 			 	 			}
	 			 	 		}
	 						
	 			 	 		if(!roomNameFlag){
	 			 	 			query="UPDATE rooms SET roomName='"+roomName+"' WHERE roomId='"+editedRoomId+"'";
	 			 	 			statement.executeUpdate(query);
	 			 	 			connection.commit();
	 			 	 			System.out.println("Room name Changed.");
	 			 				DataBaseConnection.dbClose(connection, statement, resultset);
	 			 	 			return 1;
	 			 	 		}else{
	 			 	 			System.out.println("Room name already in use.");
	 			 	 		}
				
	 					}else{
	 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
	 					}
	 					break;
 	 			
 	 			case 2://number of rooms change
 	 			
 	 					while(resultset.next() && checkFlag==false){
 	 						int id=Integer.parseInt(resultset.getString("roomId"));
 	 						if(editedRoomId==id){
 	 							checkFlag=true;
 	 						}
 	 					}
 	 					if(checkFlag){
 	 						query="UPDATE rooms SET numOfRooms='"+numberOfRooms+"' WHERE roomId='"+editedRoomId+"'";
 	 						statement.executeUpdate(query);
 	 						connection.commit();
 	 						System.out.println("Number of rooms Changed.");
					
 	 					}else{
 	 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 	 					}
 	 					break;
 	 			
 	 			case 3://number of single beds change
 	 	 			
	 					while(resultset.next() && checkFlag==false){
	 						int id=Integer.parseInt(resultset.getString("roomId"));
	 						if(editedRoomId==id){
	 							checkFlag=true;
	 						}
	 					}
	 					if(checkFlag){
	 						query="UPDATE rooms SET numOfSingleBeds='"+numberOfSingleBeds+"' WHERE roomId='"+editedRoomId+"'";
	 						statement.executeUpdate(query);
	 						connection.commit();
	 						System.out.println("Number of single beds Changed.");
				
	 					}else{
	 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
	 					}
	 					break;
	 					
 	 			case 4://number of double beds change
 	 	 			
 					while(resultset.next() && checkFlag==false){
 						int id=Integer.parseInt(resultset.getString("roomId"));
 						if(editedRoomId==id){
 							checkFlag=true;
 						}
 					}
 					if(checkFlag){
 						query="UPDATE rooms SET numOfDoubleBeds='"+numberOfDoubleBeds+"' WHERE roomId='"+editedRoomId+"'";
 						statement.executeUpdate(query);
 						connection.commit();
 						System.out.println("Number of double beds Changed.");
			
 					}else{
 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 					}
 					break;		
	 					
 	 			case 5://balcony setting
 	 				
 	 				if(hasBalcony==0 || hasBalcony==1){
	 						while(resultset.next() && checkFlag==false){
	 							int id=Integer.parseInt(resultset.getString("roomId"));
	 							if(editedRoomId==id){
	 								checkFlag=true;
	 							}
	 						}
	 						if(checkFlag){
	 							query="UPDATE rooms SET hasBalcony='"+hasBalcony+"' WHERE roomId='"+editedRoomId+"'";
	 							statement.executeUpdate(query);
	 							connection.commit();
	 							System.out.println("Balcony setting updated.");
			
	 						}else{
	 							System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
	 						}
	 					}else{
	 						System.out.println("Wrong balcony input.");
	 					}
 					break;
	 				
 	 			case 6://breakfast setting
 	 				
 	 					if(hasBreakfast==0 || hasBreakfast==1){
	 						while(resultset.next() && checkFlag==false){
	 							int id=Integer.parseInt(resultset.getString("roomId"));
	 							if(editedRoomId==id){
	 								checkFlag=true;
	 							}
	 						}
	 						if(checkFlag){
	 							query="UPDATE rooms SET hasBreakfast='"+hasBreakfast+"' WHERE roomId='"+editedRoomId+"'";
	 							statement.executeUpdate(query);
	 							connection.commit();
	 							System.out.println("Breakfast setting updated.");
			
	 						}else{
	 							System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
	 						}
	 					}else{
	 						System.out.println("Wrong breakfast input.");
	 					}
 	 					break;
 
 	 			case 7://luxury setting
 	 				
	 					if(isLuxury==0 || isLuxury==1){
 						while(resultset.next() && checkFlag==false){
 							int id=Integer.parseInt(resultset.getString("roomId"));
 							if(editedRoomId==id){
 								checkFlag=true;
 							}
 						}
 						if(checkFlag){
 							query="UPDATE rooms SET isLuxury='"+isLuxury+"' WHERE roomId='"+editedRoomId+"'";
 							statement.executeUpdate(query);
 							connection.commit();
 							System.out.println("Luxury setting updated.");
		
 						}else{
 							System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 						}
 					}else{
 						System.out.println("Wrong luxury input.");
 					}
	 					break;
	 			
	 			
	 					
 	 			case 8://single room default price change
 	 	 			
 					while(resultset.next() && checkFlag==false){
 						int id=Integer.parseInt(resultset.getString("roomId"));
 						if(editedRoomId==id){
 							checkFlag=true;
 						}
 					}
 					if(checkFlag){
 						query="UPDATE rooms SET defaultPrice='"+roomDefaultPrice+"' WHERE roomId='"+editedRoomId+"'";
 						statement.executeUpdate(query);
 						connection.commit();
 						System.out.println("Default price of room Changed.");
			
 					}else{
 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 					}
 					break;
 					
 	 			case 9://single room current price change
 	 	 			
 					while(resultset.next() && checkFlag==false){
 						int id=Integer.parseInt(resultset.getString("roomId"));
 						if(editedRoomId==id){
 							checkFlag=true;
 						}
 					}
 					if(checkFlag){
 						query="UPDATE rooms SET currentPrice='"+roomDefaultPrice+"' WHERE roomId='"+editedRoomId+"'";
 						statement.executeUpdate(query);
 						connection.commit();
 						System.out.println("Current price of room Changed.");
			
 					}else{
 						System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 					}
 					break;		
 					
 					
 					
 	 			case 10://availability setting
 	 				
	 					if(availability==0 || availability==1){
 						while(resultset.next() && checkFlag==false){
 							int id=Integer.parseInt(resultset.getString("roomId"));
 							if(editedRoomId==id){
 								checkFlag=true;
 							}
 						}
 						if(checkFlag){
 							query="UPDATE rooms SET isAvailable='"+availability+"' WHERE roomId='"+editedRoomId+"'";
 							statement.executeUpdate(query);
 							connection.commit();
 							if(availability==1){
 								System.out.println("Room is now available.");
 							}else{
 								System.out.println("Room is now reserved.");
 							}
 							
		
 						}else{
 							System.out.print("The room with the id :");System.out.print(editedRoomId);System.out.print("doesn't excist!");System.out.println();
 						}
 					}else{
 						System.out.println("Wrong availability input.");
 					}
	 					break;
	 				
 						
	 			default:
	 				System.out.println("Error, unknown eventEditRoom provided.");
 	 				break;
 	 				
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
			return 0;
 			
 		}
 		
		
 		public void updateSalePrices(){//all prices sale change
 			
 			query="SELECT currentPrice, defaultPrice FROM rooms";
			connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 			
			try{
			
				if(saleOption){
					query="UPDATE rooms SET currentPrice = defaultPrice - (defaultPrice * '"+roomSale+"'/100)";
				}else{
					query="UPDATE rooms SET currentPrice = defaultPrice + (defaultPrice * '"+roomSale+"'/100)";
				}
				
				statement.executeUpdate(query);
				connection.commit();
				System.out.println("Current room prices changed!");
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
 		
 		
 		public void resetPricesToDefault(){//all current prices return to default
 			
 			query="SELECT currentPrice, defaultPrice FROM rooms";
			connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 			
			try{
				query="UPDATE rooms SET currentPrice = defaultPrice ";
				statement.executeUpdate(query);
				connection.commit();
				System.out.println("Current room prices changed!");
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
 		
 		public static void printRooms(){
 			
 			Connection connection = null;
 			Statement statement =null;
 			ResultSet resultset=null;
 			String query=null;
 			
 			
 			query="SELECT * FROM rooms";
 	 		connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		
 	 		try{	
 	 			while(resultset.next()){
 	 				int roomId=resultset.getInt("roomId");
 	 				String name=resultset.getString("roomName");
 	 				float defPrice=resultset.getFloat("defaultPrice");
 	 				float curPrice=resultset.getFloat("currentPrice");
 	 				int numOfRooms=resultset.getInt("numOfRooms");
 	 				int singleBeds=resultset.getInt("numOfSingleBeds");
 	 				int doubleBeds=resultset.getInt("numOfDoubleBeds");
 	 				int balcony=resultset.getInt("hasBalcony");
 	 				int breakfast=resultset.getInt("hasBreakfast");
 	 				int luxury=resultset.getInt("isLuxury");
 	 				int available=resultset.getInt("isAvailable");

 	 				System.out.println("|"+roomId + "|"+name + "|" +defPrice + "|" +curPrice+ "|" +numOfRooms+ "|" +singleBeds+ "|" +doubleBeds+ "|" +balcony+ "|" +breakfast+ "|" +luxury+ "|" +available);
 	 			}
 	 			DataBaseConnection.dbClose(connection, statement, resultset);	

 	 		}catch (Exception se){
 	 			se.printStackTrace();
 	 		}
 	 			
 	 	}
 		
 		public static int[] printGraphicAvailability(){
 		
 			Connection connection = null;
 			Statement statement =null;
 			ResultSet resultset=null;
 			String query=null;	
 			
 			int totalRoomNum = 0;
 			int availableRoomNum = 0;
 			query="SELECT isAvailable FROM rooms";
 			connection=DataBaseConnection.getDBConnection();
 	 		statement=DataBaseConnection.getDBStatement(connection);
 	 		resultset=DataBaseConnection.getDBResultSet(statement, query);
 	 		try{
 	 			while(resultset.next()){
 	 				int available=resultset.getInt("isAvailable");
 	 				if(available==0){
 	 					availableRoomNum++;
 	 				}
 	 				totalRoomNum++;
 	 			}
 	 			DataBaseConnection.dbClose(connection, statement, resultset);	
 	 		}catch (Exception se){
 	 			se.printStackTrace();
 	 		}
 			return new int[] {totalRoomNum, availableRoomNum};
 		}
}