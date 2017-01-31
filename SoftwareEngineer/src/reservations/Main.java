package reservations;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Boolean isWelcomeDone = false;

    private static Stage primaryStage;
    private static BorderPane rootLayout;
    
    private static Boolean successfullLogin = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("ReservationsApp");
        Main.primaryStage.getIcons().add(new Image("file:image/title-icon.png"));
        initRootLayout();
        showWelcomeScene();
   // 	if(Main.getIsWelcomeSceneDone() == false){loadWelcomeScreen();}
	}
	
	public void initRootLayout(){
		try{
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
            setRootLayout((BorderPane) loader.load());
            rootLayout.setPrefHeight(400);
            rootLayout.setPrefWidth(400);
            Scene scene = new Scene(getRootLayout());
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
		}  catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void loginLayout(){
		try{
			
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RoomsPresentation.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            // Set Login Scene content into the center of root layout.
            rootLayout.setOpacity(1);
            rootLayout.setCenter(login);
            primaryStage.setWidth(980);
            primaryStage.setHeight(690);
		}  catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public void showWelcomeScene() {
        try {
            // Load Welcome Scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/WelcomeScene.fxml"));
            AnchorPane welcomeScene = (AnchorPane) loader.load();
            // Set Welcome Scene content into the center of root layout.
            rootLayout.setCenter(welcomeScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    public static Boolean getUserLogin(){
    	return successfullLogin;
    }
    
    public static void setUserLogin(Boolean loginValue){
    	Main.successfullLogin = loginValue;
    }
    
	public static BorderPane getRootLayout() {
		return rootLayout;
	}

	public static void setRootLayout(BorderPane rootLayout) {
		Main.rootLayout = rootLayout;
	}
	
	
	public static Boolean getIsWelcomeSceneDone(){
		return isWelcomeDone;
	}
	
	public static void setIsWelcomeSceneDone(Boolean val){
		Main.isWelcomeDone = val;
	}
	
	
	public static Stage getPrimaryStage(){
		return Main.primaryStage;
	}
    
	
	public static void main(String[] args) {
		launch(args);
	}
}
