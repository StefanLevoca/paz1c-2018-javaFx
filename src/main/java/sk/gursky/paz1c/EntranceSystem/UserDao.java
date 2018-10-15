package sk.gursky.paz1c.EntranceSystem;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private long lastId = 0;
	private List<User> users = new ArrayList<User>();
	
	public void addUser(User user) {
		if (user != null) {
			user.setId(++lastId);
			users.add(user);
		}
	}
	
	public List<User> getAll() {
		return users;
	}
	
	public int usersCount() {
		return users.size();
	}

	public boolean validate(String chipId) {
		for(User u: users) {
			if (chipId.equals(u.getChipId())) {
				if (!u.isActive()) {
					return false;
				}
				return true;
			}
		} 
		return false;
	}
	
	public void deactivate(String chipId) {
		for(User u: users) {
			if (chipId.equals(u.getChipId())) {
				u.setActive(false);
			}
		} 		
	}
}
