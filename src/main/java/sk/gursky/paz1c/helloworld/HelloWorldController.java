package sk.gursky.paz1c.helloworld;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloWorldController {

    @FXML
    private Button button;

    @FXML
    void initialize() {
    	button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Z kontrolera");
			}
		});
    }
}