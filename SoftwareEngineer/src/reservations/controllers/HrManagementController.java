package reservations.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;



public class HrManagementController {

	public HrManagementController(){}
	
    @FXML
    private ResourceBundle resources;
    /**
    * 
    *
    * @author Joh Vergs + De la Giannelos
    */
    @FXML
    private URL location;

    @FXML
    private CheckBox addUserIsAdmin;

    @FXML
    private TextField addUserPassword; 

    @FXML
    private TextField addUserUsername;

    @FXML
    private Button addUserSubmitBtn;

    @FXML
    private Button addUserCancelBtn;


    @FXML
    private TableView<Personel>userRemoveTable;

    @FXML
    private TableColumn<Personel, Integer>removeListId;
    /**
    * 
    *
    * table data
    */
    @FXML
    private TableColumn<Personel, String>removeUserFirstNameClm;

    @FXML
    private TableColumn<Personel, String>removeUserLastNameClm;

    
	private ObservableList<Personel> personel = FXCollections.observableArrayList(); 

    
	private PersonnelManagement getUsers;
	
    @FXML
    private Button rmvUserBtn;


    @FXML
    void removeUser(ActionEvent event) {
    	int selected = userRemoveTable.getSelectionModel().getSelectedIndex();
    	int toRemove = removeListId.getCellData(selected);
    	//System.out.println(toRemove);
    	ObservableList<Personel> personelTmp = personel; 
    	personel.remove(selected);
    	userRemoveTable.getItems().removeAll(personelTmp);
    	userRemoveTable.getItems().addAll(personel);
    	PersonnelManagement userAdd = new PersonnelManagement("remove", "remove", 0, toRemove, 0);
    	userAdd.deletePersonnel();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("User Removal Status");
    	alert.setHeaderText(null);
    	alert.setContentText("User Removed.");
    	alert.showAndWait();
    	
    }

    @FXML
    void addUserSubmitAction(ActionEvent event) {
    	String name = addUserUsername.getText();
    	String pass = addUserPassword.getText();
    	Boolean checked = addUserIsAdmin.selectedProperty().getValue();
    	int isAdmin = 0;
    	if(checked){isAdmin=1;}
    	PersonnelManagement userAdd = new PersonnelManagement(name, pass, isAdmin, 0, 0);
    	Boolean added = userAdd.addPersonnel();
    	if(added== true){
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("User Creation Status");
        	alert.setHeaderText(null);
        	alert.setContentText("User Creation was completed successfully.");
        	alert.showAndWait();
        	clearText();
        	personel = getUsers.printPersonnel();
        	Personel addTolist = personel.get(personel.size()-1);
        	userRemoveTable.getItems().add(addTolist);
    	}else{
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("User Creation Status");
        	alert.setHeaderText(null);
        	alert.setContentText("User Creation Failed.\nPlease Try Again.");
        	alert.showAndWait();
        	clearText();
    	}

    }
    
    private void clearText(){
    	addUserUsername.clear();
    	addUserPassword.clear();
    	addUserIsAdmin.setSelected(false);
    }

    
    @FXML
    void initialize() {
    	getUsers = new PersonnelManagement("random", "random", 0, 0, 0);
    	personel = getUsers.printPersonnel();
    	userRemoveTable.getItems().addAll(personel);
    	removeUserFirstNameClm.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());    	
    	removeUserLastNameClm.setCellValueFactory(cellData -> cellData.getValue().getLastName());
    	removeListId.setCellValueFactory(cellData -> cellData.getValue().getUserId().asObject());
    }
    
}
