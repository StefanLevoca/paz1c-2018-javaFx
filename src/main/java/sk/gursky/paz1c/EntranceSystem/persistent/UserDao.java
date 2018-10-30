package sk.gursky.paz1c.EntranceSystem.persistent;

import java.util.List;

public interface UserDao {

	void addUser(User user);

	List<User> getAll();

	int usersCount();

	void save(User u);

}