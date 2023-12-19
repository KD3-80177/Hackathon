import java.util.*;
import java.io.Closeable;
import java.sql.*;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class ReviewDao implements AutoCloseable {
	private Connection conn;
	
	public ReviewDao() throws Exception{
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
	
	public int addReview(Reviews review) throws Exception {
		int cnt = 0;
		String sql = "insert into reviews values(default, ?,?,?,?,now())";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, review.getM_id());
			stmt.setString(2, review.getReview());
			stmt.setInt(3, review.getRating());
			stmt.setInt(4, review.getUser_id());
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
	
	public List<Reviews> showAllReviews() throws Exception{
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "select * from reviews";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int rev_id = rs.getInt("rev_id");
					int mov_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("user_id");
					int user_id = rs.getInt("user_id");
					Timestamp tobj = rs.getTimestamp("modified");
					String timeStamp = tobj.toString();
					Reviews r = new Reviews(rev_id,mov_id,review,rating,user_id,timeStamp);
					list.add(r);
				}
			}
		}
		return list;
	}
	
	public List<Reviews> showResReviews(int rev_id) throws Exception{
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "select * from reviews where user_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, rev_id);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int rev_id1 = rs.getInt("rev_id");
					int mov_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int user_id = rs.getInt("user_id");
					Timestamp tobj = rs.getTimestamp("modified");
					String timeStamp = tobj.toString();
					Reviews r = new Reviews(rev_id1,mov_id,review,rating,user_id,timeStamp);
					list.add(r);
				}
			}
		}
		return list;
	}
	public int updateReview(int rev_id,int rat, String rev) throws Exception{
		int cnt = 0;
		String sql = "update reviews set rating = ? , review = ?, modified = now() where rev_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, rat);
			stmt.setString(2, rev);
			stmt.setInt(3, rev_id);
			cnt = stmt.executeUpdate();
		}
		return cnt;
	}
	
	public int deleteReview(int rid) throws Exception{
		int cnt = 0;
		String sql = "delete from reviews where rev_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, rid);
			cnt = stmt.executeUpdate();
		}
		return cnt;
		
	}
}
