import java.util.*;
public class shares {
	private int r_id;
	private int u_id;
	public shares() {
		super();
	}
	public shares(int r_id, int u_id) {
		super();
		this.r_id = r_id;
		this.u_id = u_id;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(u_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof shares) {
			shares other = (shares) obj;
			return u_id == other.u_id;
		}
		return false;
	}
	
	
}
