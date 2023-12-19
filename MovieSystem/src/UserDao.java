import java.util.*;
import java.io.Closeable;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class UserDao implements AutoCloseable {
	private Connection conn;
	
	public UserDao() throws Exception{
		conn = DbUtil.getConnection();
	}
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<User> showAllUser(int id) throws Exception{
		List<User> list = new ArrayList<User>();
		String sql = "select * from users where user_id != ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int userId = rs.getInt("user_id");
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					java.sql.Date sDate = rs.getDate("birthDate");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String birthDate = sdf.format(sDate);
					User user = new User(userId,fname,lname,email,password,mobile,birthDate);
					list.add(user);
				}
			}
		}
		return list;
	}
	
	public User showAllUserRetObj() throws Exception{
		User user = new User();
		String sql = "select * from users";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int userId = rs.getInt("user_id");
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					java.sql.Date sDate = rs.getDate("birthDate");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String birthDate = sdf.format(sDate);
					user = new User(userId,fname,lname,email,password,mobile,birthDate);
				}
			}
		}
		return user;
	}
}
