package reservations.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RoomsReservationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane DoubleBedsComb;

    @FXML
    private ComboBox<?> singleBedsComb;

    @FXML
    private CheckBox hasBlanconyChecked;

    @FXML
    private TextField locationsTxt;

    @FXML
    private TextField costTxt;

    @FXML
    private Button addRoomToDB;

    @FXML
    void initialize() {

    }
}
