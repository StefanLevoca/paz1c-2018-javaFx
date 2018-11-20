package sk.gursky.paz1c.EntranceSystem.persistent;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import sk.gursky.paz1c.EntranceSystem.annotations.DaoGetter;

public enum DaoFactory {
	INSTANCE;
	
	private UserDao userDao;
	private CardReaderDao cardReaderDao;
	private JdbcTemplate jdbcTemplate;

	@DaoGetter(description = "UserDaoGetter")
	public UserDao getUserDao() {
		if (userDao == null) {
//			userDao =  new MemoryUserDao();
			userDao = new MysqlUserDao(getJdbcTemplate());
		}
		return userDao;
	}
	
	@DaoGetter
	public CardReaderDao getCardReaderDao() {
		if (cardReaderDao == null) {
			cardReaderDao = new MysqlCardReaderDao(getJdbcTemplate());
		}
		return cardReaderDao;
	}
	private JdbcTemplate getJdbcTemplate() {
		try {
			if (jdbcTemplate == null) {
				MysqlDataSource dataSource = new MysqlDataSource();
				dataSource.setUser("paz1c");
				dataSource.setPassword("paz1cjesuper");
				dataSource.setDatabaseName("entrance");
				dataSource.setUseSSL(true);
				jdbcTemplate = new JdbcTemplate(dataSource);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jdbcTemplate;
	}
}
