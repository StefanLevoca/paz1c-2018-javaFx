package sk.gursky.paz1c.EntranceSystem;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum UserDaoFactory {
	INSTANCE;
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		if (userDao == null) {
//			userDao =  new MemoryUserDao();
			userDao = getMysqlUserDao();
		}
		return userDao;
	}
	
	private MysqlUserDao getMysqlUserDao() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("paz1c");
		dataSource.setPassword("paz1cjesuper");
		dataSource.setDatabaseName("entrance");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return new MysqlUserDao(jdbcTemplate);
	}
}
