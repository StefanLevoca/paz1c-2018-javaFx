package sk.gursky.paz1c.EntranceSystem;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	private List<User> users = new ArrayList<User>();
	
	public void addUser(User user) {
		if (user != null) {
			users.add(user);
		}
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
