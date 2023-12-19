import java.util.*;
import java.io.Closeable;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class UserLoginSgnupDao implements AutoCloseable{
	private Connection conn;
	public UserLoginSgnupDao() throws Exception{
		conn = DbUtil.getConnection(); 
	}
	
	public void close() throws Exception{
		try {
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Date parseDate(String d) {
		Date dt = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			dt = sdf.parse(d);
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dt;
	}
	
	public int signUp(User u) throws SQLException{
		int cnt = 0;
		String sql = "insert into users values (default,?,?,?,?,?,?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, u.getFname());
			stmt.setString(2, u.getLname());
			stmt.setString(3,u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			String bdate = u.getBdate();
			Date utilDate = parseDate(bdate);
			java.sql.Date sqlBDate = new java.sql.Date(utilDate.getTime());
			stmt.setDate(6, sqlBDate);
			cnt = stmt.executeUpdate();
			return cnt;
		}
	}
	public void signIn(String email, String pass) throws SQLException{
		String sql = "select user_id,password from users where email = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					if(rs.getString("password").equals(pass)) {
						System.out.println("\nLogged in successfully.");
						int id = rs.getInt("user_id");
						System.out.println(id);
						MovieMain m = new MovieMain();
						m.allOperations(id);
					}
					else {
						System.out.println("Invalid credentials..");
					}
				}
			}
		}
				
	}
	
	public List<User> getUserDetails(int userid) throws Exception{
		List<User> list = new ArrayList<User>();
		String sql = "select user_id,fname,lname,email,mobile,birthDate from users where user_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, userid);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int userid1 = rs.getInt("user_id");
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String email1 = rs.getString("email");
					String mobile = rs.getString("mobile");
					java.sql.Date bd = rs.getDate("birthDate");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String bdate = sdf.format(bd);
					User u = new User(userid1,fname,lname,email1,mobile,bdate);
					list.add(u);
				}
			}
		}
		return list;
	}
	
	public int updateUser(User u,int userid) throws Exception{
		int cnt = 0;
		String sql = "update users set fname = ?, lname = ?, email = ?, password = ?, mobile = ? , birthDate = ? where user_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, u.getFname());
			stmt.setString(2, u.getLname());
			stmt.setString(3,u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			String bdate = u.getBdate();
			Date utilDate = parseDate(bdate);
			java.sql.Date sqlBDate = new java.sql.Date(utilDate.getTime());
			stmt.setDate(6, sqlBDate);
			stmt.setInt(7, userid);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
	
	public int updatePassword(int userid,String p) throws Exception {
		int cnt = 0;
		String sql = "update users set password = ? where user_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, p);
			stmt.setInt(2, userid);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
	
}
