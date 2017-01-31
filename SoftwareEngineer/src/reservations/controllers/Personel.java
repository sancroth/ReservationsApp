package reservations.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personel {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final IntegerProperty userId;
	
	public Personel(String firstName , String lastName , int userId){
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.userId = new SimpleIntegerProperty(userId);
	}
	
	public StringProperty firstNameProperty(){
		return firstName;
	}
	
	public StringProperty getLastName(){
		return lastName;
	}
	
	public IntegerProperty getUserId(){
		return userId;
	}
	
	
}
