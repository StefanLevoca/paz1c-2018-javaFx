package sk.gursky.paz1c.EntranceSystem.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlUserDao implements UserDao {

	JdbcTemplate jdbcTemplate;
	
	public MysqlUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addUserOld(User user) {
		String sql = "INSERT INTO user (chip_id, name, active) VALUES (?,?,?)";
		jdbcTemplate.update(sql, user.getChipId(), user.getName(), user.isActive());
	}

	@Override
	public void addUser(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		insert.setTableName("user");
		insert.usingGeneratedKeyColumns("id");
		insert.usingColumns("chip_id", "name", "active");
		
		Map<String, Object> values = new HashMap<>();
		values.put("chip_id", user.getChipId());
		values.put("name", user.getName());
		values.put("active", user.isActive());
		
		user.setId(insert.executeAndReturnKey(values).longValue());
	}

	
	@Override
	public List<User> getAll() {
		String sql = "SELECT id, chip_id, name, active, user_card_reader.card_reader_id AS crid FROM user JOIN "
				+ "user_card_reader ON user.id = user_card_reader.user_id";
		List<User> users = jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> users = new ArrayList<>();
				
				List<CardReader> cardReaders = DaoFactory.INSTANCE.getCardReaderDao().getAll();
				Map<Long, CardReader> myMap = new HashMap<>();
				for (CardReader cr : cardReaders) {
					myMap.put(cr.getId(), cr);
				}
				
				User user = null;
				while(rs.next()) {
					Long id = rs.getLong("id");
					if (user == null || user.getId() != id) {
						user = new User();
						user.setId(rs.getLong("id"));
						user.setChipId(rs.getString("chip_id"));
						user.setName(rs.getString("name"));
						user.setActive(rs.getBoolean("active"));
						user.setCardReaders(new ArrayList<>());
						users.add(user);
					}
					Long idCR = rs.getLong("crid");
					user.getCardReaders().add(myMap.get(idCR));
				}
				return users;
			}
		});
//		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
//
//			@Override
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User();
//				user.setId(rs.getLong("id"));
//				user.setChipId(rs.getString("chip_id"));
//				user.setName(rs.getString("name"));
//				user.setActive(rs.getBoolean("active"));
//				return user;
//			}
//			
//		});
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
