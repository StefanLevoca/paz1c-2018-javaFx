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
	
	public void save(User user) {
		if (user.getId() == null) {
			addUser(user);
		} else {
			for (int i = 0; i < users.size(); i++) {
				if (user.equals(users.get(i))) {
					users.set(i, user);
				}
			}
		}
	}
}
