package sk.gursky.paz1c.EntranceSystem;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EntranceMainController {

	private UserDao userDao = new UserDao();
	
	public EntranceMainController() {
		User u = new User();
		u.setChipId("123456");
		u.setMeno("Jano");
		userDao.addUser(u);
		u = new User();
		u.setChipId("223456");
		u.setMeno("Fero");
		userDao.addUser(u);
	}
	
    @FXML
    private ListView<User> usersListView;

    @FXML
    private TextField chipIdTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;
    
    private UserFxModel editedUser = new UserFxModel();
    
    @FXML
    void initialize() {
    	chipIdTextField.textProperty().bindBidirectional(editedUser.chipIdProperty());
    	nameTextField.textProperty().bindBidirectional(editedUser.nameProperty());
    	usersListView.setItems(FXCollections.observableArrayList(userDao.getAll()));
    	
    	addButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// ulozit noveho pouzivatela
				User newUser = editedUser.getUser();
				userDao.addUser(newUser);
				usersListView.getItems().add(newUser);
			}
		});
    }
}
