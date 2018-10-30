package sk.gursky.paz1c.EntranceSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import sk.gursky.paz1c.EntranceSystem.persistent.User;

public class UserEditController {

    @FXML
    private CheckBox activeCheckBox;

    @FXML
    private TextField menoTextField;

    @FXML
    private TextField chipIdTextField;

    @FXML
    private FlowPane cardReadersFlowPane;

    @FXML
    private Button saveButton;
    
    private UserFxModel model;
    
    public UserEditController(User user) {
		model = new UserFxModel();
	}

	@FXML
    void initialize() {
    }
}
