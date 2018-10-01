package sk.gursky.paz1c.EntranceSystem;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class EntranceMainController {

	private List<User> users = new ArrayList<User>();
	
	public EntranceMainController() {
		User u = new User();
		u.setChipId("123456");
		u.setMeno("Jano");
		users.add(u);
		u = new User();
		u.setChipId("223456");
		u.setMeno("Fero");
		users.add(u);
	}
	
    @FXML
    private ListView<User> usersListView;

    @FXML
    void initialize() {
    	usersListView.setItems(FXCollections.observableArrayList(users));
    }
}
