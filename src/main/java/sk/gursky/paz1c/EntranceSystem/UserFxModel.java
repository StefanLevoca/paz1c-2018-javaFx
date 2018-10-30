package sk.gursky.paz1c.EntranceSystem;

import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.gursky.paz1c.EntranceSystem.persistent.CardReader;
import sk.gursky.paz1c.EntranceSystem.persistent.User;

public class UserFxModel {

	private Long id;
	private StringProperty chipId = new SimpleStringProperty();
	private StringProperty name = new SimpleStringProperty();
	private BooleanProperty active = new SimpleBooleanProperty();
	private Map<CardReader, BooleanProperty> selectedCardReaders;

	public void setUser(User user) {
		this.id = user.getId();
		setName(user.getName());
		setChipId(user.getChipId());
		setActive(user.isActive());
	}
	
	public String getChipId() {
		return chipId.get();
	}
	public void setChipId(String chipId) {
		this.setChipId(chipId);
	}
	public StringProperty chipIdProperty() {
		return chipId;
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.setName(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	public Boolean isActive() {
		return active.get();
	}

	public void setActive(Boolean active) {
		this.active.set(active);
	}
	
	public BooleanProperty activeProperty() {
		return active;
	}

	public User getUser() {
		User user = new User();
		user.setChipId(getChipId());
		user.setName(getName());
		return user;
	}
}
