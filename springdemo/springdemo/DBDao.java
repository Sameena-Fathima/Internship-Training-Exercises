package springdemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DBDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	JdbcTemplate jdbc;
	
	public void getNoOfRows() {
		jdbc = new JdbcTemplate(dataSource);
		int rows = jdbc.queryForInt("select count(*) from users");
		System.out.println("No of rows in users..."+rows);
	}
	public UserDTO getUserById(int id) {
		jdbc = new JdbcTemplate(dataSource);
		
		UserDTO user = jdbc.queryForObject("select * from users where userid=?", new Object[] {id}, new RowMapper<UserDTO>() {
			public UserDTO mapRow(ResultSet rs, int row) throws SQLException {
				UserDTO userdto = new UserDTO();
				userdto.setId(rs.getInt(1));
				userdto.setName(rs.getString(2));
				userdto.setPass(rs.getString(3));
				userdto.setFlag(rs.getInt(4));
				return userdto;
			}
		});
		
		return user;
	}
	
	public List<UserDTO> getAllUsers() {
		jdbc = new JdbcTemplate(dataSource);
		
		List<UserDTO> users = jdbc.query("select * from users",new RowMapper<UserDTO>() {
			public UserDTO mapRow(ResultSet rs, int row) throws SQLException {
				UserDTO user = new UserDTO();
				user.setId(rs.getInt("userid"));
				user.setName(rs.getString("username"));
				user.setPass(rs.getString("password"));
				user.setFlag(rs.getInt("flag"));
				return user;
			}
		});
		
		return users;
	}
}
