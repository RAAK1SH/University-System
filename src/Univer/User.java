package Univer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
/**
 */
public abstract class User implements Serializable{
	/**
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String name;
	Status s = Status.OFFLINE;
	Languages language = Languages.RUSSIAN;
	private String login;
	/**
	 */
	private String password;
	/**
	 */
	protected int debt = 0;
	/**
	 */
	public User() {

	}
	public User(String name,String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
		Database.getInstance().getUsers().put(this, new Vector<String>());
	}
	public String getLogin() {
		return this.login;
	}
	public String getPassword() {
		return this.password;
	}
	public int getDept() {
		return this.debt;
	}
	public void logInto() {
		s = Status.ONLINE;
		viewNews();
		addAction("log Into");
	}
	public void logOut() {
		s = Status.OFFLINE;
		addAction("log Out");
	}
	public void viewNews() {
		Database.getInstance().getNews().stream().forEach(n -> System.out.print(n));
		addAction("view News");
	}
	/**
	 */
	public void changeLanguage(String language) {
		if(language.equals("Russia")) {
			this.language = Languages.RUSSIAN;
		}
		if(language.equals("Kazakh")) {
			this.language = Languages.KAZAKH;
		}
		if(language.equals("English")) {
			this.language = Languages.ENGLISH;
		}
		addAction("change Language");
	}
	/**
	 */
	public void changePassword(String password) {
		if(password.length() > 6) {
			this.password = password;
			System.out.println("Password changed!");
		}
		else {
			System.out.println("Error! Size of password is less than 6.");
		}
		addAction("change Password");
	}
	/**
	 */
	/**
	 */
	public void getBook(Book b) {
		Vector<Book> temp = Database.getInstance().getBorrowedBooks().get(this);
		if(temp == null) {
			temp = new Vector<Book>();
		}
		if(temp.size() > 6) {
			System.out.println("You already have 6 books!");
		}
		else {
			b.setDateOfGetting();
			temp.add(b);
			HashMap<Book,Integer> temporary = Librarian.availableBooks;
			if(temporary.get(b) == 0) {
				System.out.println("No such book");
				return;
			}
			Librarian.availableBooks.put(b, temporary.get(b) - 1);
			Database.getInstance().getBorrowedBooks().put(this, temp);
			System.out.println("Now you have a book:" + b.name);
			b.setDateOfGetting();
		}
		addAction("get Book");
	}
	/**
	 */
	public void setPassword(String password) {
	  this.password = password;
	 }
	 
	 public void setLogin(String login) {
	  this.login = login;
	 }
	public void sendApplication(String topic) throws NumberFormatException, IOException {
		Application a = new Application(topic,this);
		Database.getInstance().getAplications().add(a);
	}
	/**
	 */
	public void giveBook(Book b,Librarian l) {
		try {
			Vector<Book> temp = Database.getInstance().getBorrowedBooks().get(this);
			if(temp.contains(b)) {
				l.checkDate(this, b);
			}
			temp.remove(b);
			Database.getInstance().getBorrowedBooks().put(this, temp);
			Librarian.availableBooks.put(b, Librarian.availableBooks.get(b) + 1);
		}
		catch(Exception e) {
			System.out.println("No such book in borrowed Books");
		}
		addAction("give Book");
	}
	/**
	 */
	public void addAction(String s) {
		if(Database.getInstance().getUsers().get(this) == null) {
			Vector<String> v = new Vector<String>();
			v.add(s);
			Database.getInstance().getUsers().put(this, v);
		}
		else {
			Database.getInstance().getUsers().get(this).add(s);
		}
	}
	public String toString() {
		return "name: " + name + ", login: " + login;
	}
}

