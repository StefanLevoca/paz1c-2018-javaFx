package sk.gursky.paz1c.EntranceSystem.persistent;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserDao implements UserDao {
	private long lastId = 0;
	private List<User> users = new ArrayList<User>();
	
	@Override
	public void addUser(User user) {
		if (user != null) {
			user.setId(++lastId);
			users.add(user);
		}
	}
	
	@Override
	public List<User> getAll() {
		return users;
	}
	
	@Override
	public int usersCount() {
		return users.size();
	}

	@Override
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
	
	@Override
	public void deactivate(String chipId) {
		for(User u: users) {
			if (chipId.equals(u.getChipId())) {
				u.setActive(false);
			}
		} 		
	}
}
