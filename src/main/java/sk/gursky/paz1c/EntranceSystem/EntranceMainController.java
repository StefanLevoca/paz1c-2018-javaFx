package sk.gursky.paz1c.EntranceSystem;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.gursky.paz1c.EntranceSystem.persistent.User;
import sk.gursky.paz1c.EntranceSystem.persistent.UserDao;
import sk.gursky.paz1c.EntranceSystem.persistent.DaoFactory;

public class EntranceMainController {

	private UserDao userDao = DaoFactory.INSTANCE.getUserDao();
	
	public EntranceMainController() {
//		User u = new User();
//		u.setChipId("123456");
//		u.setName("Jano");
//		userDao.addUser(u);
//		u = new User();
//		u.setChipId("223456");
//		u.setName("Fero");
//		userDao.addUser(u);
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
    private User selectedUser = null;
    
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
    	
    	usersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

			@Override
			public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
				selectedUser = newValue;
			}
		});
    	
    	usersListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					try {
						// mozme editovat pouzivatela
						UserEditController controller = new UserEditController(selectedUser);
						FXMLLoader fxmlLoader = new FXMLLoader(
								getClass().getResource("UserEdit.fxml"));
						fxmlLoader.setController(controller);
						Parent rootPane = fxmlLoader.load();
						Scene scene = new Scene(rootPane);
						Stage stage = new Stage();
						stage.setTitle("Editacia pouzivatela");
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.showAndWait();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
    }
}
