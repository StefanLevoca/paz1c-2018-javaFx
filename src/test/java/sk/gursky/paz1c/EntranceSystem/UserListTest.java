package sk.gursky.paz1c.EntranceSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.gursky.paz1c.EntranceSystem.persistent.MemoryUserDao;
import sk.gursky.paz1c.EntranceSystem.persistent.User;
import sk.gursky.paz1c.EntranceSystem.persistent.UserDao;

class UserListTest {

	private UserDao userList;

	@BeforeEach
	void setUp() throws Exception {
		userList = new MemoryUserDao();
		User u = new User();
		u.setChipId("123456");
		u.setName("Jano");
		userList.addUser(u);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddUser() {
		int count = userList.usersCount();
		userList.addUser(null);
		assertEquals(count, userList.usersCount());
	}
	
	@Test
	void testValidate() {
		assertTrue(userList.validate("123456"));
		assertFalse(userList.validate("111111"));
		
		User u = new User();
		u.setActive(false);
		u.setChipId("111111");
		userList.addUser(u);
		
		assertFalse(userList.validate("111111"));
	}
	
	@Test
	void testDeactivate() {
		userList.deactivate("123456");
		assertFalse(userList.validate("123456"));
	}
}
