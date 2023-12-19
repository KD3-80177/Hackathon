import java.util.*;
import java.io.Closeable;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class MoviesDao implements AutoCloseable {
	private Connection conn;
	
	public MoviesDao() throws Exception{
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
	
	public List<Movie> showAllMovie() throws Exception{
		List<Movie> list = new ArrayList<Movie>();
		String sql = "select * from movies";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int m_id = rs.getInt("movie_id");
					String title = rs.getString("title");
					java.sql.Date sqlDate = rs.getDate("release_date");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String rDate = sdf.format(sqlDate);
					Movie m = new Movie(m_id,title,rDate);
					list.add(m);
				}
			}
		}
		return list;
	}
}
