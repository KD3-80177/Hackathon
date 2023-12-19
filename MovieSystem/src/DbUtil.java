import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.*;
public class DbUtil {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/hackathon";
	public static final String DB_USER = "KD3_80177_niraj";
	public static final String DB_PASS = "manager";
	
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return conn;
	}
}
