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

import sk.gursky.paz1c.EntranceSystem.annotations.EntityGetter;

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
		insertCardReaders(user);
	}

	@EntityGetter
	@Override
	public List<User> getAll() {
		String sql = "SELECT id, chip_id, name, active, user_card_reader.card_reader_id AS crid "
				+ "FROM user LEFT JOIN "
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
					if (! rs.wasNull()) {
						user.getCardReaders().add(myMap.get(idCR));
					}
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
		String sql = "SELECT count(*) from user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public void save(User user) {
		if (user.getId() == null) {
			addUser(user);
		} else {
			String sql = "UPDATE user SET chip_id = ?, name = ?, active = ? "
					+ "WHERE id = ?";
			jdbcTemplate.update(sql,user.getChipId(), user.getName(),
					user.isActive(), user.getId());
			insertCardReaders(user);
		}
	}
	
	private void insertCardReaders(User user) {
		jdbcTemplate.update("DELETE FROM user_card_reader WHERE user_id= ?",
				user.getId());
		if (user.getCardReaders().size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO user_card_reader (user_id,card_reader_id)");
			sb.append(" VALUES ");
			for (int i = 0; i < user.getCardReaders().size(); i++) {
				sb.append("("+ user.getId() +","+ 
							user.getCardReaders().get(i).getId()+ "),");
			}
			String insertSql = sb.substring(0, sb.length()-1);
			jdbcTemplate.update(insertSql);
		}
	}

	@Override
	public void delete(long id) throws UserNotFoundException {
		jdbcTemplate.update("DELETE FROM user_card_reader WHERE user_id= ?", id);
		int deleted = jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
		if (deleted == 0)
			throw new UserNotFoundException(id);
	}
}
