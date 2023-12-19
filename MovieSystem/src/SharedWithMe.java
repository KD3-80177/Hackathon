import java.util.*;
public class SharedWithMe {
	private int rev_id;
	private int movie_id;
	private int rec_from;
	private String review;
	private int rating;
	private String modified;
	public SharedWithMe() {
		super();
	}
	public SharedWithMe(int rev_id, int movie_id, int rec_from, String review, int rating, String modified) {
		super();
		this.rev_id = rev_id;
		this.movie_id = movie_id;
		this.rec_from = rec_from;
		this.review = review;
		this.rating = rating;
		this.modified = modified;
	}
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getRec_from() {
		return rec_from;
	}
	public void setRec_from(int rec_from) {
		this.rec_from = rec_from;
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
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	@Override
	public String toString() {
		return "SharedWithMe [rev_id=" + rev_id + ", movie_id=" + movie_id + ", rec_from=" + rec_from + ", review="
				+ review + ", rating=" + rating + ", modified=" + modified + "]";
	}
	
	
}
