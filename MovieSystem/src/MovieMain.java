import java.util.*;
public class MovieMain {

	static Scanner sc = new Scanner(System.in);
	
	public static void operations() {
		int choice;
		User currentUser = null;
		do {
			System.out.println("\n0. Exit\n1. Sign Up\n2. Sign In\nEnter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 0:
				System.out.println("Bye....");
				break;
			case 1:
				signup();
				break;
			case 2:
				signIn();
				break;
			default:
				System.out.println("Wrong choice.....");
				break;
			}
		} while (choice != 0);
	}
	
	public static void signup() {
		System.out.println("Enter First Name: ");
		String fname = sc.next();
		System.out.println("Enter Last Name: ");
		String lname = sc.next();
		System.out.println("Enter Email: ");
		String email = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		System.out.println("Enter Mobile Number: ");
		String mobile = sc.next();
		System.out.println("Enter Birth Date(dd-MM-yyyy): ");
		String bdate = sc.next();
		User user = new User(fname,lname,email,password,mobile,bdate);
		try(UserLoginSgnupDao dao = new UserLoginSgnupDao()){
			int count = dao.signUp(user);
			System.out.println("Rows inserted: "+count);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void signIn() {
		String email, password;
		System.out.println("Enter Email: ");
		email = sc.next();
		System.out.println("Enter Password: ");
		password = sc.next();
		try(UserLoginSgnupDao dao = new UserLoginSgnupDao()){
			dao.signIn(email, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void allOperations(int id) {
		int oper;
		do {
			System.out.println("0. LogOut.");
			System.out.println("1. Edit Profile.");
			System.out.println("2. Change Password.");
			System.out.println("3. Write A Review.");
			System.out.println("4. Edit Review.");
			System.out.println("5. Display All Movies.");
			System.out.println("6. Display All Reviews.");
			System.out.println("7. Display My Reviews.");
			System.out.println("8. Display Reviews Shared With Me.");
			System.out.println("9. Share A Review.");
			System.out.println("10. Delete A Review.");
			System.out.println("Enter your choice: ");
			oper = sc.nextInt();
			
			switch(oper) {
			case 0:
				System.out.println("Logged out.");
				break;
			case 1:
				System.out.println("Previous details: ");
				try (UserLoginSgnupDao dao = new UserLoginSgnupDao()){
					List<User> list = dao.getUserDetails(id);
					list.forEach(System.out::println);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				System.out.println("Enter New Details: ");
				System.out.println("Enter First Name: ");
				String fname = sc.next();
				System.out.println("Enter Last Name: ");
				String lname = sc.next();
				System.out.println("Enter Email: ");
				String email = sc.next();
				System.out.println("Enter Password: ");
				String password = sc.next();
				System.out.println("Enter Mobile Number: ");
				String mobile = sc.next();
				System.out.println("Enter Birth Date(dd-MM-yyyy): ");
				String bdate = sc.next();
				User user = new User(fname,lname,email,password,mobile,bdate);
				try(UserLoginSgnupDao dao = new UserLoginSgnupDao()){
					int count = dao.updateUser(user, id);
					System.out.println("Rows updated: "+count);
				}
				catch(Exception e3) {
					e3.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter Password to change: ");
				String passChange = sc.next();
				try(UserLoginSgnupDao dao = new UserLoginSgnupDao()) {
					int count = dao.updatePassword(id, passChange);
					System.out.println("Rows Updated: "+count);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("List of all movies: ");
				try(MoviesDao md = new MoviesDao()){
					List<Movie> list = md.showAllMovie();
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Enter movie id to add review: ");
				int mid = sc.nextInt();
				System.out.println("Enter rating: ");
				int rating = sc.nextInt();
				System.out.println("Enter review: ");
				sc.nextLine();
				String rev = sc.nextLine();
				Reviews review = new Reviews(mid,rev,rating,id);
				try(ReviewDao rd = new ReviewDao()){
					int cnt = rd.addReview(review);
					System.out.println("Reviews added: "+cnt);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter review id to update: ");
				int r_id = sc.nextInt();
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(r_id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Enter new rating: ");
				int rating1 = sc.nextInt();
				System.out.println("Enter new review: ");
				sc.nextLine();
				String rev1 = sc.nextLine();
				try(ReviewDao rd = new ReviewDao()){
					int count = rd.updateReview(r_id, rating1, rev1);
					System.out.println("Reviews updated: "+count);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("List Of All Movies: ");
				try(MoviesDao md = new MoviesDao()){
					List<Movie> list = md.showAllMovie();
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("List Of All Reviews: ");
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showAllReviews();
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Your reviews: ");
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 8:
				try(SharesDao sd = new SharesDao()){
					List<SharedWithMe> list = sd.displaySharedWithMe(id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 9:
				System.out.println("Your reviews: ");
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Enter review id to be shared: ");
				int shareRev = sc.nextInt();
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(shareRev);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("All users: ");
				try(UserDao ud = new UserDao()){
					List<User> list = ud.showAllUser(id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.print("Enter user ids to share review with(enter 0 to end): ");
				List<Integer> shareReview = new ArrayList<Integer>();
				int shId;
				do {
					shId = sc.nextInt();
					shareReview.add(shId);
				} while (shId != 0);
				try(SharesDao sd = new SharesDao()){
					sd.shareReview(shareReview,shareRev,id);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Review shared with "+(shareReview.size()-1)+" users succesfully");
				break;
				
			case 10:
				System.out.println("Your reviews: ");
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(id);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("Enter review id to delete: ");
				int rid = sc.nextInt();
				try(ReviewDao rd = new ReviewDao()){
					List<Reviews> list = rd.showResReviews(rid);
					list.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				try(ReviewDao rd = new ReviewDao()){
					int cnt = rd.deleteReview(rid);
					System.out.println("Reviews deleted: "+cnt);
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Wrong choice.");
				break;
			}
			
		} while (oper != 0);
	}
	
	public static void main(String[] args) {
		operations();
	}

}
