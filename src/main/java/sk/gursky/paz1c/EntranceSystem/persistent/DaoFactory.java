package sk.gursky.paz1c.EntranceSystem.persistent;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
	INSTANCE;
	
	private UserDao userDao;
	private CardReaderDao cardReaderDao;
	private JdbcTemplate jdbcTemplate;
	
	public UserDao getUserDao() {
		if (userDao == null) {
//			userDao =  new MemoryUserDao();
			userDao = new MysqlUserDao(getJdbcTemplate());
		}
		return userDao;
	}
	
	public CardReaderDao getCardReaderDao() {
		if (cardReaderDao == null) {
			cardReaderDao = new MysqlCardReaderDao(getJdbcTemplate());
		}
		return cardReaderDao;
	}
	private JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("paz1c");
			dataSource.setPassword("paz1cjesuper");
			dataSource.setDatabaseName("entrance");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
