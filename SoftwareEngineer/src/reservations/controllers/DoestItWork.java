package reservations.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.collections.ObservableList;

public class DoestItWork {

	@Test
	public void test() {
		Personel testPers = new Personel("DeLa", "Giannelos", 0);
		assertEquals("DeLa",testPers.firstNameProperty().get());
		assertEquals("Giannelos",testPers.getLastName().get());
		assertNotEquals(3,testPers.getUserId().asObject());
		

		assertNotEquals(null,DataBaseConnection.getDBConnection());
		
		PersonnelManagement testPerMan = new PersonnelManagement("Vergos","Ioannis",1,0,0);
		ObservableList<Personel> testList = testPerMan.printPersonnel();
		assertEquals(9, testList.size());
		PersonnelManagement testPerMan2 = new PersonnelManagement("Kappas","Pride",3,0,0);
		ObservableList<Personel> testList2 = testPerMan2.printPersonnel();
		
		assertNotEquals(5, testList2.size());
		assertEquals(false,testPerMan.addPersonnel());
		assertNotEquals(true,testPerMan2.addPersonnel());
		
		UserLogin testLogin = new UserLogin("admin","admin");
		assertEquals(true, testLogin.connectUser());
	}

}
