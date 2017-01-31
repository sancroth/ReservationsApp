package reservations.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import reservations.Main;

public class LoginController {
	
	private static String user;
	private static String pass;

	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane registerButton;

    @FXML
    private Button loginButton;
    
    @FXML
    private PasswordField passwordContainer;

    @FXML
    private TextField userNameContainer;

    @FXML
    void loginCheck(ActionEvent event) throws IOException {
    	user = userNameContainer.getText();
    	pass= passwordContainer.getText();
    	UserLogin check = new UserLogin(user,pass);
    	check.connectUser();
    	Boolean gotIn = check.connectUser();
    	if(gotIn){System.out.println("HOREY");
    	Main.setUserLogin(true);;
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Login Status");
    	alert.setHeaderText(null);
    	alert.setContentText("Your Login was successfull.");
    	alert.showAndWait();
    	}else{
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Login Status");
        	alert.setHeaderText(null);
        	alert.setContentText("Your Login was not succesfull.\nPlease check your credentials.");
        	alert.showAndWait();
    	}
    }

    @FXML
    void showRegisterForm(ActionEvent event) {
    	
    }

    @FXML
    void initialize() {
    }
}