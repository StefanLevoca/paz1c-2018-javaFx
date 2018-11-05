package sk.gursky.paz1c.EntranceSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public void start(Stage stage) throws Exception {
		logger.info("aplikacia spustena");
		EntranceMainController controller = new EntranceMainController();
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("EntranceMain.fxml"));
		fxmlLoader.setController(controller);
		Parent rootPane = fxmlLoader.load();
		Scene scene = new Scene(rootPane);
		stage.setTitle("Entrance system");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

