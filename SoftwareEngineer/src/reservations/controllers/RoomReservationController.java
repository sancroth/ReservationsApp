package reservations.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomReservationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView roomImg1;

    @FXML
    private ImageView roomImg2;

    @FXML
    private ImageView roomImg3;

    @FXML
    void initialize() {
    	Image roomsImage = new Image("rooms.png");
    	roomImg1.setImage(roomsImage);
    	roomImg2.setImage(roomsImage);
    	roomImg3.setImage(roomsImage);
    }
}