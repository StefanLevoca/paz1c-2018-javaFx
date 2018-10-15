package sk.gursky.paz1c.EntranceSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlUserDao implements UserDao {

	JdbcTemplate jdbcTemplate;
	
	public MysqlUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT id, chip_id, name, active FROM user";
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setChipId(rs.getString("chip_id"));
				user.setMeno(rs.getString("name"));
				user.setActive(rs.getBoolean("active"));
				return user;
			}
			
		});
		return users;
	}

	@Override
	public int usersCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validate(String chipId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deactivate(String chipId) {
		// TODO Auto-generated method stub

	}

}
