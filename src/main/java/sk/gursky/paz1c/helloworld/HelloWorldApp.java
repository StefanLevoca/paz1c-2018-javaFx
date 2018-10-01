package sk.gursky.paz1c.helloworld;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {
	
	public void start(Stage stage) throws Exception {
		HelloWorldController controller = new HelloWorldController();
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("HelloWorld.fxml"));
		fxmlLoader.setController(controller);
		Parent rootPane = fxmlLoader.load();
		Scene scene = new Scene(rootPane);
		stage.setTitle("Hello World");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
