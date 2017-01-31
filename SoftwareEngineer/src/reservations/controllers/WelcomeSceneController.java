package reservations.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import reservations.Main;

public class WelcomeSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ImageView welcomeImage;

    @FXML
    void initialize() {
    	Image vacationsImage = new Image("rent-hello-logo.png");
    	welcomeImage.setImage(vacationsImage);
    	welcomeImage.fitWidthProperty().bind(Main.getRootLayout().widthProperty());
    	welcomeImage.setPreserveRatio(true);
//    	double imageH = Main.getRootLayout().getHeight();
//    	double imageW = Main.getRootLayout().getWidth();
//    	welcomeImage.setFitHeight(imageH);
//    	welcomeImage.setFitWidth(imageW);
    	if(!Main.getIsWelcomeSceneDone()){welcomeScenePlay();}
    }
    
    private void welcomeScenePlay(){
    	FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),Main.getRootLayout());
    	fadeIn.setFromValue(0);
    	fadeIn.setToValue(1);
    	fadeIn.setCycleCount(1);
    	
    	FadeTransition fadeOut = new FadeTransition(Duration.seconds(3),Main.getRootLayout());
    	fadeOut.setFromValue(1);
    	fadeOut.setToValue(0);
    	fadeOut.setCycleCount(1);
    	
    	fadeIn.play();
    	
    	fadeIn.setOnFinished((e)->{
    		fadeOut.play();
    	});
    	
    	fadeOut.setOnFinished((e)->{
    		Main.loginLayout();
    	});
 
    }
}