package Univer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 */
public class Database implements Serializable{
    /**
	 * 
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static Database instance = new Database();
	public static Database getInstance() {
		return instance;
	}
	private static final long serialVersionUID = 1L;
	/**
     */
    private HashMap<User, Vector<String>> users = new HashMap<User, Vector<String>>();

    /**
     */
    public HashMap<User, Vector<String>> getUsers(){
    	return this.users;
    }
    private Vector<Student> temp = new Vector<Student>();
	public Vector<Student> getStudents() {
//		Vector<Student> temp = (Vector<Student>) users.keySet().stream().filter(u -> u instanceof Student);
		for(User u : this.users.keySet()) {
			if(u instanceof Student) {
				Student t = (Student) u;
				temp.add(t);
			}
		}
		return temp;
    }
    private Vector<Course> courses = new Vector<Course>();
    /**
     */
    private HashMap<User, Vector<Book>> borrowedBooks = new HashMap<User,Vector<Book>>();
    private Queue<Application> applications = new LinkedList<Application>();
    /**
     */
    public HashMap<User, Vector<Book>> getBorrowedBooks(){
    	return borrowedBooks;
    }
    public Queue<Application> getAplications(){
    	return applications;
    }
    public Vector<Course> getCourses(){
    	return courses;
    }
    private Vector<News> news = new Vector<News>();
    public Vector<News> getNews(){
    	return news;
    }
	static void writeData(Database data) throws Exception{
		FileOutputStream fos = new FileOutputStream("data.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(data);
		fos.close();
		oos.close();
	}
	@SuppressWarnings({ "unused" })
	static void deserialize(Database data) {
		FileInputStream fos;
		try {
			fos = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fos);
			Database t1 = (Database)in.readObject();
			fos.close();
			in.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException ex)
        {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	static Database readData() throws Exception{
		FileInputStream fis = new FileInputStream("data.ser");
		try (ObjectInputStream ois = new ObjectInputStream(fis)) {
			Database data = (Database)ois.readObject();
			return data;
		}
	}
}

