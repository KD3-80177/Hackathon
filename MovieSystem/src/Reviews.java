import java.util.*;
public class Reviews {
	private int rev_id;
	private int m_id;
	private String review;
	private int rating;
	private int user_id;
	private String mod_time;
	public Reviews() {
		super();
	}
	
	public Reviews(int rev_id, int m_id, String review, int rating, int user_id, String mod_time) {
		super();
		this.rev_id = rev_id;
		this.m_id = m_id;
		this.review = review;
		this.rating = rating;
		this.user_id = user_id;
		this.mod_time = mod_time;
	}

	public Reviews(int m_id, String review, int rating, int user_id, String mod_time) {
		super();
		this.m_id = m_id;
		this.review = review;
		this.rating = rating;
		this.user_id = user_id;
		this.mod_time = mod_time;
	}
	
	public Reviews(int m_id, String review, int rating, int user_id) {
		super();
		this.m_id = m_id;
		this.review = review;
		this.rating = rating;
		this.user_id = user_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getMod_time() {
		return mod_time;
	}
	public void setMod_time(String mod_time) {
		this.mod_time = mod_time;
	}
	
	
	
	@Override
	public String toString() {
		return "Reviews [rev_id=" + rev_id + ", m_id=" + m_id + ", review=" + review + ", rating=" + rating
				+ ", user_id=" + user_id + ", mod_time=" + mod_time + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(m_id);
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj instanceof Reviews) {
			Reviews other = (Reviews) obj;
			return m_id == other.m_id;
		}
		return false;
	}
	
	
}
