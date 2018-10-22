package sk.gursky.paz1c.EntranceSystem.persistent;

import java.util.List;

public interface UserDao {

	void addUser(User user);

	List<User> getAll();

	int usersCount();

	boolean validate(String chipId);

	void deactivate(String chipId);

}