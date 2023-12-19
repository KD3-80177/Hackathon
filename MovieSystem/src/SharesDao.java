import java.util.*;
import java.io.Closeable;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class SharesDao implements AutoCloseable{
	private Connection conn;
	
	public SharesDao() throws Exception{
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
	
	public void shareReview(List l,int revid,int id) throws Exception{
		String sql = "insert into shares values (?,?,?)";
		for(int i = 0 ; i < l.size()-1 ; i++) {
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setInt(1,revid);
				stmt.setInt(2, (int) l.get(i));
				stmt.setInt(3, id);
				stmt.executeUpdate();
			}
		}
	}
	
	public List<SharedWithMe> displaySharedWithMe(int id) throws Exception{
		List<SharedWithMe> list = new ArrayList<SharedWithMe>();
		String sql = "select shares.review_id,reviews.movie_id,shares.rec_from,reviews.review,reviews.rating,reviews.modified from shares,reviews where shares.user_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int rev_id = rs.getInt("rev_id");
					int mov_id = rs.getInt("movie_id");
					int rec_from = rs.getInt("rec_from");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					Timestamp tobj = rs.getTimestamp("modified");
					String timeStamp = tobj.toString();
					SharedWithMe share = new SharedWithMe(rev_id,mov_id,rec_from,review,rating,timeStamp);
					list.add(share);
				}
			}
		}
		return list;
	}
}
